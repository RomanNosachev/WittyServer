package com.grandfather.WittyServer.broker;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.grandfather.WittyServer.audio.AudioDriver;

import io.moquette.broker.Server;
import io.moquette.broker.config.ClasspathResourceLoader;
import io.moquette.broker.config.IConfig;
import io.moquette.broker.config.IResourceLoader;
import io.moquette.broker.config.ResourceLoaderConfig;
import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.InterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import io.moquette.interception.messages.InterceptSubscribeMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.mqtt.MqttMessageBuilders;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttQoS;

public final class BrokerLauncher 
implements Runnable
{
	IResourceLoader classpathLoader = new ClasspathResourceLoader();
    final IConfig classPathConfig = new ResourceLoaderConfig(classpathLoader);
    
    final Server mqttBroker = new Server();
    List<? extends InterceptHandler> userHandlers = Collections.singletonList(new PublisherListener());
	
    static AudioDriver audioDriver = new AudioDriver();
    
    static class PublisherListener extends AbstractInterceptHandler 
    {

        @Override
        public String getID() 
        {
            return "BrokerLauncher";
        }

        @Override
        public void onPublish(InterceptPublishMessage msg) 
        {
        	ByteBuf payload = msg.getPayload();
        	
            final String decodedPayload = new String(ByteBufUtil.getBytes(payload), Charset.forName("UTF-8"));
            System.out.println("Received on topic: " + msg.getTopicName() + " content: " + decodedPayload);
            
            if (msg.getTopicName().equals("light/effect"))
            {                           	
            	audioDriver.setAudioPattern(decodedPayload);
            	new Thread(audioDriver).start();
            }
        }

		@Override
		public void onSubscribe(InterceptSubscribeMessage msg) 
		{
			final String decodedPaylod = msg.getClientID();
			System.out.println(decodedPaylod);
		}
    }

    public void publish(String topic, String payload, String clientId)
    {
    	System.out.println("Publish: " + topic);
    	
    	MqttPublishMessage message = MqttMessageBuilders.publish()
                .topicName(topic)
                .retained(true)
                .qos(MqttQoS.EXACTLY_ONCE)
                .payload(Unpooled.copiedBuffer(payload.getBytes(UTF_8)))
                .build();
    	
    	try {
    		mqttBroker.internalPublish(message, clientId);
    	}
    	catch (NullPointerException e) {}
    	
    	if (topic.equals("light/effect"))
        {                           	
    		audioDriver.setAudioPattern(payload);
            new Thread(audioDriver).start();
        }
    }
    
	@Override
	public void run() 
	{
		try {
			mqttBroker.startServer(classPathConfig, userHandlers);
		} catch (IOException e) {
			e.printStackTrace();
		}

        System.out.println("Broker started press [CTRL+C] to stop");
        
        //Bind  a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Stopping broker");
            mqttBroker.stopServer();
            System.out.println("Broker stopped");
        }));

        try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        System.out.println("Before self publish");
        MqttPublishMessage message = MqttMessageBuilders.publish()
            .topicName("/exit")
            .retained(true)
//        qos(MqttQoS.AT_MOST_ONCE);
//        qQos(MqttQoS.AT_LEAST_ONCE);
            .qos(MqttQoS.EXACTLY_ONCE)
            .payload(Unpooled.copiedBuffer("Hello World!!".getBytes(UTF_8)))
            .build();

        mqttBroker.internalPublish(message, "INTRLPUB");
        System.out.println("After self publish");
	}
}
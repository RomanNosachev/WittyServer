package com.grandfather.WittyServer.broker;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import io.moquette.broker.Server;
import io.moquette.broker.config.ClasspathResourceLoader;
import io.moquette.broker.config.IConfig;
import io.moquette.broker.config.IResourceLoader;
import io.moquette.broker.config.ResourceLoaderConfig;
import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.InterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.mqtt.MqttMessageBuilders;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttQoS;

public class BrokerLauncher 
{
    class PublisherListener extends AbstractInterceptHandler 
    {
        @Override
        public String getID() {
            return "EmbeddedLauncherPublishListener";
        }

        @Override
        public void onPublish(InterceptPublishMessage msg) {
            final String decodedPayload = new String(msg.getPayload().array(), UTF_8);
            System.out.println("Received on topic: " + msg.getTopicName() + " content: " + decodedPayload);
        }
    }
    
    public void start() throws IOException, InterruptedException
    {
    	IResourceLoader classpathLoader = new ClasspathResourceLoader();
        final IConfig classPathConfig = new ResourceLoaderConfig(classpathLoader);
        final Server mqttBroker = new Server();
        
        List<? extends InterceptHandler> userHandlers = Collections.singletonList(new PublisherListener());
    	
        mqttBroker.startServer(classPathConfig, userHandlers);

        System.out.println("Broker started press [CTRL+C] to stop");
        //Bind  a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Stopping broker");
            mqttBroker.stopServer();
            System.out.println("Broker stopped");
        }));

        Thread.sleep(20000);
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

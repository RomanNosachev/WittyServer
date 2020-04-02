package com.wittyhome.broker.model;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.model.Service;

import io.moquette.broker.Server;
import io.moquette.broker.config.IConfig;
import io.moquette.broker.config.IResourceLoader;
import io.moquette.broker.config.ResourceLoaderConfig;
import io.moquette.interception.InterceptHandler;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.mqtt.MqttMessageBuilders;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttQoS;

@Component
@EnableScheduling
public final class BrokerLauncher 
implements Service
{
	private static final Logger LOG = LoggerFactory.getLogger(BrokerLauncher.class);
	
    private final Server broker = new Server();
    
    private Broadcaster broadcaster;
    private final int port = 1234;
    
	@Autowired
    public BrokerLauncher(List<? extends InterceptHandler> handlers)
    { 	
		try {
			IResourceLoader classpathLoader = new CustomClasspathResourceLoader("/config/moquette.conf");
		    final IConfig classPathConfig = new ResourceLoaderConfig(classpathLoader);
		    
			broker.startServer(classPathConfig, handlers);
			
			LOG.info("Broker started, press [CTRL+C] to stop");
		} catch (IOException | IllegalArgumentException e) {
			LOG.error(e.getMessage());
		}
        
        //Bind  a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOG.info("Stopping broker");
            broker.stopServer();
            LOG.info("Broker stopped");
        }));
    }
	
	@Autowired
	public void setBroadcaster(Broadcaster broadcaster)
	{
		this.broadcaster = broadcaster;
	}
    
	@Scheduled(fixedDelay = 60_000)
	public void deviceBroadcast()
	{
		if (Objects.nonNull(broadcaster))
		{
			broadcaster.broadcast("WittyServer", port);
			
			LOG.info("Broadcast to port {} done", port);
		}
	}
	
    public void publish(String topic, String payload, String clientId)
    {
    	LOG.info("Internal publish to {} with payload: {}.", topic, payload);
    	
    	MqttPublishMessage message = MqttMessageBuilders.publish()
                .topicName(topic)
                .retained(true)
                .qos(MqttQoS.EXACTLY_ONCE)
                .payload(Unpooled.copiedBuffer(payload.getBytes(UTF_8)))
                .build();

    	try {
    		broker.internalPublish(message, clientId);
    	}
    	catch (NullPointerException e) {}
    }    
}

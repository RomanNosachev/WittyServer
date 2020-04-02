package com.wittyhome.broker.model;

import java.nio.charset.Charset;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import io.moquette.interception.messages.InterceptSubscribeMessage;
import io.netty.buffer.ByteBufUtil;

@Component
public class CallbackInterceptHandler 
extends AbstractInterceptHandler
{
	public interface PublishCallback {
		void callingBack(InterceptPublishMessage msg);
	}
	
	public interface SubscribeCallback {
		void callingBack(InterceptSubscribeMessage msg);
	}
	
	private static Logger LOG = LoggerFactory.getLogger(CallbackInterceptHandler.class);
	
	private PublishCallback publishCallback;
	private	SubscribeCallback subscribeCallback;
	
	public void setPublishCallback(PublishCallback callback) 
	{
		this.publishCallback = callback;
	}
	
	public void setSubscribeCallback(SubscribeCallback callback)
	{
		this.subscribeCallback = callback;
	}
	
	@Override
    public void onPublish(InterceptPublishMessage msg) 
    {
        final String decodedPayload = new String(ByteBufUtil.getBytes(msg.getPayload()), Charset.forName("UTF-8"));

        LOG.info("Received on topic: {} content: {}", msg.getTopicName(), decodedPayload);
        
        if (Objects.nonNull(publishCallback))
        	publishCallback.callingBack(msg);
    }

	@Override
	public void onSubscribe(InterceptSubscribeMessage msg) 
	{				
		LOG.info("Username {} with ID {} subscribed", msg.getUsername(), msg.getClientID());
		
		if (Objects.nonNull(subscribeCallback))
			subscribeCallback.callingBack(msg);
	}
	
	@Override
	public String getID() 
	{
		return "CallbackInterceptHandler";
	}
}

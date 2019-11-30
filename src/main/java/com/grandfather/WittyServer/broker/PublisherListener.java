package com.grandfather.WittyServer.broker;

import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;

public class PublisherListener extends AbstractInterceptHandler
{

	@Override
	public String getID() 
	{		
		return null;
	}

	@Override
	public void onPublish(InterceptPublishMessage msg) 
	{
		System.out.println("moquette mqtt broker message intercepted, topic: " + msg.getTopicName()
		+ ", content: " + new String(msg.getPayload().array()));
		
		//super.onPublish(msg);
	}
}

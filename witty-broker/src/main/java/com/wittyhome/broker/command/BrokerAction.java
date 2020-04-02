package com.wittyhome.broker.command;

import com.wittyhome.module_base.command.Action;

public class BrokerAction 
implements Action
{
	private static final long serialVersionUID = 4914988651309847389L;
	
	private String topic;
	private String payload;
	private String clientId;

	public BrokerAction()
	{
			
	}

	public BrokerAction(String topic, String payload)
	{
		this.topic = topic;
		this.payload = payload;
	}

	public BrokerAction(String topic, String payload, String clientId)
	{
		this(topic, payload);
			
		this.clientId = clientId;
	}

	public void setTopic(String topic) 
	{
		this.topic = topic;
	}

	public String getTopic() 
	{
		return topic;
	}

	public void setPayload(String payload) 
	{
		this.payload = payload;
	}

	public String getPayload() 
	{
		return payload;
	}

	public void setClientId(String clientId) 
	{
		this.clientId = clientId;
	}

	public String getClientId() 
	{
		return clientId;
	}
}

package com.wittyhome.broker.generator;

import com.wittyhome.module_base.generator.Request;

public class BrokerRequest 
implements Request
{
	private static final long serialVersionUID = -5855137463323926180L;
	
	private String topic;
	private String payload;
	private String clientId;
	
	public BrokerRequest()
	{
		
	}
	
	public BrokerRequest(String topic, String payload)
	{
		this.topic = topic;
		this.payload = payload;
	}
	
	public BrokerRequest(String topic, String payload, String clientId)
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

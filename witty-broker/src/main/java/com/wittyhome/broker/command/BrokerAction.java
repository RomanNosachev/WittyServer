package com.wittyhome.broker.command;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.Action;

@Component
public class BrokerAction 
implements Action
{
	private static final long serialVersionUID = 4914988651309847389L;
	
	@Id
	private String id;
	
	private String topic;
	private String payload;
	private String clientId;

	public BrokerAction() {}

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
	
	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("topic: ")
			.append(topic)
			.append(System.lineSeparator())
			.append("payload: ")
			.append(payload)
			.append(System.lineSeparator())
			.append("clientid: ")
			.append(clientId);
		
		return builder.toString();
	}
	
	@Override
	public Action clone() 
	{
		try {
			return (Action) super.clone();
		}
		catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	@Override
	public void setId(String id) 
	{
		this.id = id;
	}

	@Override
	public String getId() 
	{
		return id;
	}
}

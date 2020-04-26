package com.wittyhome.core.generator;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.generator.Request;

@Component
public class StringRequest 
implements Request
{
	private static final long serialVersionUID = 6794532338262339413L;

	@Id
	private String id;
	
	private String text;
	
	public StringRequest() 
	{
		
	}

	public StringRequest(String text)
	{
		this.text = text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}

	@Override
	public String toString() 
	{
		return String.format("text: %s", text);
	}

	@Override
	public Request clone() 
	{
		try {
			return (Request) super.clone();
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

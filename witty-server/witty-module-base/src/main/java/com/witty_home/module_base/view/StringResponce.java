package com.witty_home.module_base.view;

import org.springframework.stereotype.Component;

@Component
public class StringResponce 
implements Responce
{
	private static final long serialVersionUID = 7074512983181333183L;

	private String body;

	public void setBody(String body)
	{
		this.body = body;
	}
	
	@Override
	public String toString() 
	{
		return body;
	}
}

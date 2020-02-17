package com.witty_home.module_base.controller;

import org.springframework.stereotype.Component;

@Component
public class StringRequest 
implements Request
{
	private static final long serialVersionUID = -3462377014381739738L;
	
	private String request;

	public StringRequest()
	{
		
	}
	
	public StringRequest(String request) 
	{
		this.request = request;
	}
	
	public void setRequest(String request)
	{
		this.request = request;
	}
	
	public String getRequest()
	{
		return request;
	}
	
	@Override
	public String toString() 
	{
		return request;
	}
}

package com.witty_home.module_base.controller;

public class JsonRequest 
extends StringRequest
{
	private static final long serialVersionUID = -5903026150358991522L;
	
	private String body;
	
	public JsonRequest(String body)
	{
		this.body = body;
	}
	
	public void setBody(String body)
	{
		this.body = body;
	}
	
	public String getBody()
	{
		return body;
	}
}

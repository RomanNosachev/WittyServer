package com.wittyhome.core.generator;

import com.wittyhome.module_base.generator.Request;

public class StringRequest 
implements Request
{
	private static final long serialVersionUID = 6794532338262339413L;

	private String name;
	
	public StringRequest() 
	{
		
	}

	public StringRequest(String name)
	{
		this.name = name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}

	@Override
	public String toString() 
	{
		return String.format("StringRequest name=%s", name);
	}
}

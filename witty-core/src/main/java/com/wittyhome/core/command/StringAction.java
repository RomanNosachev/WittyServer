package com.wittyhome.core.command;

import com.wittyhome.module_base.command.Action;

public class StringAction 
implements Action
{
	private static final long serialVersionUID = -5872469110774846472L;

	private String name;
	
	public StringAction() 
	{
	}

	public StringAction(String name)
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
		return String.format("StringAction name=%s", name);
	}
}

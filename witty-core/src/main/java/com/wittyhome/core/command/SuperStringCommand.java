package com.wittyhome.core.command;

//@Component
public class SuperStringCommand 
extends StringCommand
{
	@Override
	public void execute(StringAction action) 
	{
		System.out.println("SUPER STRING COMMAND");
		
		super.execute(action);
		
		System.out.println("SUPER STRING COMMAND");
	}
}

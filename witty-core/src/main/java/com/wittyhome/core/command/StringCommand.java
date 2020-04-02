package com.wittyhome.core.command;

import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.BaseCommand;
import com.wittyhome.core.model.StringService;

@Component
public class StringCommand 
extends BaseCommand<StringAction, StringService>
{
	@Override
	public void execute(StringAction action) 
	{
		System.out.println("StringCommand: " + action.getName());
		
		target.print(action.getName());
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<StringAction> getActionClass() 
	{
		return StringAction.class;
	}
}

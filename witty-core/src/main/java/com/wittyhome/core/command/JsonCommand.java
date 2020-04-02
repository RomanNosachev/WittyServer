package com.wittyhome.core.command;

import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.BaseCommand;
import com.wittyhome.core.model.StringService;

@Component
public class JsonCommand 
extends BaseCommand<JsonAction, StringService>
{
	@Override
	public void execute(JsonAction action) 
	{
		System.out.println("Json command: " + action.getName());
		
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
	public Class<JsonAction> getActionClass() 
	{
		return JsonAction.class;
	}
}

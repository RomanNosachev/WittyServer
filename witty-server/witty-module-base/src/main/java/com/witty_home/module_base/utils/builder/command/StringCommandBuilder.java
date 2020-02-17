package com.witty_home.module_base.utils.builder.command;

import org.springframework.stereotype.Component;

import com.witty_home.module_base.command.StringCommand;
import com.witty_home.module_base.controller.StringRequest;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.model.StringAction;
import com.witty_home.module_base.view.StringResponce;

@Component
public class StringCommandBuilder 
implements CommandBuilder<StringRequest, StringAction, StringResponce>
{
	@Override
	public StringCommand createCommand(StringRequest request,
			StringAction action, StringResponce responce, Model<StringAction> target) 
	{
		/*
		return StringCommand.builder()
				.request(request)
				.action(action)
				.responce(responce)
				.target(target)
				.build();
				*/
		
		return new StringCommand(request, action, responce, target);
	}
}

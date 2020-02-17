package com.witty_home.module_base.controller;

import org.springframework.stereotype.Component;

import com.witty_home.module_base.command.StringCommand;
import com.witty_home.module_base.command.TypedCommand;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.model.StringAction;
import com.witty_home.module_base.view.StringResponce;
import com.witty_home.module_base.view.View;

@Component
public class StringController 
extends BaseController<StringRequest, StringAction, StringResponce>
{
	public StringController(Model<StringAction> model, View<StringResponce> view, 
			TypedCommand<StringRequest, StringAction, StringResponce> command) 
	{
		super(model, view, command);
	}

	@Override
	public void handle(StringRequest request) 
	{
		System.out.println("Request: " + request.toString());
		
		if (request.toString().equalsIgnoreCase(command.getRequest().toString()))
		{
			command.execute();
			updateView();
		}
		else
			System.out.println("Хуйня, я такого запроса не понимаю");

	}
	
	public void setCommand(StringCommand command) 
	{
		super.setCommand(command);
	}
	
	@Override
	public void updateView() 
	{
		view.display(command.getResponce());
	}
	
	@Override
	public Model<StringAction> getModel() 
	{
		return model;
	}
	
	@Override
	public View<StringResponce> getView() 
	{
		return view;
	}
}

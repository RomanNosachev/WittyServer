package com.witty_home.module_base.controller;

import org.springframework.stereotype.Controller;

import com.witty_home.module_base.command.BaseCommand;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.model.PlaybackAction;
import com.witty_home.module_base.view.StringResponce;
import com.witty_home.module_base.view.View;

@Controller
public class PlaybackController 
extends BaseController<StringRequest, PlaybackAction, StringResponce>
{
	public PlaybackController(Model<PlaybackAction> model, View<StringResponce> view,
			BaseCommand<StringRequest, PlaybackAction, StringResponce> command) 
	{
		super(model, view, command);
	}

	@Override
	public void handle(StringRequest request) 
	{
		System.out.println("StringRequest: " + request);
		
		command.execute();
		//command.execute(model);
		updateView();
	}

	@Override
	public void updateView() 
	{
		view.display(command.getResponce());
	}

	@Override
	public Model<PlaybackAction> getModel() 
	{
		return model;
	}

	@Override
	public View<StringResponce> getView() 
	{
		return view;
	}
}

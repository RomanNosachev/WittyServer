package com.witty_home.module_base.controller;

import org.springframework.stereotype.Component;

import com.witty_home.module_base.command.TypedCommand;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.view.Responce;
import com.witty_home.module_base.view.View;

public class BaseController<RQ extends Request, A extends Action, RS extends Responce>
implements Controller<RQ, A, RS> 
{
	final protected Model<A> model; 
	final protected View<RS> view;	
		
	protected TypedCommand<RQ, A, RS> command;
	
	private RQ request;
	
	public BaseController(Model<A> model, View<RS> view, TypedCommand<RQ, A, RS> command) 
	{
		this.model = model;
		this.view = view;
		this.command = command;
	}
	
	@Override
	public void setCommand(TypedCommand<RQ, A, RS> command)
	{
		this.command = command;
	}

	@Override
	public void handle(RQ request) 
	{
		if (request != null)
		{
			this.request = request;
			
			command.execute();
			updateView();
		}
	}

	@Override
	public void updateView() 
	{
		view.display(command.getResponce());
	}

	@Override
	public Model<A> getModel() 
	{
		return model;
	}

	@Override
	public View<RS> getView() 
	{
		return view;
	}

	@Override
	public RQ getRequest() 
	{
		return request;
	}
}

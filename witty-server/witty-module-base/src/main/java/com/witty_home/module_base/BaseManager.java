package com.witty_home.module_base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.witty_home.module_base.command.Command;
import com.witty_home.module_base.controller.Controller;
import com.witty_home.module_base.controller.Request;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.view.Responce;

public class BaseManager<RQ extends Request>
implements Manager<RQ>, Runnable
{
	private List<Controller<RQ, ? extends Action, ? extends Responce>> controllers;
	private List<Command<RQ>> commands;
	
	private RQ lastRequest;
	
	private Class<RQ> requestClass;
	
	public BaseManager(Class<RQ> clazz) 
	{
		controllers = new ArrayList<Controller<RQ, ? extends Action,? extends Responce>>();
		commands = new ArrayList<Command<RQ>>();
		
		this.requestClass = clazz;
	}
	
	@Override
	public void addCommand(Command<RQ> command) 
	{
		commands.add(command);
	}

	@Override
	public void addController(Controller<RQ, ? extends Action, ? extends Responce> controller) 
	{
		controllers.add(controller);
	}

	@Override
	public void handle(RQ request) 
	{
		if (request != null)
		{
			this.lastRequest = request;
			
			controllers.forEach(controller -> controller.handle(request));
		}		
	}
	
	@Override
	public void handle(Request request, Class<?> clazz) 
	{
		if (clazz.isInstance(request))
		{
			handle((RQ) request);
		}
	}
	
	@Override
	public void run() 
	{		
		controllers.forEach(controller -> {
			commands.forEach(command -> {
				//if (command.getRequest().getClass() == controller.getRequest().getClass())
				//	controller.setCommand((TypedCommand<RQ, ? extends Action, ? extends Responce>) command);
			});
		});
	}

	@Override
	public RQ getRequest() 
	{
		return lastRequest;
	}	
	
	@Override
	public Class<RQ> getRequestClass()
	{
		return requestClass;
	}
}

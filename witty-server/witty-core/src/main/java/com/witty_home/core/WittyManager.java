package com.witty_home.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.witty_home.module_base.Manager;
import com.witty_home.module_base.command.Command;
import com.witty_home.module_base.controller.Request;

@Component
public class WittyManager
{
	@Autowired
	private final List<Manager<? extends Request>> managers;
			
	//private List<Command<? extends Request>> commands;
	
	public WittyManager()
	{
		managers = new ArrayList<>();
	}
	
	public void addManager(Manager<? extends Request> manager)
	{
		managers.add(manager);
	}
	
	public <RQ extends Request> void addCommand(Command<RQ> command)
	{
		
	}
	
	public <RQ extends Request> void handle(RQ request, Class<RQ> clazz)
	{		
		managers.forEach(manager -> {
			if (manager.getRequestClass().getCanonicalName().equals(clazz.getCanonicalName()))
				manager.handle(request, clazz);
		});	
	}
}

	

package com.witty_home.module_base.command;

import java.util.List;

import com.witty_home.module_base.controller.Request;

public class MacroCommand<RQ extends Request>
implements Command<RQ>
{
	private static final long serialVersionUID = -7104483553526173118L;

	private RQ request;
	
	private List<Command<RQ>> commands;
	
	public MacroCommand(RQ request, List<Command<RQ>> commands) 
	{
		this.request = request;
		this.commands = commands;
	}
	
	@Override
	public void execute() 
	{
		commands.forEach(command -> command.execute());
	}

	@Override
	public void undo() 
	{
		commands.forEach(command -> command.undo());
	}

	@Override
	public void redo() 
	{
		commands.forEach(command -> command.redo());
	}

	@Override
	public RQ getRequest() 
	{
		return request;
	}

	@Override
	public void setRequest(RQ request) 
	{
		this.request = request;
	}
}

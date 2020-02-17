package com.witty_home.command_module.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.witty_home.module_base.controller.Request;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.view.Responce;

public class CommandEntity 
implements Serializable
{
	@Id
	private String id;
	
	private static final long serialVersionUID = 8337580937901745257L;
	
	private Request request;
	private Action action;
		
	public CommandEntity(Request request, Action action)
	{
		this.request = request;
		this.action = action;		
	}
	
	public String getID()
	{
		return id;
	}
	
	public void setRequest(Request request)
	{
		this.request = request;
	}
	
	public void setAction(Action action)
	{
		this.action = action;
	}
	
	public Request getRequest()
	{
		return request;
	}
	
	public Action getAction()
	{
		return action;
	}
}

package com.wittyhome.module_base.task;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.model.Entity;

public class Task 
implements Serializable, Entity
{
	@Id
	private String id;
	
	private static final long serialVersionUID = 8337580937901745257L;
	
	private Request request;
	private Action action;
		
	public Task() {}
	
	public Task(Request request, Action action)
	{
		this.request = request;
		this.action = action;		
	}
	
	public Task(Request request)
	{
		this.request = request;
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

package com.wittyhome.module_base.task;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.wittyhome.module_base.model.Entity;

public class Scenario 
implements Serializable, Entity
{
	private static final long serialVersionUID = 8186073244856791L;

	@Id
	private String id;
	
	private Task task;
	private Rule rule;
	
	public Scenario() {}
	
	public Scenario(Task task)
	{
		this.task = task;
	}
	
	public Scenario(Task task, Rule rule) 
	{
		this.setTask(task);
		this.setRule(rule);
	}

	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getId()
	{
		return id;
	}
	
	public Task getTask() 
	{
		return task;
	}

	public void setTask(Task task) 
	{
		this.task = task;
	}

	public Rule getRule() 
	{
		return rule;
	}

	public void setRule(Rule rule) 
	{
		this.rule = rule;
	}
}

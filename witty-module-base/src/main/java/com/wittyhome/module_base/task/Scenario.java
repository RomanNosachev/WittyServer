package com.wittyhome.module_base.task;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.wittyhome.module_base.model.Entity;

@ScriptConstraint
public class Scenario 
implements Serializable, Entity
{
	private static final long serialVersionUID = 8186073244856791L;

	@Id
	private String id;
	
	private boolean enabled;
	
	@GroupConstraint
	private List<String> groups;
	
	private Task task;
	
	private Rule rule;
		
	public Scenario() {}
	
	public Scenario(Task task)
	{
		this.task = task;
		
		this.enabled = false;
	}
	
	public Scenario(Task task, Rule rule) 
	{
		this.setTask(task);
		this.setRule(rule);
		
		this.enabled = false;
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

	public boolean isEnabled() 
	{
		return enabled;
	}

	public void setEnabled(boolean enabled) 
	{
		this.enabled = enabled;
	}

	public List<String> getGroups() 
	{
		return groups;
	}
	
	public void setGroups(List<String> groups) 
	{
		this.groups = groups;
	}
}

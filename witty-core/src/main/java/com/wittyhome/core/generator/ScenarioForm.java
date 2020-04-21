package com.wittyhome.core.generator;

import com.wittyhome.module_base.task.Rule;

public class ScenarioForm 
{
	private String requestId;
	private String actionId;
	
	private Rule rule;
	
	public ScenarioForm() 
	{
		rule = new Rule();
	}

	public ScenarioForm(String requestId, String actionId, Rule rule)
	{
		this.setRequestId(requestId);
		this.setActionId(actionId);
		
		this.rule = rule;
	}

	public String getRequestId() 
	{
		return requestId;
	}

	public void setRequestId(String requestId) 
	{
		this.requestId = requestId;
	}

	public String getActionId() 
	{
		return actionId;
	}

	public void setActionId(String actionId) 
	{
		this.actionId = actionId;
	}
	
	public void setRule(Rule rule)
	{
		this.rule = rule;
	}
	
	public Rule getRule()
	{
		return this.rule;
	}
}

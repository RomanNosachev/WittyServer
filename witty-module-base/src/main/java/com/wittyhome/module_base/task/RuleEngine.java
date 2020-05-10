package com.wittyhome.module_base.task;

public interface RuleEngine 
{
	public boolean validate(Scenario scenario);
	public boolean evalRule(Scenario scenario);
}

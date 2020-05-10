package com.wittyhome.module_base.task;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class ScriptValidator 
implements ConstraintValidator<ScriptConstraint, Scenario>
{
	@Autowired
	private RuleEngine ruleEngine;
	
	@Override
	public boolean isValid(Scenario value, ConstraintValidatorContext context) 
	{
		return ruleEngine.validate(value);
	}
}

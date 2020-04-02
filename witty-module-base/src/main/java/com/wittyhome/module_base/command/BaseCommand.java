package com.wittyhome.module_base.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.model.Service;

@Component
public abstract class BaseCommand<A extends Action, S extends Service>
implements Command<A, S>
{	
	protected A action;		
	protected S target;
	
	@Override
	@Autowired
	public void setTarget(S target) 
	{
		this.target = target;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void executeAuto(Action action) 
	{
		if (getActionClass().isInstance(action))
			execute((A) action);
	}
	
	@Override
	public A getAction() 
	{
		return action;
	}
}

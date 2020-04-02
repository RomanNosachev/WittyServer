package com.wittyhome.module_base.command;

import com.wittyhome.module_base.model.Service;

public interface Command<A extends Action, S extends Service>
{
	public void execute(A action);
	public void executeAuto(Action action);
	public void undo();
	public void redo();	
	
	public void setTarget(S target);
	
	public Class<A> getActionClass();
	public A getAction();
}

package com.witty_home.module_base.command;

import com.witty_home.module_base.controller.Request;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.view.Responce;

public interface TypedCommand<RQ extends Request, A extends Action, RS extends Responce>
extends Command<RQ>
{
	public A getAction();
	public RS getResponce();
	
	public void setAction(A action);
	public void setResponce(RS responce);
}

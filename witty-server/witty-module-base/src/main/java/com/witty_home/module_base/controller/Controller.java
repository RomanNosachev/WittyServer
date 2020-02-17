package com.witty_home.module_base.controller;

import com.witty_home.module_base.command.TypedCommand;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.view.Responce;
import com.witty_home.module_base.view.View;

public interface Controller<RQ extends Request, A extends Action, RS extends Responce>
{
	//контроллер знает только о входном типе, информацию о цели хранит в себе команда
	
	public void handle(RQ request);
	public void updateView();
	
	public Model<A> getModel();
	public View<RS> getView();
	
	public RQ getRequest();
	
	public void setCommand(TypedCommand<RQ, A, RS> command);
}

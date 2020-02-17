package com.witty_home.module_base.utils.builder.controller;

import com.witty_home.module_base.command.TypedCommand;
import com.witty_home.module_base.controller.BaseController;
import com.witty_home.module_base.controller.Controller;
import com.witty_home.module_base.controller.Request;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.view.Responce;
import com.witty_home.module_base.view.View;

public class BaseControllerBuilder<RQ extends Request, A extends Action, RS extends Responce>
implements ControllerBuilder<RQ, A, RS>
{
	@Override
	public Controller<RQ, A, RS> createController(Model<A> model, View<RS> view, TypedCommand<RQ, A, RS> command) 
	{
		return new BaseController<RQ, A, RS>(model, view, command);
	}
}

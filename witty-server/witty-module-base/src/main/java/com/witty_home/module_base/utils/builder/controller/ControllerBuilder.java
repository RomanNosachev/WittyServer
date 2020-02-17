package com.witty_home.module_base.utils.builder.controller;

import com.witty_home.module_base.command.TypedCommand;
import com.witty_home.module_base.controller.Controller;
import com.witty_home.module_base.controller.Request;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.view.Responce;
import com.witty_home.module_base.view.View;

public interface ControllerBuilder<RQ extends Request, A extends Action, RS extends Responce>
{
	Controller<RQ, A, RS> createController(Model<A> model, View<RS> view, TypedCommand<RQ, A, RS> command);
}

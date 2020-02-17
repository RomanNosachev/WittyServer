package com.witty_home.module_base.utils.factory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import com.witty_home.module_base.command.TypedCommand;
import com.witty_home.module_base.controller.BaseController;
import com.witty_home.module_base.controller.Request;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.utils.builder.controller.ControllerBuilder;
import com.witty_home.module_base.view.Responce;
import com.witty_home.module_base.view.View;

public class ControllerFactory<RQ extends Request, A extends Action, RS extends Responce> 
{
	private List<ControllerBuilder<RQ, A, RS>> builders;
	
	public ControllerFactory(List<ControllerBuilder<RQ, A, RS>> builders)
	{
		this.builders = builders; 
	}
	
	public BaseController<RQ, A, RS> getController(Model<A> model, View<RS> view, TypedCommand<RQ, A, RS> command)
	{
		ResolvableType type = ResolvableType.forClassWithGenerics(ControllerBuilder.class, model.getClass(),
				view.getClass(), command.getClass());
		
		ControllerBuilder<RQ, A, RS> builder = builders.stream().filter(b -> type.isInstance(b)).findFirst().get();
				
		return (BaseController<RQ, A, RS>) builder.createController(model, view, command);
	}
}

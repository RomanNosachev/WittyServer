package com.witty_home.module_base.utils.factory.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import com.witty_home.module_base.command.TypedCommand;
import com.witty_home.module_base.controller.Request;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.utils.builder.command.CommandBuilder;
import com.witty_home.module_base.view.Responce;

@Component
public class CommandFactory<RQ extends Request, A extends Action, RS extends Responce>
{
	@Autowired
	private List<CommandBuilder<RQ, A, RS>> builders;
	
	public CommandFactory(List<CommandBuilder<RQ, A, RS>> builders)
	{
		this.builders = builders;
	}
	
	public TypedCommand<RQ, A, RS> getCommand(RQ request, A action, RS responce, Model<A> target)
	{
		ResolvableType type = ResolvableType.forClassWithGenerics(CommandBuilder.class, request.getClass(),
				action.getClass(), responce.getClass());
		
		CommandBuilder<RQ, A, RS> builder = builders.stream().filter(b -> type.isInstance(b)).findFirst().get();
				
		return builder.createCommand(request, action, responce, target);
	}
}

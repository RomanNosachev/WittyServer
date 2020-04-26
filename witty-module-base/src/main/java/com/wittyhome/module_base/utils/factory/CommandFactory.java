package com.wittyhome.module_base.utils.factory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ResolvableType;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.command.Command;
import com.wittyhome.module_base.model.Service;
import com.wittyhome.module_base.utils.builder.CommandBuilder;

//@Component
public class CommandFactory<A extends Action, S extends Service>
{
	//@Autowired
	private List<CommandBuilder<A, S>> builders;
	
	public CommandFactory(List<CommandBuilder<A, S>> builders)
	{
		this.builders = builders;
	}
	
	public Command<A, S> getCommand(A action, S service)
	{
		ResolvableType type = ResolvableType.forClassWithGenerics(CommandBuilder.class, action.getClass(),
				service.getClass());
		
		CommandBuilder<A, S> builder = builders.stream().filter(b -> type.isInstance(b)).findFirst().get();	
				
		return builder.createCommand(action, service);
	}
}

package com.wittyhome.module_base.dispatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.command.Command;
import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.model.Service;
import com.wittyhome.module_base.task.Transformer;

@Component
public class BaseDispatcher 
implements Dispatcher
{
	private Map<Class<? extends Action>, List<Command<? extends Action, ? extends Service>>> commandRegistry;
	
	private Transformer transformer;
	
	@Autowired
	public BaseDispatcher(Transformer transformer) 
	{
		this.transformer = transformer;
		commandRegistry = new HashMap<>();
	}
	
	@Autowired
	public void setCommands(List<Command<? extends Action, ? extends Service>> commands)
	{
		this.commandRegistry = new HashMap<>();
		
		commands.forEach(command -> {
			var commandsWithAction = commandRegistry.get(command.getActionClass());
			
			if (commandsWithAction == null)
				commandsWithAction = new ArrayList<Command<? extends Action, ? extends Service>>();
			
			commandsWithAction.add(command);
			
			commandRegistry.put(command.getActionClass(), commandsWithAction);
		});
	}
	
	@Override
	public <R extends Request> void dispatch(R request) 
	{
		var actions = transformer.findActionsByRequest(request);
		
		actions.forEach(action -> {
			var commands = commandRegistry.get(action.getClass());
			
			commands.forEach(command -> command.executeAuto(action));
		});
	}

	@Override
	public <A extends Action> void registerCommand(Class<A> actionType, Command<A, ? extends Service> command) 
	{
		var commandsWithAction = commandRegistry.get(command.getActionClass());
		
		if (commandsWithAction == null)
			commandsWithAction = new ArrayList<Command<? extends Action, ? extends Service>>();
		
		commandsWithAction.add(command);
		
		commandRegistry.put(command.getActionClass(), commandsWithAction);
	}
}

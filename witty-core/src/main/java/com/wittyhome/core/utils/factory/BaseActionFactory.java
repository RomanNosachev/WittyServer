package com.wittyhome.core.utils.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.utils.factory.ActionFactory;

@Component
public class BaseActionFactory 
implements ActionFactory
{
	private Map<String, Action> actionRegistry;
	
	@Autowired
	public BaseActionFactory(List<? extends Action> actionPrototypes) 
	{
		actionRegistry = new HashMap<>();
		
		actionPrototypes.forEach(action -> {
			this.actionRegistry.put(action.getClass().getName(), action);
		});
	}
	
	@Override
	public Action getAction(String className) 
	{
		return actionRegistry.get(className).clone();
	}

	@Override
	public Action getAction(Class<? extends Action> type)
	{
		return actionRegistry.get(type.getName()).clone();
	}

	@Override
	public List<Class<? extends Action>> getAllActionClasses() 
	{
		return actionRegistry.values().stream().map(action -> action.getClass()).collect(Collectors.toList());
	}

	@Override
	public List<? extends Action> getAllActions() 
	{
		return actionRegistry.values().stream().map(action -> action.clone()).collect(Collectors.toList());
	}

	@Override
	public Map<String, ? extends Action> getActionRegistry() 
	{
		HashMap<String, Action> copy = new HashMap<>();
		
		for (Map.Entry<String, ? extends Action> entry: actionRegistry.entrySet()) {
			copy.put(entry.getKey(), entry.getValue().clone());
		}
		
		return copy;
	}
}

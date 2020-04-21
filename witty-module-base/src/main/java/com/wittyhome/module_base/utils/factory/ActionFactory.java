package com.wittyhome.module_base.utils.factory;

import java.util.List;
import java.util.Map;

import com.wittyhome.module_base.command.Action;

public interface ActionFactory 
{
	Action getAction(String className);
	Action getAction(Class<? extends Action> type);
	
	List<Class<? extends Action>> getAllActionClasses();
	List<? extends Action> getAllActions();
	
	Map<String, ? extends Action> getActionRegistry();
}

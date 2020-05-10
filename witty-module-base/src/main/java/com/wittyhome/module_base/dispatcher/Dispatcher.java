package com.wittyhome.module_base.dispatcher;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.command.Command;
import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.model.Service;

public interface Dispatcher 
{
	public <R extends Request> void dispatch(R request);	
	public <A extends Action> void registerCommand(Class<A> actionType, Command<A, ? extends Service> command);
	
	public void setRequestRepository(RequestRepository requestRepository);
}

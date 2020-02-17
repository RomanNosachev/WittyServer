package com.witty_home.module_base;

import com.witty_home.module_base.command.Command;
import com.witty_home.module_base.controller.Controller;
import com.witty_home.module_base.controller.Request;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.view.Responce;

public interface Manager<RQ extends Request> //дженерик?
{
	/*
	 * основной контроллер, который должен иметь информацию
	 * о сохранненых командах, всех остальных контроллерах, пока не ясно точно
	 */
	
	//public void addCommand(Command<RQ, A, RS> command);
	//public void addController(Controller<RQ, A, RS> controller);
	
	public void addCommand(Command<RQ> command);
	public void addController(Controller<RQ, ? extends Action, ? extends Responce> controller);
	
	public void handle(RQ request);
	public void handle(Request request, Class<?> clazz);
	public RQ getRequest();
	public Class<RQ> getRequestClass();
}

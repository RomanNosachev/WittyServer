package com.witty_home.module_base.command;

import java.io.Serializable;

import com.witty_home.module_base.controller.Request;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.view.Responce;

public abstract class BaseCommand<RQ extends Request, A extends Action, RS extends Responce>
implements Serializable, TypedCommand<RQ, A, RS>
{
	/*
	 * также должна быть реализована поддержка макрокоманд, где каждая вложенная команда
	 * имеет такой же RequestType, но иное значние Action
	 * 
	 * в соответствии с паттерном макрокоманда вызывает интерфейсный метод всех вложенных команд
	 */
	
	private static final long serialVersionUID = -7187347539297227205L;

	protected RQ request;
	protected A action;
	protected RS responce;
	
	public BaseCommand(RQ request, A action, RS responce)
	{
		this.request = request;
		this.action = action;
		this.responce = responce;
	}
	
	@Override
	public RQ getRequest() 
	{
		return request;
	}

	@Override
	public RS getResponce()
	{
		return responce;
	}
	
	@Override
	public A getAction()
	{
		return action;
	}
	
	@Override
	public void setRequest(RQ request)
	{
		this.request = request;
	}
	
	@Override
	public void setAction(A action)
	{
		this.action = action;
	}
	
	@Override
	public void setResponce(RS responce)
	{
		this.responce = responce;
	}
}

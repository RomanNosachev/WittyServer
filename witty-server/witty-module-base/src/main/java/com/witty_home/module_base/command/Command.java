package com.witty_home.module_base.command;

import java.io.Serializable;

import com.witty_home.module_base.controller.Request;

public interface Command<RQ extends Request>
extends Serializable
{
	public void execute();
	public void undo();
	public void redo();
	
	public RQ getRequest();
	
	public void setRequest(RQ request);
}

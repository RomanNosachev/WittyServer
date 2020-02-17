package com.witty_home.module_base;

import com.witty_home.module_base.controller.Request;

public interface ManagerConfiguration<RQ extends Request>
{
	public Manager<RQ> configureManager();
}

package com.witty_home.module_base.view;

public interface View<RS extends Responce>
{
	//представления специфицируется ResponceType
	
	public void display(RS responce);
}

package com.witty_home.module_base.model;

import org.springframework.stereotype.Component;

@Component
public class StringAction 
implements Action
{
	private static final long serialVersionUID = 4095177799872892052L;

	private String name = "StringToUpperCase";

	public StringAction() 
	{
		super();
	}
	
	@Override
	public String toString() 
	{
		return name;
	}
}

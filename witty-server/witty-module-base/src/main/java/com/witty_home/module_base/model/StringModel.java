package com.witty_home.module_base.model;

import org.springframework.stereotype.Component;

@Component
public class StringModel 
implements Model<StringAction> 
{
	private String modelState;
	
	public String getModelState() 
	{
		return modelState;
	}

	public void setModelState(String modelState) 
	{
		this.modelState = modelState;
	}
	
	@Override
	public void execute(StringAction action) 
	{
		if (action != null)
			modelState = action.toString();
	}
}

package com.witty_home.module_base.command;

import org.springframework.stereotype.Component;

import com.witty_home.module_base.controller.StringRequest;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.model.StringAction;
import com.witty_home.module_base.model.StringModel;
import com.witty_home.module_base.view.StringResponce;

@Component
public class StringCommand 
extends BaseCommand<StringRequest, StringAction, StringResponce>
{
	private static final long serialVersionUID = 3086829285466020826L;
	
	private StringModel model;
	
	public StringCommand(StringRequest request, StringAction action, StringResponce responce, Model<StringAction> target) 
	{
		super(request, action, responce);
		this.model = (StringModel) target;
	}

	private String oldModelState;
	private String newModelState;

	@Override
	public void execute() 
	{
		oldModelState = model.getModelState();
				
		model.execute(action);
		newModelState = model.getModelState();
		
		responce.setBody(model.getModelState() + "(" + request + ") = " + request.toString().toUpperCase());
		
		System.out.println("Action executed: " + model.getModelState());
	}

	@Override
	public void undo() 
	{
		model.setModelState(oldModelState);
	}

	@Override
	public void redo() 
	{
		model.setModelState(newModelState);
	}
}

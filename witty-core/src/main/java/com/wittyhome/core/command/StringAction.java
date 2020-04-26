package com.wittyhome.core.command;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.Action;

@Component
public class StringAction 
implements Action
{
	private static final long serialVersionUID = -5872469110774846472L;

	@Id
	private String id;
	
	private String text;
	
	public StringAction() 
	{
	}

	public StringAction(String text)
	{
		this.text = text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}

	@Override
	public String toString() 
	{
		return String.format("text: %s", text);
	}
	
	@Override
	public Action clone() 
	{
		try {
			return (Action) super.clone();
		}
		catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	@Override
	public void setId(String id) 
	{
		this.id = id;
	}

	@Override
	public String getId() 
	{
		return id;
	}
}

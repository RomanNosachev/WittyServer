package com.wittyhome.core.command;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.Action;

@Component
public class PlaybackAction 
implements Action
{
	private static final long serialVersionUID = -6430505659297049835L;

	@Id
	private String id;
	
	private String filename;
	
	public PlaybackAction() {}

	public PlaybackAction(String filename)
	{
		this.filename = filename;
	}
	
	public String getFilename() 
	{
		return filename;
	}

	public void setFilename(String filename) 
	{
		this.filename = filename;
	}
	
	@Override
	public String toString() 
	{
		return String.format("filename: %s", filename);
	}
	
	@Override
	public Action clone()
	{
		try {
			return (Action) super.clone();
		} catch (CloneNotSupportedException e) {
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

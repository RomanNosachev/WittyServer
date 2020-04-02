package com.wittyhome.core.command;

import com.wittyhome.module_base.command.Action;

public class PlaybackAction 
implements Action
{
	private static final long serialVersionUID = -6430505659297049835L;

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
}

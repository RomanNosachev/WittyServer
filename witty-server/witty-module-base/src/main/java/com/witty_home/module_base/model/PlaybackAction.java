package com.witty_home.module_base.model;

import org.springframework.stereotype.Component;

@Component
public class PlaybackAction 
implements Action
{
	private static final long serialVersionUID = -4292917918077996387L;

	private String musicFileName = "puk.mp3";

	public PlaybackAction() 
	{
		super();
	}
	
	public PlaybackAction(String musicFileName)
	{
		super();
		this.musicFileName = musicFileName;
	}
	
	@Override
	public String toString() 
	{
		return musicFileName;
	}
}

package com.witty_home.module_base.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PlaybackModel 
implements Model<PlaybackAction>
{
	private String currentMusicFile;
	
	@Override
	public void execute(PlaybackAction action) 
	{
		if (action != null)
		{
			currentMusicFile = action.toString();
			System.out.println("Play: " + action.toString());
		}
	}

	public String getCurrentMusicFile() 
	{
		return currentMusicFile;
	}

	public void setCurrentMusicFile(String currentMusicFile) 
	{
		this.currentMusicFile = currentMusicFile;
	}
}

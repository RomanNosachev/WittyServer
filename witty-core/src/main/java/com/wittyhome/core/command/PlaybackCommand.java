package com.wittyhome.core.command;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.BaseCommand;
import com.wittyhome.core.model.AudioPlayer;

@Component
public class PlaybackCommand 
extends BaseCommand<PlaybackAction, AudioPlayer>
{
	public PlaybackCommand() {}

	@Override
	public void execute(PlaybackAction action) 
	{
		if (Objects.nonNull(action) && !action.getFilename().isEmpty())	
			target.play(action.getFilename());
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<PlaybackAction> getActionClass() 
	{
		return PlaybackAction.class;
	}

}

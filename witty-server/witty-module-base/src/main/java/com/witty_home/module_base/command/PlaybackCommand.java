package com.witty_home.module_base.command;

import org.springframework.stereotype.Component;

import com.witty_home.module_base.controller.StringRequest;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.model.PlaybackAction;
import com.witty_home.module_base.model.PlaybackModel;
import com.witty_home.module_base.view.StringResponce;

@Component
public class PlaybackCommand 
extends BaseCommand<StringRequest, PlaybackAction, StringResponce>
{
	private static final long serialVersionUID = -2507820569838521348L;

	private PlaybackModel model;
	
	private String oldMusicFile;
	private String newMusicFile;

	public PlaybackCommand(StringRequest request, PlaybackAction action, StringResponce responce, Model<PlaybackAction> target) 
	{
		super(request, action, responce);
		this.model = (PlaybackModel) target;
	}

	@Override
	public void execute() 
	{		
		oldMusicFile = model.getCurrentMusicFile();
		model.execute(action);
		
		newMusicFile = model.getCurrentMusicFile();
		
		responce.setBody("Played:" + model.getCurrentMusicFile());
		
		System.out.println("BEEP BEEP PLAY " + model.getCurrentMusicFile() + ", Requested: " + request.toString());
	}

	@Override
	public void undo() 
	{
		model.setCurrentMusicFile(oldMusicFile);
	}

	@Override
	public void redo() 
	{
		model.setCurrentMusicFile(newMusicFile);
	}
}

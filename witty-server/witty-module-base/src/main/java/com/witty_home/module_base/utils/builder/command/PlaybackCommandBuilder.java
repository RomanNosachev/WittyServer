package com.witty_home.module_base.utils.builder.command;

import org.springframework.stereotype.Component;

import com.witty_home.module_base.command.PlaybackCommand;
import com.witty_home.module_base.controller.StringRequest;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.model.PlaybackAction;
import com.witty_home.module_base.view.StringResponce;

@Component
public class PlaybackCommandBuilder 
implements CommandBuilder<StringRequest, PlaybackAction, StringResponce>
{
	@Override
	public PlaybackCommand createCommand(StringRequest request,
			PlaybackAction action, StringResponce responce, Model<PlaybackAction> target) 
	{
		/*
		return PlaybackCommand.builder()
				.request(request)
				.action(action)
				.responce(responce)
				.target(target)
				.build();
				*/
		
		return new PlaybackCommand(request, action, responce, target);
	}
}

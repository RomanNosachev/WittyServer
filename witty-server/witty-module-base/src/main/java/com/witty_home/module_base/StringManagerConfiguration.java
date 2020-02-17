package com.witty_home.module_base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.witty_home.module_base.command.PlaybackCommand;
import com.witty_home.module_base.command.StringCommand;
import com.witty_home.module_base.controller.JsonRequest;
import com.witty_home.module_base.controller.StringController;
import com.witty_home.module_base.controller.StringRequest;

@Configuration
public class StringManagerConfiguration 
implements ManagerConfiguration<StringRequest>
{
	@Autowired
	private StringController stringController;
	
	@Autowired
	private StringCommand stringCommand;
	
	@Autowired
	private PlaybackCommand playbackCommand;
	
	@Bean("StringManager")
	@Override
	public StringManager configureManager() 
	{
		StringManager manager = new StringManager(StringRequest.class);
				
		stringCommand.setRequest(new StringRequest("String request"));
		playbackCommand.setRequest(new StringRequest("Playback request"));
		
		JsonRequest request = new JsonRequest("Pepka");
		request.setRequest("Json request");
		
		stringCommand.setRequest(request);
		
		stringController.setCommand(stringCommand);
		manager.addController(stringController);
		
		return manager;
	}
}

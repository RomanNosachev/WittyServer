package com.wittyhome.broker.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wittyhome.broker.model.BrokerLauncher;
import com.wittyhome.module_base.command.BaseCommand;

@Component
public class BrokerCommand 
extends BaseCommand<BrokerAction, BrokerLauncher>
{
	private static final Logger LOG = LoggerFactory.getLogger(BrokerCommand.class);
	
	@Override
	public void execute(BrokerAction action) 
	{
		target.publish(action.getTopic(), action.getPayload(), action.getClientId());
		
		LOG.info("BrokerCommand executed, message sent to interal publish");
	}

	@Override
	public void undo() 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void redo() 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public Class<BrokerAction> getActionClass() 
	{
		return BrokerAction.class;
	}
}

package com.grandfather.WittyServer.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.grandfather.WittyServer.broker.BrokerLauncher;
import com.grandfather.WittyServer.speechRecognition.RecognitionDriver;
import com.grandfather.WittyServer.speechRecognition.SphinxConfigurator;

@Component
public class ComponentStarter 
implements InitializingBean
{
	private BrokerLauncher brokerLauncher = new BrokerLauncher();
	private RecognitionDriver recognitionDriver = new RecognitionDriver(SphinxConfigurator.getConfig(), brokerLauncher);

	@Override
	public void afterPropertiesSet() throws Exception 
	{
		new Thread(brokerLauncher).start();
		
		new Thread(recognitionDriver).start();
	}
}

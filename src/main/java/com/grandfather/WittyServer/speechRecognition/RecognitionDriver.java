package com.grandfather.WittyServer.speechRecognition;

import java.io.IOException;

import com.grandfather.WittyServer.broker.BrokerLauncher;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

public class RecognitionDriver 
implements Runnable
{
	private Configuration configuration;
	
	private LiveSpeechRecognizer jsgfRecognizer;
	
	private BrokerLauncher broker;
	
	public RecognitionDriver(Configuration config, BrokerLauncher broker)
	{
		this.configuration = config;
		this.broker = broker;
	}
	
	@Override
	public synchronized void run() 
	{
		try {
			jsgfRecognizer = new LiveSpeechRecognizer(configuration);
		} catch (IOException ioe) {}
		
		jsgfRecognizer.startRecognition(true);
		
		while (true) 
        {
            System.out.println("Server is listening now...");

            String utterance = jsgfRecognizer.getResult().getHypothesis();

            System.out.println("\n" + utterance);
            
            if (utterance.endsWith("менты")) 
            {
                jsgfRecognizer.stopRecognition();
                
                broker.publish("light/effect", "police", "");
                
                jsgfRecognizer.startRecognition(true);
            }
            
            if (utterance.endsWith("пульс"))
            {
            	jsgfRecognizer.stopRecognition();
                
                broker.publish("light/effect", "pulse", "");
                
                jsgfRecognizer.startRecognition(true);
            }
            
            if (utterance.indexOf("радуга") != -1)
            {
            	jsgfRecognizer.stopRecognition();
            	
                broker.publish("light/effect", "rainbowCycle", "");

                jsgfRecognizer.startRecognition(true);
            }

            if (utterance.indexOf("шок") != -1)
            {
                jsgfRecognizer.stopRecognition();
                
                broker.publish("light/effect", "shodan", "");
                
                jsgfRecognizer.startRecognition(true);
            }
        }
	}
}
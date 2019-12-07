package com.grandfather.WittyServer.speechRecognition;

import java.io.IOException;

import com.grandfather.WittyServer.broker.BrokerLauncher;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

public class RecognitionDriver 
{
	private static Configuration configuration;
	
	private LiveSpeechRecognizer jsgfRecognizer;
	
	private BrokerLauncher broker;
	
	private static final String ACOUSTIC_MODEL =
	        "resource:/edu/cmu/sphinx/models/en-us/en-us";
	    private static final String DICTIONARY_PATH =
	        "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict";
	    private static final String GRAMMAR_PATH =
	        "resource:/grammars/";
	    private static final String LANGUAGE_MODEL =
	        "resource:/models/russian/weather.lm";
	
	public RecognitionDriver(Configuration config, BrokerLauncher broker)
	{
		//configuration = config;
		
		configuration = new Configuration();
        configuration.setAcousticModelPath(ACOUSTIC_MODEL);
        configuration.setDictionaryPath(DICTIONARY_PATH);
        configuration.setGrammarPath(GRAMMAR_PATH);
        configuration.setUseGrammar(true);

        configuration.setGrammarName("dialog");
		try {
			jsgfRecognizer = new LiveSpeechRecognizer(configuration);
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}

        configuration.setUseGrammar(false);
        configuration.setLanguageModelPath(LANGUAGE_MODEL);
        		
		this.broker = broker;
		
		/*
		 try {
			jsgfRecognizer = new LiveSpeechRecognizer(configuration);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public void handleVoiceCommand() 
	{
		jsgfRecognizer.startRecognition(true);
        
        while (true) 
        {
            System.out.println("Say command:");
            System.out.println("Example: exit");
            System.out.println("Example: call police");

            String utterance = jsgfRecognizer.getResult().getHypothesis();

            System.out.println(utterance);
            
            if (utterance.startsWith("exit"))
                break;

            if (utterance.endsWith("police")) 
            {
                jsgfRecognizer.stopRecognition();
                
                broker.publish("light/effect", "police", "");
                
                jsgfRecognizer.startRecognition(true);
            }
            
            if (utterance.indexOf("rainbow") != -1)
            {
            	jsgfRecognizer.stopRecognition();
            	
                broker.publish("light/effect", "rainbowCycle", "");

                jsgfRecognizer.startRecognition(true);
            }

            if (utterance.endsWith("home")) 
            {
                jsgfRecognizer.stopRecognition();
                
                broker.publish("light/effect", "shodan", "");
                
                jsgfRecognizer.startRecognition(true);
            }
        }

        jsgfRecognizer.stopRecognition();
	}
}
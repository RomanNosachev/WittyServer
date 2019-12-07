package com.grandfather.WittyServer.speechRecognition;

import edu.cmu.sphinx.api.Configuration;

public class SphinxConfigurator 
{	
	private static final String ACOUSTIC_MODEL = "resource:/edu/cmu/sphinx/models/en-us/en-us";
	private static final String DICTIONARY_PATH = "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict";
	private static final String GRAMMAR_PATH = "resource:/grammars/";
	private static final String LANGUAGE_MODEL = "resource:/models/russian/weather.lm";

	public static Configuration getConfig()
	{
		Configuration config = new Configuration();
		
		config.setAcousticModelPath(ACOUSTIC_MODEL);
        config.setDictionaryPath(DICTIONARY_PATH);
        config.setGrammarPath(GRAMMAR_PATH);
        config.setUseGrammar(true);
        config.setGrammarName("dialog");
        config.setUseGrammar(false);
        config.setLanguageModelPath(LANGUAGE_MODEL);
        
        return config;
	}
}

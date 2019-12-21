package com.grandfather.WittyServer.speechRecognition;

import edu.cmu.sphinx.api.Configuration;

public class SphinxConfigurator 
{	
	private static final String ACOUSTIC_MODEL = "resource:/models/russian/";
	private static final String DICTIONARY_PATH = "resource:/models/russian/ru.dict";
	private static final String GRAMMAR_PATH = "resource:/grammars/";

	public static Configuration getConfig()
	{
		Configuration config = new Configuration();
		
		config.setAcousticModelPath(ACOUSTIC_MODEL);
        config.setDictionaryPath(DICTIONARY_PATH);
        config.setGrammarPath(GRAMMAR_PATH);
        config.setUseGrammar(true);
        config.setGrammarName("dialog");
        
        return config;
	}
}

package com.grandfather.WittyServer.speechRecognition;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

public class Main 
{
    private static final String ACOUSTIC_MODEL =
        "resource:/edu/cmu/sphinx/models/en-us/en-us";
    private static final String DICTIONARY_PATH =
        "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict";
    private static final String GRAMMAR_PATH =
        "resource:/grammars/";
    private static final String LANGUAGE_MODEL =
        "resource:/models/russian/weather.lm";

    public void start() throws Exception 
    {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath(ACOUSTIC_MODEL);
        configuration.setDictionaryPath(DICTIONARY_PATH);
        configuration.setGrammarPath(GRAMMAR_PATH);
        configuration.setUseGrammar(true);

        configuration.setGrammarName("dialog");
        LiveSpeechRecognizer jsgfRecognizer =
            new LiveSpeechRecognizer(configuration);

        configuration.setUseGrammar(false);
        configuration.setLanguageModelPath(LANGUAGE_MODEL);
        
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
                
                System.out.println("! Police !");
                
                jsgfRecognizer.startRecognition(true);
            }
            
            if (utterance.indexOf("rainbow") != -1)
            {
            	jsgfRecognizer.stopRecognition();
                
                System.out.println("! Rainbow !");
                
                jsgfRecognizer.startRecognition(true);
            }

            if (utterance.endsWith("shodan")) 
            {
                jsgfRecognizer.stopRecognition();
                
                System.out.println("! Shodan !");
                
                jsgfRecognizer.startRecognition(true);
            }
        }

        jsgfRecognizer.stopRecognition();
    }
}
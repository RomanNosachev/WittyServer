package com.wittyhome.speech_recognition.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.dispatcher.Dispatcher;
import com.wittyhome.module_base.generator.Generator;

//@Component
public class SpeechGenerator 
implements Generator<SpeechRequest>
{
	private static Logger LOG = LoggerFactory.getLogger(SpeechGenerator.class);
	
	private Dispatcher dispatcher;
	
	@Autowired
	public SpeechGenerator(Dispatcher dispatcher) 
	{		
		this.dispatcher = dispatcher;
	}

	@Override
	public void generate(SpeechRequest request) 
	{
		LOG.info("SpeechRequest generated with utterance: {}", request.getUtterance());
		
		dispatcher.dispatch(request);
	}
}

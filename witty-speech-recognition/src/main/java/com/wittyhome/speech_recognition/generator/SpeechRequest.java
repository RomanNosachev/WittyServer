package com.wittyhome.speech_recognition.generator;

import com.wittyhome.module_base.generator.Request;

public class SpeechRequest 
implements Request
{
	private static final long serialVersionUID = -973907559710194871L;

	private String utterance;
	
	public SpeechRequest()
	{
		
	}
	
	public SpeechRequest(String utterance)
	{
		this.utterance = utterance;
	}
	
	public void setUtterance(String utterance)
	{
		this.utterance = utterance;
	}
	
	public String getUtterance()
	{
		return utterance;
	}
}

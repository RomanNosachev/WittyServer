package com.wittyhome.speech_recognition.generator;

import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.generator.Request;

@Component
public class SpeechRequest 
implements Request
{
	private static final long serialVersionUID = -973907559710194871L;

	@Id
	private String id;
	
	@Pattern(regexp = "[\\p{L}]{1}[\\p{L} ]*[\\p{L}]")
	private String utterance;
	
	public SpeechRequest() {}
	
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
	
	@Override
	public String toString() 
	{
		return String.format("utterance: %s", utterance);
	}
	
	@Override
	public Request clone() 
	{
		try {
			return (Request) super.clone();
		}
		catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	@Override
	public void setId(String id) 
	{
		this.id = id;
	}

	@Override
	public String getId()
	{
		return id;
	}
}

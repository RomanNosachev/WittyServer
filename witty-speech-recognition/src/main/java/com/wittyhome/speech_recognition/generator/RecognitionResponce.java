package com.wittyhome.speech_recognition.generator;

import java.util.List;

public class RecognitionResponce 
{
	private List<RecognitionResult> result;
	
	private String partial;
	private String text;
	
	public RecognitionResponce(String partial) 
	{
		this.partial = partial;
	}

	public RecognitionResponce(List<RecognitionResult> result, String text) 
	{
		this.result = result;
		this.text = text;
	}

	public List<RecognitionResult> getResult() 
	{
		return result;
	}

	public void setResult(List<RecognitionResult> result) 
	{
		this.result = result;
	}

	public String getPartial() 
	{
		return partial;
	}

	public void setPartial(String partial) 
	{
		this.partial = partial;
	}

	public String getText() 
	{
		return text;
	}

	public void setText(String text) 
	{
		this.text = text;
	}
}

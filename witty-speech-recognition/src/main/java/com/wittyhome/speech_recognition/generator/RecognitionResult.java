package com.wittyhome.speech_recognition.generator;

public class RecognitionResult 
{
	private float conf;
	private float end;
	private float start;
	
	private String word;
	
	public RecognitionResult(float conf, float end, float start, String word) 
	{
		this.conf = conf;
		this.end = end;
		this.start = start;
		this.word = word;
	}
	
	public RecognitionResult(String word) 
	{
		this.word = word;
	}

	public float getConf() 
	{
		return conf;
	}

	public void setConf(float conf) 
	{
		this.conf = conf;
	}

	public float getEnd() 
	{
		return end;
	}

	public void setEnd(float end) 
	{
		this.end = end;
	}

	public float getStart() 
	{
		return start;
	}

	public void setStart(float start) 
	{
		this.start = start;
	}

	public String getWord() 
	{
		return word;
	}

	public void setWord(String word) 
	{
		this.word = word;
	}
}

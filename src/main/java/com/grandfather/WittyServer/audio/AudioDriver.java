package com.grandfather.WittyServer.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.springframework.util.ResourceUtils;

public class AudioDriver 
implements Runnable
{
	private String audioPattern;
	
	@Override
	public void run() 
	{
		try {
			Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(ResourceUtils.getFile("classpath:static/" + audioPattern + ".wav"));
	        clip.open(inputStream);
	        clip.start(); 
	        
	        System.out.println(audioPattern + " playing now");
		} catch (Exception e) {
	        System.err.println(e.getMessage());
		}
	}

	public String getAudioPattern() 
	{
		return audioPattern;
	}

	public void setAudioPattern(String audioPattern) 
	{
		this.audioPattern = audioPattern;
	}

}

package com.wittyhome.core.model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.wittyhome.module_base.model.Service;

@org.springframework.stereotype.Service
public class AudioPlayer 
implements Service
{
	private static Logger LOG = LoggerFactory.getLogger(AudioPlayer.class);
	
	private	String lastFilename;
	
	public AudioPlayer() 
	{
	}

	public void play()
	{
		play(lastFilename);
	}
	
	public void play(String filename)
	{
		try {
			Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(ResourceUtils.getFile("classpath:static/" + filename + ".wav"));
	        clip.open(inputStream);
	        clip.start(); 
	        
	        lastFilename = filename;
	        
	        LOG.info("{} is playing now", filename);
		} catch (Exception e) {
	        LOG.error("File not foung", e);
		}
	}
}

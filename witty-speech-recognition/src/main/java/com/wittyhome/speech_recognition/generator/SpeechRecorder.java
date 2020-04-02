package com.wittyhome.speech_recognition.generator;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.annotation.PostConstruct;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpeechRecorder
implements Runnable
{
	private static Logger LOG = LoggerFactory.getLogger(SpeechRecorder.class);

	private SpeechRecordSender sender;
	
	@Autowired
	public SpeechRecorder(SpeechRecordSender sender) 
	{		
		this.sender = sender;
	}
	
	@PostConstruct
	public void startRecording()
	{
		new Thread(this).start();
		
		LOG.info("SpeechRecorder is listening microphone now with {}", getAudioFormat());
	}

	@SuppressWarnings("exports")
	public AudioFormat getAudioFormat() 
	{
        float sampleRate = 8000;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
        return format;
    }

	@Override
	public void run() 
	{
		AudioFormat format = getAudioFormat();
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		
		if (!AudioSystem.isLineSupported(info)) 
		{
            LOG.error("Line with audioformat {} is not supported", format);
            
            return;
		}
		
        try (TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info))
        {
			line.open(format);
			
			line.start(); 
	        AudioInputStream ais = new AudioInputStream(line);
	        
	        while (true)
	        {	        	
	        	ByteBuffer payload = ByteBuffer.wrap(ais.readNBytes((int) getAudioFormat().getSampleRate()));
	        		        	
	        	sender.send(payload);
	        }	        
		} catch (LineUnavailableException e) {
			LOG.error(e.getLocalizedMessage());
		}
        catch (IOException e) {
			LOG.error(e.getLocalizedMessage());
		}
	}
}

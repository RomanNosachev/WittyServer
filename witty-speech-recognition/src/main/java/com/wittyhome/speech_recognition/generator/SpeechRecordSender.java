package com.wittyhome.speech_recognition.generator;

import java.nio.ByteBuffer;

public interface SpeechRecordSender 
{
	public default void send(ByteBuffer payload)
	{
		send("", payload);
	}
	
	public void send(String destination, ByteBuffer payload);
}

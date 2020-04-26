package com.wittyhome.speech_recognition.generator;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import com.google.gson.Gson;
import com.wittyhome.module_base.dispatcher.Dispatcher;
import com.wittyhome.module_base.generator.Generator;

@Service
public class VoskWebsocketAdapter 
implements SpeechRecordSender, Generator<SpeechRequest>
{
    private static final Logger LOG = LoggerFactory.getLogger(VoskWebsocketAdapter.class);
 
    private Dispatcher dispatcher;
    
    private WebSocketClient webSocketClient;
    private WebSocketSession webSocketSession;
    
    private URI uri;
    
    private Gson parser;
    
    @Autowired
    public VoskWebsocketAdapter(Dispatcher dispatcher) 
    {
		this(dispatcher, URI.create("ws://localhost:2700"));
	}
    
    public VoskWebsocketAdapter(Dispatcher dispatcher, String uri)
    {
    	this(dispatcher, URI.create(uri));
    }
    
    public VoskWebsocketAdapter(Dispatcher dispatcher, URI uri) 
    {
		this.dispatcher = dispatcher;
		this.uri = uri;
		
		parser = new Gson();
	}
    
    @PostConstruct
    public void connect() 
    {
        try {
            webSocketClient = new StandardWebSocketClient();
 
            webSocketSession = webSocketClient.doHandshake(new BinaryWebSocketHandler() {
            	@Override
            	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            		LOG.info("Established connection: " + session);
                    
                    webSocketSession = session;
            	}
            	
            	@Override
            	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
            		RecognitionResponce responce = parser.fromJson(message.getPayload(), RecognitionResponce.class);
            		
            		if (Objects.nonNull(responce.getText()) && !responce.getText().isEmpty())
            		{
            			LOG.info("Speech recognized: " + message.getPayload());
            			
            			SpeechRequest request = new SpeechRequest(responce.getText());
            			
            			generate(request);
            		}
            	}
            }, new WebSocketHttpHeaders(), uri).get();
        } catch (Exception e) {
            LOG.error("Exception while accessing websockets", e);
        }
    }

	@Override
	public void send(String destination, ByteBuffer payload) 
	{
		if (Objects.nonNull(webSocketSession))
		{
			BinaryMessage message = new BinaryMessage(payload);
			
			try {
				webSocketSession.sendMessage(message);
			} catch (IOException e) {
				LOG.error("Error sending message to Vosk server", e);
			}
		}
	}

	@Override
	public void generate(SpeechRequest request) 
	{
		dispatcher.dispatch(request);
	}
}
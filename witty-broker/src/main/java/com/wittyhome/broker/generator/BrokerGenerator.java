package com.wittyhome.broker.generator;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wittyhome.broker.model.CallbackInterceptHandler;
import com.wittyhome.broker.model.CallbackInterceptHandler.PublishCallback;
import com.wittyhome.module_base.dispatcher.Dispatcher;
import com.wittyhome.module_base.generator.Generator;

import io.moquette.interception.messages.InterceptPublishMessage;
import io.netty.buffer.ByteBufUtil;

@Component
public class BrokerGenerator 
implements Generator<BrokerRequest> 
{
	private static Logger LOG = LoggerFactory.getLogger(BrokerGenerator.class);

	private Dispatcher dispatcher;
	
	private CallbackInterceptHandler handler;

	@Autowired
	public BrokerGenerator(Dispatcher dispatcher, CallbackInterceptHandler handler) {
		this.dispatcher = dispatcher;
		this.handler = handler;

		this.handler.setPublishCallback(new PublishCallback() {
			@Override
			public void callingBack(InterceptPublishMessage msg) {
				final String decodedPayload = new String(ByteBufUtil.getBytes(msg.getPayload()),
						Charset.forName("UTF-8"));

				BrokerRequest request = new BrokerRequest(msg.getTopicName(), decodedPayload);
				generate(request);
			}
		});
	}

	@Override
	public void generate(BrokerRequest request) 
	{
		LOG.info("{} was generated", request.getClass().getCanonicalName());
				
		dispatcher.dispatch(request);
	}
}

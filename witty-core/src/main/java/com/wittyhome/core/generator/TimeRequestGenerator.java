package com.wittyhome.core.generator;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.dispatcher.Dispatcher;
import com.wittyhome.module_base.generator.Generator;

@Component
public class TimeRequestGenerator 
implements Generator<TimeRequest>
{
	private static Logger LOG = LoggerFactory.getLogger(TimeRequestGenerator.class);
	
	private Dispatcher dispatcher;
	
	@Autowired
	public TimeRequestGenerator(Dispatcher dispatcher) 
	{
		this.dispatcher = dispatcher;
	}
	
	@Scheduled(fixedRate = 60_000)
	private void clock()
	{
		generate(new TimeRequest(new Date()));
	}
	
	@Override
	public void generate(TimeRequest request) 
	{
		dispatcher.dispatch(request);
		
		LOG.info("TimeRequest: {}", request.getTimeString());
	}
}

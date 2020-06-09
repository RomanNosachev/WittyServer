package com.wittyhome.broker.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import com.wittyhome.broker.generator.BrokerRequest;
import com.wittyhome.module_base.task.AsteriskValueTransformer;
import com.wittyhome.module_base.task.CustomExampleSupplier;
import com.wittyhome.module_base.task.Scenario;

@Component
public class BrokerCustomExampleSupplier
implements CustomExampleSupplier<BrokerRequest>
{
	private AsteriskValueTransformer valueTransformer;
	
	@Autowired
	public BrokerCustomExampleSupplier(AsteriskValueTransformer valueTransformer) 
	{
		this.valueTransformer = valueTransformer;
	}
	
	@Override
	public Example<Scenario> get(Scenario scenario) 
	{	
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withTransformer("task.request.payload", valueTransformer);
		
		return Example.of(scenario, matcher);
	}
	
	@Override
	public Class<BrokerRequest> getExampleRequestClass() 
	{
		return BrokerRequest.class;
	}
}

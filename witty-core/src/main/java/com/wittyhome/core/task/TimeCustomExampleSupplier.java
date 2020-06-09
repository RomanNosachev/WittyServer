package com.wittyhome.core.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import com.wittyhome.core.generator.TimeRequest;
import com.wittyhome.module_base.task.AsteriskValueTransformer;
import com.wittyhome.module_base.task.CustomExampleSupplier;
import com.wittyhome.module_base.task.Scenario;

@Component
public class TimeCustomExampleSupplier 
implements CustomExampleSupplier<TimeRequest>
{
	private AsteriskValueTransformer valueTransformer;
	
	@Autowired
	public TimeCustomExampleSupplier(AsteriskValueTransformer valueTransformer) 
	{
		this.valueTransformer = valueTransformer;
	}
	
	@Override
	public Example<Scenario> get(Scenario scenario) 
	{
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withTransformer("task.request.timeString", valueTransformer);
		
		return Example.of(scenario, matcher);
	}

	@Override
	public Class<TimeRequest> getExampleRequestClass() 
	{
		return TimeRequest.class;
	}

}

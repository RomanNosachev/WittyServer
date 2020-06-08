package com.wittyhome.module_base.task;

import org.springframework.data.domain.Example;

import com.wittyhome.module_base.generator.Request;

public interface CustomExampleSupplier<R extends Request> 
{
	public Example<Scenario> get(Scenario scenario);
	public Class<R> getExampleRequestClass();
}

package com.wittyhome.core.utils.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.utils.factory.RequestFactory;

@Component
public class BaseRequestFactory 
implements RequestFactory
{	
	private Map<String, Request> requestRegistry;
	
	@Autowired
	public BaseRequestFactory(List<? extends Request> requestPrototypes) 
	{
		requestRegistry = new HashMap<>();
		
		requestPrototypes.forEach(request -> {
			this.requestRegistry.put(request.getClass().getName(), request);
		});
	}
	
	@Override
	public Request getRequest(String className) 
	{
		Request request = requestRegistry.get(className);
		
		if (Objects.nonNull(request))
			return request.clone();
		
		return request;
	}

	@Override
	public Request getRequest(Class<? extends Request> type)
	{
		Request request = requestRegistry.get(type.getName());
		
		if (Objects.nonNull(request))
			return request.clone();
		
		return request;
	}

	@Override
	public List<Class<? extends Request>> getAllRequestClasses() 
	{
		return requestRegistry.values().stream().map(request -> request.getClass()).collect(Collectors.toList());
	}

	@Override
	public List<? extends Request> getAllRequests() 
	{
		return requestRegistry.values().stream().map(request -> request.clone()).collect(Collectors.toList());
	}
	
	@Override
	public Map<String, ? extends Request> getRequestRegistry() 
	{
		HashMap<String, Request> copy = new HashMap<>();
				
		for (Map.Entry<String, ? extends Request> entry: requestRegistry.entrySet()) {
			copy.put(entry.getKey(), entry.getValue().clone());
		}
		
		return copy;
	}
}

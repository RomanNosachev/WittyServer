package com.wittyhome.module_base.utils.factory;

import java.util.List;
import java.util.Map;

import com.wittyhome.module_base.generator.Request;

public interface RequestFactory 
{
	Request getRequest(String className);
	Request getRequest(Class<? extends Request> type);
	
	List<Class<? extends Request>> getAllRequestClasses();
	List<? extends Request> getAllRequests();
	
	Map<String, ? extends Request> getRequestRegistry();
}

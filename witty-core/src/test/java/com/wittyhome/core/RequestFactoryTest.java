package com.wittyhome.core;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wittyhome.broker.generator.BrokerRequest;
import com.wittyhome.core.generator.StringRequest;
import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.utils.factory.RequestFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestFactoryTest 
{
	@Autowired
	private RequestFactory factory;
	
	@Test
	public void test()
	{
		assertNotNull(factory);
				
		assertNotNull(factory.getRequest(StringRequest.class));
		
		assertNotNull(factory.getRequest(StringRequest.class.getName()));
		
		assertTrue(factory.getRequest("com.wittyhome.broker.generator.BrokerRequest").getClass() 
				== BrokerRequest.class);
		
		Request firstRequest = factory.getRequest(StringRequest.class);
		Request secondRequest = factory.getRequest(StringRequest.class);
		
		assertFalse(firstRequest == secondRequest);
		
		assertTrue(factory.getAllRequestClasses().size() >= 3);
		
		var requests = factory.getAllRequests();
		var nextRequests = factory.getAllRequests();
		
		assertTrue(requests.size() >= 3);
		
		requests.forEach(request -> {
			nextRequests.forEach(nextRequest -> {
				assertFalse(request == nextRequest);
			});	
		});
	}
}

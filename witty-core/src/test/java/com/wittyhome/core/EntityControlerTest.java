package com.wittyhome.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wittyhome.core.generator.ActionController;
import com.wittyhome.core.generator.RequestController;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class EntityControlerTest 
{
	@Autowired
	private RequestController requestController;
	
	@Autowired
	private ActionController actionController;
	
	@Test
	public void saveRequestTest()
	{
		assertNotNull(requestController);		
	}
	
	@Test
	public void saveActionTest()
	{
		assertNotNull(actionController);
	}
}

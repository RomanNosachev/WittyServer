package com.wittyhome.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wittyhome.core.generator.ScenarioController;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class EntityControlerTest 
{
	@Autowired
	private ScenarioController controller;
	
	@Test
	public void scenarioControllerTest()
	{
		assertNotNull(controller);		
	}
}

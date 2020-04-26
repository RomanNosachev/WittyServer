package com.wittyhome.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wittyhome.broker.command.BrokerAction;
import com.wittyhome.core.command.StringAction;
import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.utils.factory.ActionFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActionFactoryTest 
{
	@Autowired
	private ActionFactory factory;
	
	@Test
	public void test()
	{
		assertNotNull(factory);
		
		assertNotNull(factory.getAction(StringAction.class));
		
		assertNotNull(factory.getAction(StringAction.class.getName()));
		
		assertNull(factory.getAction("test string"));
		
		assertTrue(factory.getAction("com.wittyhome.broker.command.BrokerAction").getClass() 
				== BrokerAction.class);
		
		Action firstAction = factory.getAction(StringAction.class);
		Action secondAction = factory.getAction(StringAction.class);
		
		assertFalse(firstAction == secondAction);
		
		assertTrue(factory.getAllActionClasses().size() >= 4);
		
		var actions = factory.getAllActions();
		var nextActions = factory.getAllActions();
		
		assertTrue(actions.size() >= 4);
		
		actions.forEach(action -> {
			nextActions.forEach(nextAction -> {
				assertFalse(action == nextAction);
			});
		});
	}
}

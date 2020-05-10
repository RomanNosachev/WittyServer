package com.wittyhome.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wittyhome.module_base.attribute.GeneralAttributeRegistry;
import com.wittyhome.module_base.attribute.TypedAttributeRegistry;
import com.wittyhome.module_base.generator.Request;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttributeRegistryTest 
{
	@Autowired
	private GeneralAttributeRegistry generalRegistry;
	
	@Test
	public void attributeRegistryTest()
	{
		assertNotNull(generalRegistry);
		
		Map<String, String> stringMap = generalRegistry.findAllByType(String.class);
		
		String color = stringMap.get("globalString.lightColor");
		
		assertNotNull(color);
				
		assertFalse(color.isBlank());
		
		assertNull(generalRegistry.findAllByType(Request.class));
		
		String colorByName = generalRegistry.findByName("lightColor", String.class);
		
		assertEquals(color, colorByName);
		
		assertNull(generalRegistry.findByName("impossibleAttr", String.class));
		
		@SuppressWarnings("unchecked")
		TypedAttributeRegistry<Integer> intRegistry = 
			(TypedAttributeRegistry<Integer>) generalRegistry.findRegistryByPrefix("globalInteger");
		
		Integer memoryPercent = intRegistry.findByName("memoryUsagePercent");
		
		assertNotEquals(memoryPercent.longValue(), 0L);
		
		Map<String, Object> map = generalRegistry.findAll();
		
		assertTrue(map.size() >= 2);
		
		String parsedColor = (String) map.get("globalString.lightColor");
		
		assertEquals(color, parsedColor);
		
		Integer parsedMemoryPercent = (Integer) map.get("globalInteger.memoryUsagePercent");
		
		assertEquals(memoryPercent.longValue(), parsedMemoryPercent.longValue());
	}
}

package com.wittyhome.module_base.attribute;

import java.util.List;
import java.util.Map;

public interface GeneralAttributeRegistry 
{
	<T> TypedAttributeRegistry<T> findRegistryByType(Class<T> clazz);
	TypedAttributeRegistry<?> findRegistryByPrefix(String prefix);
	
	boolean isExist(String attrName);
	
	Object findByName(String attrName);
	<T> T findByName(String attrName, Class<T> clazz);
	<T> Map<String, T> findAllByType(Class<T> clazz);
	
	List<String> getAllPrefix();
	
	Map<String, Object> findAll();
}

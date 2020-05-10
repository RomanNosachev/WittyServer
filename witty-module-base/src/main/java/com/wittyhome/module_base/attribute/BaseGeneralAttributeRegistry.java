package com.wittyhome.module_base.attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseGeneralAttributeRegistry 
implements GeneralAttributeRegistry
{
	private Map<Class<?>, TypedAttributeRegistry<?>> registries;
	
	@Autowired
	public BaseGeneralAttributeRegistry(List<TypedAttributeRegistry<?>> registryList) 
	{
		registries = new HashMap<>();
		
		registryList.forEach(registry -> {
			registries.put(registry.getAttributeClass(), registry);
		});
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> TypedAttributeRegistry<T> findRegistryByType(Class<T> clazz) 
	{
		return (TypedAttributeRegistry<T>) registries.get(clazz);
	}

	@Override
	public boolean isExist(String attrName) 
	{		
		for (TypedAttributeRegistry<?> registry: registries.values())
		{
			if (registry.isExist(attrName)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Object findByName(String attrName) 
	{
		for (TypedAttributeRegistry<?> registry: registries.values())
		{
			Object attribute = registry.findByName(attrName);
			
			if (Objects.nonNull(attribute)) {
				return attribute;
			}
		}
		
		return null;
	}
	
	@Override
	public <T> T findByName(String attrName, Class<T> clazz) 
	{
		TypedAttributeRegistry<T> registry = findRegistryByType(clazz);
		
		if (Objects.nonNull(registry)) {
			return registry.findByName(attrName);
		}
		
		return null;
	}

	@Override
	public <T> Map<String, T> findAllByType(Class<T> clazz) 
	{
		TypedAttributeRegistry<T> registry = findRegistryByType(clazz);

		if (Objects.nonNull(registry)) {
			return registry.findAll();
		}
		
		return null;
	}

	@Override
	public Map<String, Object> findAll() 
	{
		Map<String, Object> result = new HashMap<>();
		
		for (Entry<Class<?>, TypedAttributeRegistry<?>> entry : registries.entrySet())
		{
			Map<String, ?> currentMap = entry.getValue().findAll();
			
			result.putAll(currentMap);
		}
		
		return result;
	}

	@Override
	public TypedAttributeRegistry<?> findRegistryByPrefix(String prefix) 
	{
		for (TypedAttributeRegistry<?> registry: registries.values())
		{
			if (registry.getPrefix().equals(prefix)) {
				return registry;
			}
		}
		
		return null;
	}

	@Override
	public List<String> getAllPrefix() 
	{
		List<String> prefixList = new ArrayList<>();
		
		for (TypedAttributeRegistry<?> registry: registries.values()) {
			prefixList.add(registry.getPrefix());
		}
		
		return prefixList;
	}
}

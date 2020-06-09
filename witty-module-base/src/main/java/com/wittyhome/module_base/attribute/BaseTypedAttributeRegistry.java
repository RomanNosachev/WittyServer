package com.wittyhome.module_base.attribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BaseTypedAttributeRegistry<T>
implements TypedAttributeRegistry<T>
{
	protected HashMap<String, AttributeSupplier<T>> registry;

	public BaseTypedAttributeRegistry(List<AttributeSupplier<T>> suppliers)
	{
		registry = new HashMap<>();
		
		suppliers.forEach(supplier -> {
			StringBuilder builder = new StringBuilder(getPrefix());
			builder.append(".");
			
			builder.append(supplier.getAttributeName());
			
			registry.put(builder.toString(), supplier);
		});
	}
	
	@Override
	public void registerSupplier(String key, AttributeSupplier<T> supplier) 
	{
		registry.put(getPrefix().concat(".").concat(key), supplier);
	}

	@Override
	public boolean isExist(String attrName) 
	{
		return registry.containsKey(getPrefix().concat(".").concat(attrName));
	}

	@Override
	public T findByName(String attrName) 
	{
		AttributeSupplier<T> supplier = registry.get(getPrefix().concat(".").concat(attrName));
		
		if (Objects.nonNull(supplier)) {
			return supplier.get();
		}
		
		return null;
	}

	@Override
	public T findByFullName(String fullAttrName) 
	{
		return registry.get(fullAttrName).get();
	}
	
	@Override
	public Map<String, T> findAll() 
	{		
		return registry.entrySet().stream()
			.collect(Collectors.toMap(
					entry -> entry.getKey(), 
					entry -> entry.getValue().get()));
	}
}

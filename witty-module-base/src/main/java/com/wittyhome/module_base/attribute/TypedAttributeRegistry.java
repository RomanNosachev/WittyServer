package com.wittyhome.module_base.attribute;

import java.util.Map;

public interface TypedAttributeRegistry<T>
{
	void registerSupplier(String key, AttributeSupplier<T> supplier);
	
	boolean isExist(String attrName);
	
	T findByName(String attrName);
	T findByFullName(String fullAttrName);
	Map<String, T> findAll();
	
	Class<T> getAttributeClass();
	String getPrefix();
}

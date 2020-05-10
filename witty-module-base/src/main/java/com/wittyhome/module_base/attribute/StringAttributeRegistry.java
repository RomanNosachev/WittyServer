package com.wittyhome.module_base.attribute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StringAttributeRegistry 
extends BaseTypedAttributeRegistry<String>
{
	@Autowired
	public StringAttributeRegistry(List<AttributeSupplier<String>> suppliers) 
	{
		super(suppliers);
	}

	@Override
	public Class<String> getAttributeClass() 
	{
		return String.class;
	}

	@Override
	public String getPrefix() 
	{
		return "globalString";
	}	
}

package com.wittyhome.module_base.attribute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntegerAttributeRegistry 
extends BaseTypedAttributeRegistry<Integer>
{
	@Autowired
	public IntegerAttributeRegistry(List<AttributeSupplier<Integer>> suppliers)
	{
		super(suppliers);
	}

	@Override
	public Class<Integer> getAttributeClass() 
	{
		return Integer.class;
	}

	@Override
	public String getPrefix() 
	{
		return "globalInteger";
	}
}

package com.wittyhome.core.attribute;

import org.springframework.stereotype.Component;

import com.wittyhome.module_base.attribute.AttributeSupplier;

@Component
public class MemoryUsagePercentAttributeSupplyer 
implements AttributeSupplier<Integer>
{
	@Override
	public Integer get() 
	{
		long total = Runtime.getRuntime().totalMemory();
	    long free = Runtime.getRuntime().freeMemory();
	    
	    long used = total - free;
	    
	    double usedPercent = ((used * 1.0) / total) * 100;
	    
	    return (int) usedPercent;
	}

	@Override
	public String getAttributeName() 
	{
		return "memoryUsagePercent";
	}
}

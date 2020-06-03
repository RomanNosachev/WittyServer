package com.wittyhome.core.attribute;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.wittyhome.module_base.attribute.AttributeSupplier;

@Component
public class CurrentTimeAttributeSupplier 
implements AttributeSupplier<String>
{
	@Override
	public String get() 
	{
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		DateFormat format = new SimpleDateFormat("HH:mm");
		
		return format.format(date);
	}

	@Override
	public String getAttributeName() 
	{
		return "currentTime";
	}

}

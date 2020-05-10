package com.wittyhome.core.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class RoleWritingConverter 
implements Converter<Role, String>
{
	@Override
	public String convert(Role source) 
	{
		return source.getRoleName();
	}
}

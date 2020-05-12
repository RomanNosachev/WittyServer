package com.wittyhome.module_base.generator;

import java.util.Date;

import com.wittyhome.module_base.Data;

public interface Request
extends Data, Cloneable
{
	public void setId(String id);
	public String getId();
	
	public Date getCreatedDate();
	public void setCreatedDate(Date date);
	
	public Request clone();
}

package com.wittyhome.module_base.generator;

import com.wittyhome.module_base.Data;

public interface Request
extends Data, Cloneable
{
	public void setId(String id);
	public String getId();
	
	public Request clone();
}

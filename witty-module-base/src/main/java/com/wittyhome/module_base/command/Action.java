package com.wittyhome.module_base.command;

import com.wittyhome.module_base.Data;

public interface Action 
extends Data, Cloneable
{
	public void setId(String id);
	public String getId();
	
	public Action clone();
}

package com.wittyhome.module_base.task;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.wittyhome.module_base.model.Entity;

public class Rule 
implements Serializable, Entity
{
	private static final long serialVersionUID = 1606844220170062932L;

	@Id
	private String id;

	private String script;
	
	public Rule() {}
	
	public Rule(String script)
	{
		this.script = script;
	}
	
	public void setScript(String script)
	{
		this.script = script;
	}
	
	public String getScript()
	{
		return script;
	}
	
	@Override
	public String toString() 
	{
		return script;
	}
}

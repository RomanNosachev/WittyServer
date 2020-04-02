package com.wittyhome.module_base.task;

import java.util.List;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.generator.Request;

public interface Transformer 
{
	List<Action> findActionsByRequest(Request request);
}

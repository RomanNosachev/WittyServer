package com.wittyhome.module_base.utils.builder.command;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.command.Command;
import com.wittyhome.module_base.model.Service;

@FunctionalInterface
public interface CommandBuilder<A extends Action, S extends Service>
{
	Command<A, S> createCommand(A action, S target);
}

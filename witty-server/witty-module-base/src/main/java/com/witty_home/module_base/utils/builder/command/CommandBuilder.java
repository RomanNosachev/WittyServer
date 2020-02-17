package com.witty_home.module_base.utils.builder.command;

import com.witty_home.module_base.command.TypedCommand;
import com.witty_home.module_base.controller.Request;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.view.Responce;

@FunctionalInterface
public interface CommandBuilder<RQ extends Request, A extends Action, RS extends Responce>
{
	TypedCommand<RQ, A, RS> createCommand(RQ request, A action, RS responce, Model<A> target);
}

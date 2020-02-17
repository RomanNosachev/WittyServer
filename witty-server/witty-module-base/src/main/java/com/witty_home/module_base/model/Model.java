package com.witty_home.module_base.model;

public interface Model<A extends Action>
{
	/*
	 * должна ли модель принимать именно команду?
	 * можно попробовать принимать Action, в котором и будут выполняться действия
	 * 
	 * как команду связать с моделью?
	 * приняв команду, зная, что она точно для этого обработчика -- можно присвоить команде this, а она уже выполнит все
	 * 
	 */
	
	public void execute(A action);
}

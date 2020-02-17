package com.witty_home.module_base.generator;

import com.witty_home.module_base.controller.Request;

public interface Generator<RQ extends Request>
{
	public void generate(RQ request, Class<RQ> clazz);
}

package com.wittyhome.module_base.generator;

public interface Generator<RQ extends Request>
{
	public void generate(RQ request);
}

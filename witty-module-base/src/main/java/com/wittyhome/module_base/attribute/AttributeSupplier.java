package com.wittyhome.module_base.attribute;

import java.util.function.Supplier;

public interface AttributeSupplier<T>
extends Supplier<T>
{
	public String getAttributeName();
}

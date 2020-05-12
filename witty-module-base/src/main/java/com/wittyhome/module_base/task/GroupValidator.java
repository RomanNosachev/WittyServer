package com.wittyhome.module_base.task;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GroupValidator 
implements ConstraintValidator<GroupConstraint, List<String>>
{
	@Override
	public boolean isValid(List<String> value, ConstraintValidatorContext context) 
	{
		if (Objects.nonNull(value) && !value.isEmpty())
		{
			for (String group: value)
			{
				if (group.isBlank()) {
					return false;
				}
			}
			
			Set<String> set = new HashSet<>(value);
			
			if (set.size() < value.size()) {
				return false;
			}
		}
		
		return true;
	}
}

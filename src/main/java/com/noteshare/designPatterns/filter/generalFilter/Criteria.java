package com.noteshare.designPatterns.filter.generalFilter;

import java.util.List;

/**
 * 条件
 */
public interface Criteria {
	public List<Person> meetCriteria(List<Person> persons);
}

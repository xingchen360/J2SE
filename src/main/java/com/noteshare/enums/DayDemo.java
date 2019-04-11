/******************************************************************************
* Copyright (C) itnoteshare.com
******************************************************************************/
package com.noteshare.enums;

/**
 * @Title:DayDemo.java
 * @Description:使用普通方式定义日期常量类,这种定义方式会存在值相同的变量，混淆的几率较大，编译器也不会发出警告，因此这种方式在枚举出现后不再建议使用。
 * @author:NoteShare
 * @date:2019年3月25日 下午8:56:40
 */
public class DayDemo {
	public static final int MONDAY = 1;

	public static final int TUESDAY = 2;

	public static final int WEDNESDAY = 3;

	public static final int THURSDAY = 4;

	public static final int FRIDAY = 5;

	public static final int SATURDAY = 6;

	public static final int SUNDAY = 7;
}

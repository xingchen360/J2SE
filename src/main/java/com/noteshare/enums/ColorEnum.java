package com.noteshare.enums;

/**
 * 普通枚举
 * 枚举类型是类型安全的，如果出现类型问题，编译器会提示我们改进，这是它相对于常量类的优势，但务必要记住
 * 枚举类型的值必须是有限的，也就是说每个值都是可以枚举出来的。
 */
public enum ColorEnum {
	RED, GREEN, YELLOW, BLUE;
	public static void main(String[] args) {
		System.out.println(ColorEnum.RED.name().equals("RED"));
	}
}

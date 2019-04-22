package com.test;

import java.util.Collections;
import java.util.Set;

public class ObjectTest {
	public static void main(String[] args) {
		Set<String> set = Collections.singleton("rd1");
		for (String string : set) {
			System.out.println(string);
		}
	}
}
class Person{
	private String name;
	Person(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

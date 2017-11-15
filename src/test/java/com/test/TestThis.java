package com.test;

public class TestThis {
	private String name;

	public TestThis(String name) {
		this.name = name;
	}

	public void printA() {
		System.out.println(this);
	}

	public static void main(String[] args) {
		 TestThis testThis = new TestThis("展示");
		 testThis.printA();
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

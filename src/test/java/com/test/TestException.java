package com.test;

public class TestException {
	public static void main(String[] args) {
		TestException test1 = new TestException();
		try{
			test1.test2();
			System.out.println("2222222");
		}catch(Exception e){
			System.out.println("---" + e.getMessage());
		}
		System.out.println("333333");
	}
	
	public void test2(){
		System.out.println("test2-1");
		test1();
		System.out.println("test2-2");
	}
	
	public void test1(){
		System.out.println("1111");
		throw new RuntimeException("test222");
	}
}

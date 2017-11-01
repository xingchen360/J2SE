package com.noteshare.generic;

public class GenericDemo2 {
	public static void main(String[] args) {
		Demo<Demo2> demo = new Demo<Demo2>();
		Demo2 deme2Test = new Demo2();
		//此处不需要进行强制类型转换
		Demo2 demo2 = demo.getInstance(deme2Test);
		System.out.println(demo2);
	}
}

class Demo<T>{
	
	public Demo() {
		
	}
	
	public T getInstance(T t){
		return t;
	}
}

class Demo2{
	
}
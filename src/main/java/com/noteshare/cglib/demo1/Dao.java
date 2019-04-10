package com.noteshare.cglib.demo1;

public class Dao {
	public void select() {
		System.out.println("invoke select method !");
	}

	public void update() {
		System.out.println("invoke update method !");
	}
	
	public Object updateById(Integer id){
		System.out.println("invoke updateById(int id) method !");
		return null;
	}
}

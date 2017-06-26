package com.noteshare.compare;

import java.util.TreeSet;
import java.util.Comparator;

public class ComparatorTestCase {

	public static void main(String[] args) {
		
		TreeSet<People> set = new TreeSet<People>(new Comparator<People>(){
			@Override
            public int compare(People obj1, People obj2){
				return obj1.age - obj2.age;
			}
		});
		set.add(new People(1));
		set.add(new People(3));
		set.add(new People(8));
		set.add(new People(6));
		set.add(new People(1));
		
		System.out.println(set);
	}

}
class People {
	
	public int age;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		Person other = (Person) obj;
		if (age == other.age)
			return true;
		return false;
	}

	public People(int age) {
		super();
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "person age:"+this.age;
	}
	
}

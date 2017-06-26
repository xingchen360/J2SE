package com.noteshare.compare;

import java.util.TreeSet;

public class ComparableTestCase {

	public static void main(String[] args) {
		TreeSet<Person> set = new TreeSet<Person>();
		set.add(new Person(1));
		set.add(new Person(3));
		set.add(new Person(8));
		set.add(new Person(6));
		set.add(new Person(1));
		
		System.out.println(set);
	}

}
class Person implements Comparable<Person>{
	
	public int age;
	
	@Override
	public int compareTo(Person o) {
		return this.age - o.age;
	}
	
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

	public Person(int age) {
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
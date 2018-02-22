package com.test;

public class PrivateTest {
    public static void main(String[] args) {
        A a = new A();
        a.setAge(10);
        a.setName("张三");
        B b = new B();
        b.setAge(10);
        b.setName("B");
        System.out.println(b.getAge());
        System.out.println(b.getName());
    }
}

class A {
    private String name;
    public int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        A a = new A();
        a.setAge(10);
        a.setName("32");
        System.out.println(a.name);
        System.out.println(a.age);
    }
}

class B extends A {
    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.age);
    }
}

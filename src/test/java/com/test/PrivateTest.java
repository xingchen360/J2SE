/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

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

package com.noteshare.accessModifier;

public class PassByValueDemo {
    int in = 5;
	String str = new String("good");   
	char[] ch = { 'a', 'b', 'c',97 };   
	public void change(int in1, String str1, char[] ch1) {   
		in1 = 10;
        str1 = "test ok";   
        ch1[0] = 'g';   
    }   
	public static void main(String args[]){   
		PassByValueDemo ex = new PassByValueDemo();   
        ex.change(ex.in,ex.str, ex.ch);   
        System.out.print(ex.in+" and "+ex.str + " and ");   
        System.out.println(ex.ch);
        System.out.println(Integer.valueOf(ex.ch[3]));
        System.out.println(ex.ch[3]);
    }   
}

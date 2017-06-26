package com.noteshare.accessModifier;

public class PassByValueDemo2 {
    int num = 9;
    String str = "love";
	char[] ch = {'h','e','l','l','o'};
	void change(int num1,char[] ch1,String str1){
		num1 = 10;
		ch1[0] = 'm';
		ch1 = new char[]{'w','o','r','l','d'};
		ch1[0] = 'n';
		str1 = "you";
	}
	public static void main(String[] args) {
		PassByValueDemo2 t = new PassByValueDemo2();
		t.change(t.num,t.ch,t.str);
		System.out.print("num:"+t.num+" str:"+t.str+" ch:");
		System.out.println(t.ch);
	}
}

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


        System.out.println("================");
        char a = 65;
        System.out.println(a);
        System.out.println(Integer.valueOf(a));
        char aa = 'b';
        System.out.println(aa);
        System.out.println(Integer.valueOf(aa));
        System.out.println("-----------------");
        int b = 'a';
        System.out.println(b);
        System.out.println((char)b);

        System.out.println("++++++++++++++++++");
        int c = 99;
        char dd = 97 + 'a';
        int ii = 97 + 'a';
        char ddd = (char) (c + 'a');
        char cc = (char)(c + '0');
        System.out.println(dd);
        System.out.println(ddd);
        System.out.println(cc);
        System.out.println('0');
        System.out.println("+_+_+_+_+_+");
        System.out.println(Integer.valueOf('0'));
        System.out.println();

        short s = 89;
        int i = 90;
        i = s;
        s = (short) i;
        short ss = 10000;


    }
}

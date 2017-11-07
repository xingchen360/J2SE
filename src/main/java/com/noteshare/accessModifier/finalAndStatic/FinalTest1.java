package com.noteshare.accessModifier.finalAndStatic;


/**
* @Title:
* @author  NoteShare
* @since   JDK1.8
* @history 2017年11月7日
*/
public class FinalTest1 {
    
    public static void test1(){
        System.out.println("FinalTest1-test1-static()");
    }
    
    public static final void test2(){
        System.out.println("FinalTest1-test2-static-final");
    }
    
    public void test3(){
        System.out.println("FinalTest1-test3");
    }
}

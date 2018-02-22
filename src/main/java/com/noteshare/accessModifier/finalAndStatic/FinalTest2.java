package com.noteshare.accessModifier.finalAndStatic;


/**
* @Title: 考点：静态方法能不能被继承，终态方法能不能被继承，静态方法能不能被重写，终态方法能不能被重写
* @author  NoteShare
* @since   JDK1.8
* @history 2017年11月7日
*/
public class FinalTest2 extends FinalTest1{

    public void test3() {
        System.out.println("FinalTest2-test3");
    }
    /**
     * @Title      	: test1 
     * @Description	: 静态方法可以被继承，但是不能被重写，如果子类中含有同名静态方法，只是会隐藏父类的同名方法
     * @author     	: NoteShare
     * Create Date 	: 2017年11月7日 下午4:28:43
     * @throws
     */
    public static void test1(){
        System.out.println("FinalTest2-test1");
    }
    /**
     * @Title      	: test2 
     * @Description	: Cannot override the final method from FinalTest1
     * @author     	: NoteShare
     * Create Date 	: 2017年11月7日 下午4:22:13
     * @throws
     */
   /* public static final void test2(){
        System.out.println("FinalTest2-test1");
    }*/
    
    @SuppressWarnings("static-access")
	public static void main(String[] args) {
        FinalTest1.test1();
        FinalTest1.test2();
        FinalTest1 test1 = new FinalTest1();
        test1.test1();
        test1.test2();
        test1.test3();
        System.out.println("==============================");
        FinalTest2.test1();
        FinalTest2.test2();
        FinalTest2 test2 = new FinalTest2();
        test2.test1();
        test2.test2();
        test2.test3();
        System.out.println("---------------");
        FinalTest1 test3 = new FinalTest2();
        test3.test1();
        test3.test2();
        test3.test3();
    }
}

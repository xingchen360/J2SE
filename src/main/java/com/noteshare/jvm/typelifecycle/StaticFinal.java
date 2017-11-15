package com.noteshare.jvm.typelifecycle;

/**
* @Title:  如果一个字段既是静态（static）的又是最终的（final）的，并且使用一个编译时常量表达式初始化，
*          使用这个字段，就不是对声明该字段的类的主动使用。Java编译器把这样的字段解析成对常量的本地拷贝
*          （该常量存在于引用者的常量池中或者字节码流中，或者二者都有）。下面给出这种静态final字段特殊处理的例子：
* @author  NoteShare
* @since   JDK1.8
* @history 2017年11月15日
*/
public class StaticFinal{
    /**
     * 
     * @Title      	: main 
     * @Description	: 
     * 如果一个字段既是静态（static）的又是最终的（final）的，并且使用一个编译时常量表达式初始化，
     * 使用这个字段，就不是对声明该字段的类的主动使用。Java编译器把这样的字段解析成对常量的本地拷贝
     *（该常量存在于引用者的常量池中或者字节码流中，或者二者都有）。下面给出这种静态final字段特殊处理的例子：
     *  输出结果为：
     *  StaticFinal was initailization
        Grrrr
        Woof,woof,world
     * @author     	: NoteShare
     * Create Date 	: 2017年11月15日 上午9:42:32
     * @param args
     * @throws
     */
    public static void main(String[] args) {
        System.out.println(Angry.greeting);
        System.out.println(Dog.greeting);
    }
    static {
        System.out.println("StaticFinal was initailization");
    }
}

interface Angry {

    String greeting = "Grrrr";

    int angerLevel = Dog.getAngerLevel();

    /**
     * @Title      	: main 
     * @Description	: 输出：
     * Dog was initialized.
       Angry was initialized
     * @author     	: NoteShare
     * Create Date 	: 2017年11月15日 上午9:44:10
     * @param args
     * @throws
     */
    public static void main(String[] args) {

    }

    static void test() {

    }
}

class Dog {
    static final String greeting = "Woof,woof,world";
    static {
        System.out.println("Dog was initialized.");
    }

    static int getAngerLevel() {
        System.out.println("Angry was initialized");
        return 1;
    }
}

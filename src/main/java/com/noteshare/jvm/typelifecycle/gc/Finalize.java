package com.noteshare.jvm.typelifecycle.gc;

/**
* @Title:  1.Java虚拟机实现必须具有某种自动堆存储管理策略-大部分是采用垃圾收集器。程序可以明确或隐含地为对象分配内存，单不能明确
* 释放内存。但一个对象不再为程序所引用了，虚拟机必须回收（垃圾收集）那部分内存。实现可以决定何时应垃圾收集不再被引用的对象-或者决定是否根本
* 不收集它们。并没有要求Java虚拟机实现一定要释放不再被引用的对象所占据的内存。
* 如果类声明了一个名为finalize()的返回void的方法，垃圾收集器 会在释放这个实例所占据的内存之前执行这个方法（被称为终结方法）一次。
* 2.因为一个终结方法是一个普通的Java方法，它可以直接被程序所调用。这样的直接调用不会影响垃圾收集器的自动调用过程。垃圾收集器（最多）只会调用一个对象的终结
* 方法一次，在对象变成不再被引用的之后的某个时候，在占据的对象被重用之前。如果终结方法代码执行后，对象重新被引用（复活了），随后再次变得不被引用，垃圾收集器不会第二次
* 调用终结方法。
* 3.垃圾收集器自动调用finalize方法抛出的任何异常都将被忽略。垃圾收集器可以用任意顺序调用finalize方法，使用任意线程，甚至并行使用多线程。
* @author  NoteShare
* @since   JDK1.8
* @history 2017年11月16日
*/
public class Finalize {
    public static void main(String[] args) {
        Final obj = new Final();
        obj = null;
        //obj.finalize();
    }
}

class Final{
    protected void finalize() {
        System.out.println("A Finalize Object was finalize.");
    }
}

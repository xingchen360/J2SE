package com.noteshare.jvm.typelifecycle;

/**
* @Title:  被动使用示例
*          主动使用和被动使用区别请查看itnoteshare.com专题中关于虚拟机《类型的声明周期》章节描述
* @author  NoteShare
* @since   JDK1.8
* @history 2017年11月15日
*/
public class ActiveAndPassiveUse {
    public static void main(String[] args) {
        /** 第一种情况测试：
         * int hours = NewBornBaby.hoursOfCrying;
         * 输出结果：初始化子类时先初始化父类
         * ActiveAndPassiveUse was initialization
           NewParent was initialization
           NewBornBaby was initialization
           7
         */
        /**
         * 第二种情况测试：
         * 被动调用没有初始化自己，直接调用了父类的静态变量，也只初始化了父类,子类根本就没有被装载
         * ActiveAndPassiveUse was initialization
           NewParent was initialization
           1
         */
        int hours = NewBornBaby.hoursOfSleep;
        System.out.println(hours);
    }

    static {
        System.out.println("ActiveAndPassiveUse was initialization");
    }
}

class NewParent {
    static int hoursOfSleep = (int) (Math.random() * 3.0);
    static {
        System.out.println("NewParent was initialization");
    }
}

class NewBornBaby extends NewParent {
    static int hoursOfCrying = 6 + (int) (Math.random() * 2.0);
    static {
        System.out.println("NewBornBaby was initialization");
    }
}

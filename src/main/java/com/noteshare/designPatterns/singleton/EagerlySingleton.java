package com.noteshare.designPatterns.singleton;


/**
* @Title:  饿汉模式：提前new出来实例了，并不是在第一次调用get方法时才实例化，没有进行延迟加载。
* 此处为了更好了的理解你需要去了解下类的饿汉和懒汉加载方式
* @author  NoteShare
* @since   JDK1.8
* @history 2017年10月24日
*/
public class EagerlySingleton {
    //类加载时进行对象实例化
    private static EagerlySingleton eagerlySingleton = new EagerlySingleton();
    /**
     * 私有的构造方法，不让在外部进行类的实例化
     */
    private EagerlySingleton(){
        
    }
    /**
     * @Title      	: getInstance 
     * @Description	: 开放对外的获取实例的方法
     * @author     	: NoteShare
     * Create Date 	: 2017年10月24日 下午4:09:19
     * @return
     * @throws
     */
    public static EagerlySingleton getInstance(){
        return eagerlySingleton;
    }
}

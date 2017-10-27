package com.noteshare.designPatterns.singleton;


/**
* @Title:http://blog.csdn.net/glory1234work2115/article/details/50814419
                           
* @author  NoteShare
* @since   JDK1.8
* @history 2017年10月24日
*/
public class LazySingleton {
    
    private static LazySingleton lazySingleton;
    
    /**
     * 定义私有的构造方法，防止外部进行创建对象
     */
    private LazySingleton(){
        
    }
    /**
     * @Title      	: getInstance 
     * @Description	: 定义向外公布对象的接口
     *                缺点：多线程环境下无法保证单例效果，会多次执行 instance=new Singleton()，需要考虑到多线程
     * @author     	: NoteShare
     * Create Date 	: 2017年10月25日 下午7:46:19
     * @return
     * @throws
     */
    public static LazySingleton getInstance(){
        if(null == lazySingleton){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
    /**
     * @Title      	: getSyncInstance 
     * @Description	: 同步的获取对象的方法
     *                同步方法方式：性能不高，同步范围太大，在实例化instacne后，获取实例仍然是同步的，效率太低，需要缩小同步的范围。
     * @author     	: NoteShare
     * Create Date 	: 2017年10月25日 下午7:50:52
     * @return
     * @throws
     */
    public static synchronized LazySingleton getSyncInstance(){
        if(null == lazySingleton){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
    /**
     * @Title      	: getSyncBlockInstance 
     * @Description	: 同步的获取对象的方法
     *                缺点：缩小同步范围，来提高性能，但是仍然存在多次执行instance=new Singletom()的可能，由此引出double check
     * @author     	: NoteShare
     * Create Date 	: 2017年10月25日 下午7:53:45
     * @return
     * @throws
     */
    public static LazySingleton getSyncBlockInstance(){
        if(null == lazySingleton){
            synchronized (LazySingleton.class) {
                lazySingleton = new LazySingleton();
            }
        }
        return lazySingleton;
    }
    /**
     * @Title      	: getInstanceByDoubleCheck 
     * @Description	: 缺点：避免的上面方式的明显缺点，但是java内存模型（jmm）并不限制处理器重排序，在执行instance=new Singleton()；时，并不是原子语句，实际是包括了下面三大步骤：
     *                1.为对象分配内存
                      2.初始化实例对象
                      3.把引用instance指向分配的内存空间
                                                                  这个三个步骤并不能保证按序执行，处理器会进行指令重排序优化，存在这样的情况：
                                                                  优化重排后执行顺序为：1,3,2, 这样在线程1执行到3时，instance已经不为null了，线程2此时判断instance!=null，则直接返回instance引用，但现在实例对象还没有初始化完毕，此时线程2使用instance可能会造成程序崩溃。
                                                                  现在要解决的问题就是怎样限制处理器进行指令优化重排。
                      解决以上问题请查看VolatileSingleton实现。
     * @author     	: NoteShare
     * Create Date 	: 2017年10月25日 下午7:58:47
     * @return
     * @throws
     */
    public static LazySingleton getInstanceByDoubleCheck(){
        if(null == lazySingleton){
            synchronized (LazySingleton.class) {
                if(null == lazySingleton){
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}

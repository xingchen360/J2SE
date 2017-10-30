package com.noteshare.designPatterns.singleton;

/**
* @Title:  在JDK1.5之后，使用volatile关键字修饰instance就可以实现正确的double check单例模式了
* @author  NoteShare
* @since   JDK1.8
* @history 2017年10月27日
*/
public class VolatileDoubleCheckSingleton {
    
    private static volatile VolatileDoubleCheckSingleton volatileDoubleCheckSingleton;
    
    private VolatileDoubleCheckSingleton(){
        
    }
    /**
     * @Title      	: getInstance 
     * @Description	: 这里就要介绍一下volatile的作用了：
                      1.保证可见性
                                                                    可以保证在多线程环境下，变量的修改可见性。每个线程都会在工作内存（类似于寄存器和高速缓存），实例对象都存放在主内存中，
                                                                    在每个线程要使用的时候把主内存中的内容拷贝到线程的工作内存中。使用volatile关键字修饰后的变量，保证每次修改了变量需要立即写回主内存中，
                                                                    同时通知所有的该对变量的缓存失效，保证缓存一致性，其他线程需要使用该共享变量时就要重新从住内存中获取最新的内容拷贝到工作内存中供处理器使用。
                                                                    这样就可以保证变量修改的可见性了。但volatile不能保证原子性，比如++操作。
                      2.提供内存屏障
                       volatile关键字能够通过提供内存屏障，来保证某些指令顺序处理器不能够优化重排，编译器在生成字节码时，会在指令序列中插入内存屏障来禁止特定类型的处理器重排序。
                                                                    下面是保守策略插入内存屏障：
                                                                    在每个volatile写操作的前面插入一个StoreStore屏障。
                                                                    在每个volatile写操作的后面插入一个StoreLoad屏障。
                                                                    在每个volatile读操作的前面插入一个LoadLoad屏障。
                                                                    在每个volatile读操作的后面插入一个LoadLoad屏障。
                                                                    这样可以保证在volatile关键字修饰的变量的赋值和读取操作前后两边的大的顺序不会改变，在内存屏障前面的顺序可以交换，屏障后面的也可以换序，但是不能跨越内存屏障重排执行顺序。
                                                                    好了，现在来看上面的单例模式，这样就可以保证3步骤（instance赋值操作）是保持最后一步完成，这样就不会出现instance在对象没有初始化时就不为null的情况了。这样也就实现了正确的单例模式了。
     * @author     	: NoteShare
     * Create Date 	: 2017年10月27日 下午5:23:13
     * @return
     * @throws
     */
    public static VolatileDoubleCheckSingleton getInstance(){
        if(null != volatileDoubleCheckSingleton){
            synchronized (VolatileDoubleCheckSingleton.class) {
                if(null != volatileDoubleCheckSingleton){
                    volatileDoubleCheckSingleton = new VolatileDoubleCheckSingleton();
                }
            }
        }
        return volatileDoubleCheckSingleton;
    }
}

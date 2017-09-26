package com.noteshare.set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

/**
* @Title:  ConcurrentModificationException异常出现的原因
* @author  NoteShare
* @since   JDK1.6
* @history 2017年9月26日 
*/
@SuppressWarnings("boxing")
public class ConcurrentModificationTest {
    
    /**
     *  输出结果
     *  ====
        ----
        ++++
        ====
        Exception in thread "main" java.util.ConcurrentModificationException
        at java.util.ArrayList$Itr.checkForComodification(Unknown Source)
        at java.util.ArrayList$Itr.next(Unknown Source)
        at com.noteshare.set.ConcurrentModificationTest.main(ConcurrentModificationTest.java:20)
     */
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println("====");
            Integer integer = iterator.next();
            System.out.println("-----");
            if(integer==2){
                System.out.println("++++++");
                list.remove(integer);
            }
        }
    }
    /**
     * 使用并发容器CopyOnWriteArrayList代替ArrayList和Vector
     */
    @Test
    public void test5(){
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println("====");
            Integer integer = iterator.next();
            System.out.println("-----");
            if(integer==2){
                System.out.println("++++++");
                list.remove(integer);
            }
        }
    }
    
    /**
     * 以下方法运行正常
     * ====
       -----
       ++++++
     */
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println("====");
            Integer integer = iterator.next();
            System.out.println("-----");
            if(integer==2){
                System.out.println("++++++");
                iterator.remove();
            }
        }
    }
    
    /**
     *  输出：
        ====
        integer1:2
        false
        true
     *  Integer integer2 = iterator.next();此行代码异常
     *  java.util.ConcurrentModificationException
     *  at java.util.ArrayList$Itr.checkForComodification(Unknown Source)
     *  at java.util.ArrayList$Itr.next(Unknown Source)
     *  at com.noteshare.set.ConcurrentModificationTest.test3(ConcurrentModificationTest.java:76)
     */
    @Test
    public void test3(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println("====");
            Integer integer1 = iterator.next();
            System.out.println("integer1:" + integer1);
            System.out.println(iterator.hasNext());
            list.add(4);
            System.out.println(iterator.hasNext());
            Integer integer2 = iterator.next();//此处异常
            System.out.println("-----integer2:" + integer2);
        }
    }
    
    /**
     *  输出：
     *  ====
        integer1:2
        false
        false
     * java.util.NoSuchElementException
        at java.util.concurrent.CopyOnWriteArrayList$COWIterator.next(Unknown Source)
        at com.noteshare.set.ConcurrentModificationTest.test4(ConcurrentModificationTest.java:129)
     */
    @Test
    public void test4(){
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println("====");
            Integer integer1 = iterator.next();
            System.out.println("integer1:" + integer1);
            System.out.println(iterator.hasNext());
            list.add(4);
            System.out.println(iterator.hasNext());
            Integer integer2 = iterator.next();//此处异常
            System.out.println("-----integer2:" + integer2);
        }
    }
}

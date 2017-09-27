package com.noteshare.set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
        //判断是否需要对数组扩容
        //modCount++;
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        //源码Itr：cursor != size;判断当前光标是否到达结尾
        while(iterator.hasNext()){
            System.out.println("====");
            //先判断modCount != expectedModCount 成立则异常ConcurrentModificationException
            //i = cursor
            //i >= size NoSuchElementException
            //i >= elementData.length  ConcurrentModificationException
            // 否则cursor后移一位，指向下一个元素
            Integer integer = iterator.next();
            System.out.println("-----");
            if(integer==2){
                System.out.println("++++++");
                // modCount++;
                // 然后把后面的元素往前移动一位，也就是数据拷贝if (numMoved > 0)System.arraycopy(elementData, index+1, elementData, index,numMoved);
                // 将最后一个元素置为空
                // 注意此时没有改变Itr中cursor的值，cursor为1而此时size为0故cursor!=size成立
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
        //加锁
        //对数组扩容，赋值
        //解锁
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        //cursor < snapshot.length
        while(iterator.hasNext()){
            System.out.println("====");
            Integer integer = iterator.next();
            System.out.println("-----");
            if(integer==2){
                System.out.println("++++++");
                list.remove(integer);
                System.out.println(list.size());
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
                //有做expectedModCount = modCount;
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
            //if (! hasNext()) NoSuchElementException否则直接取值cursor后移一位
            Integer integer1 = iterator.next();
            System.out.println("integer1:" + integer1);
            System.out.println(iterator.hasNext());
            //单纯的数组扩容和snapshot无太大关系，所以snapshot的长度应该是没改变
            list.add(4);
            //cursor < snapshot.length  ：cursor改变了而snapshot未改变所以异常了 
            System.out.println(iterator.hasNext());
            Integer integer2 = iterator.next();//此处异常---NoSuchElementException
            System.out.println("-----integer2:" + integer2);
        }
    }
    public static void main(String[] args) {
        testList1();
    }
    
    /**
     * 分析面试题中的一个坑,答出来了的应java底层技术还是比较扎实
     * 以下代码是否有异常或报错，如果有请做原因分析
     * 答案有异常：在for循环处报异常java.util.ConcurrentModificationException
     * 原因分析：
     * 此代码中for循环()内部相当于做了Iterator的hasNext()判断和调用了next()方法
     * hasNext会判断指针cursor指向的元素索引是否等于集合大小size，开始cursor为0；
     * 而next取值前会进行modCount和expectedModCount的相等判断，不相等则报异常ConcurrentModificationException
     * 开始modCount和expectedModCount均为0
     * 第一次循环时由于调用list.remove会改变modCount的值  +1了导致第二次循环时取next的时候报异常
     */
    private static void testList1(){
        List list = new ArrayList();
        list.add("1");
        list.add("3");
        list.add("4");
        for(Object object : list) {
            if("1".equals(object)){
                list.remove(object);
            }
        }
    }
    /**
     * 分析面试题中的一个坑,答出来了的应java底层技术还是比较扎实
     * 以下代码是否有异常或报错，如果有请做原因分析
     * 答案有异常：无异常无报错，但是写法有问题
     * 原因分析：
     * 此代码中for循环()内部相当于做了Iterator的hasNext()判断和调用了next()方法
     * hasNext会判断指针cursor指向的元素索引是否等于集合大小size，开始cursor为0；
     * 开始modCount和expectedModCount均为0
     * 第二次循环时由于调用list.remove会改变modCount的值  +1，此时由于list的长度变为2了，而此时的cursor也正好指向2，当要进行第三次for
     * 循环时由于先判断hasNext是否成立，此时由于cursor==size，所以不会执行next取值，故循环执行了两次后结束，并不会触发异常。
     */
    private static void testList2(){
        List list = new ArrayList();
        list.add("1");
        list.add("3");
        list.add("4");
        for(Object object : list) {
            if("3".equals(object)){
                list.remove(object);
            }
        }
    }
    /**
     * 以下代码是否有异常或问题，如果没问题输出结果是什么
     * 答案：无报错异常，但是写法有问题
     * 输出结果为：
     * size：3
     * 1
     * ==
     * size：2
     * 4
     */
    private static void testList3(){
        List list = new ArrayList();
        list.add("1");
        list.add("3");
        list.add("4");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("size:" + list.size());
            Object obj = list.get(i);
            System.out.println(obj);
            if("1".equals(obj)){
                System.out.println("==");
                list.remove(obj);
            }
        }
    }
    
    
}

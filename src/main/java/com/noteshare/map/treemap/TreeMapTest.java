package com.noteshare.map.treemap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
* @Title:  测试Treeset的有序遍历和分析其底层代码
* @author  NoteShare
* @since   JDK1.8
* @history 2017年11月13日
*/
public class TreeMapTest {
    public static void main(String[] args) {
        test1();
    }
    //注意key不要放太简单的数据不然测试一直是排序的如a、b、c....，用较复杂的数据做测试
    public static void test1(){
        //TreeMap<String,Integer> map = new TreeMap<String, Integer>(Collections.reverseOrder());
        //TreeMap<String,String> map = new TreeMap<String, String>(Collections.reverseOrder());
        TreeMap<String,String> map = new TreeMap<String, String>();
        map.put("i32ewe32332", "i");
        map.put("fdasfdsa", "a");
        map.put("cdfas", "c");
        map.put("bcvc", "b");
        map.put("de23", "d");
        map.put("efdas", "ee");
        map.put("fxcv", "ff");
        map.put("gfdsa", "g");
        map.put("hsfsd", "hh");
        Set<String> set = map.keySet();
        System.out.println(set.getClass());
        System.out.println(Arrays.toString(set.toArray()));
        System.out.println(set instanceof HashSet);
        System.out.println(set instanceof TreeSet);
        System.out.println(set instanceof Set);
        Iterator<String> iter = set.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + map.get(key));
        }
    }
}

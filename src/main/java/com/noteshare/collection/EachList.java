package com.noteshare.collection;

import java.util.ArrayList;
import java.util.List;

public class EachList {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("张三");
		list.add("李四");
		list.add("王五");
		list.add("小二");
		for(int i = 0;i < list.size();i++){
			System.out.println(list.get(i));
			list.remove(i);
		}
		/*Exception in thread "main" java.util.ConcurrentModificationException
		at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
		at java.util.ArrayList$Itr.next(ArrayList.java:851)
		at com.somnus.ListTest.main(ListTest.java:13)*/
		/*for (String name : list) {
			System.out.println(name);
			list.remove(name);
		}*/
	}
}

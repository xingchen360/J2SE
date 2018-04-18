package com.noteshare.jdk8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JButton;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @Title:Lambda.java
 * @Description:Java 8的一个大亮点是引入Lambda表达式，使用它设计的代码会更加简洁，
 * 当开发者在编写Lambda表达式时，也会随之被编译成一个函数式接口
 * @author:NoteShare
 * @date:2018年4月13日 上午11:40:30
 */
public class Lambda {

	/**
	 * 数组排序的老的实现方式
	 */
	@Ignore
	@Test
	public void arraysSortOld() {
		String[] arr = { "a", "c", "b" };
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * Lambda表达式实现数组排序写法 
	 */
	@Test
	@Ignore
	public void arraySortLamda() {
		String[] arr = { "a", "c", "b" };
		Arrays.sort(arr, (a, b) -> {
			return a.compareTo(b);
		});
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * Runnable的两种实现方式
	 */
	@Test
	@Ignore
	public void runnableImp() {
		// 普通实现方式
		Runnable runnable1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("Running without Lambda");
			}
		};
		runnable1.run();
		// 用lambda表达式实现Runnable
		Runnable runnable2 = () -> {
			System.out.println("Running from Lambda");
		};
		runnable2.run();
	}

	/**
	 * 使用Java 8 lambda表达式进行事件处理
	 */
	@Test
	@Ignore
	public void swingLambda() {
		// 老的实现方式
		JButton button1 = new JButton("测试");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Event handling without lambda expression is boring");
			}
		});

		// jdk lambda的实现方式
		button1.addActionListener((e) -> {
			System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
		});
	}

	/**
	 * 使用lambda表达式对列表进行迭代
	 */
	@SuppressWarnings("unused")
	@Test
	@Ignore
	public void forLambda() {
		// 老的遍历方式
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		for (String feature : features) {
			System.out.println(feature);
		}
		System.out.println("=================================");
		// Java 8之后：
		features.forEach(feature -> {
			feature += "a";
			System.out.println(feature);
		});
		System.out.println("--------------------------------");
		features.forEach(feature -> System.out.println(feature));
		System.out.println("++++++++++++++++++++++++++++++++");
		// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
		// 看起来像C++的作用域解析运算符
		// 展示了如何在Java 8中使用方法引用（method reference）。你可以看到C++里面的双冒号、范围解析操作符现在在Java
		// 8中用来表示方法引用
		features.forEach(System.out::println);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Lambda lambda = new Lambda();
		lambda: runnableImp();
	}

	/**
	 * 除了在语言层面支持函数式编程风格，Java 8也添加了一个包，叫做 java.util.function。
	 * 它包含了很多类，用来支持Java的函数式编程。其中一个便是Predicate，
	 * 使用 java.util.function.Predicate 函数式接口以及lambda表达式，
	 * 可以向API方法添加逻辑，用更少的代码支持更多的动态行为。下面是Java 8 Predicate 的例子，
	 * 展示了过滤集合数据的多种常用方法。Predicate接口非常适用于做过滤。
	 */
	@Test
	public void predicate() {
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		System.out.println("Languages which starts with J :");
		filter(languages, (str) -> str.startsWith("J"));
		System.out.println("Languages which ends with a ");
		filter(languages, (str) -> str.endsWith("a"));
		System.out.println("Print all languages :");
		filter(languages, (str) -> true);
		System.out.println("Print no language : ");
		filter(languages, (str) -> false);
		System.out.println("Print language whose length greater than 4:");
		filter(languages, (str) -> str.length() > 4);
	}
	
	/**
	 * 测试Predicate的使用
	 * @param names
	 * @param condition
	 */
	private void filter(List<String> names, Predicate<String> condition) {
		/*for (String name : names) {
			if (condition.test(name)) {
				System.out.println(name);
			}
		}*/
		// 更好的写法
		//可以看到，Stream API的过滤方法也接受一个Predicate，这意味着可以将我们定制的 filter() 
		//方法替换成写在里面的内联代码，这就是lambda表达式的魔力。
		//另外，Predicate接口也允许进行多重条件的测试，下个例子将要讲到
		names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
			System.out.println(name);
		});
	}
}

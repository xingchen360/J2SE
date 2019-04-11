package com.noteshare.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/** 
 * @Description:	
 * 作用分类：
 * 	①编写文档：通过代码里标识的元数据生成文档【生成文档doc文档】
	②代码分析：通过代码里标识的元数据对代码进行分析【使用反射】
	③编译检查：通过代码里标识的元数据让编译器能够实现基本的编译检查【Override】
	JDK1.5开始引入的新特性
 * 注解方法不能有参数
 * 注解方法的返回类型局限于原始类型，字符串，枚举，注解，或以上类型构成的数组
 * 注解方法可以包含默认值
 * 注解可以包含与其绑定的元注解，元注解为注解提供信息，有四种元注解类型：@Documented 、@Target、@Inherited、@Retention 
 */
//让它定制文档化功能--注解是否将包含在JavaDoc中
//使用此注解时必须设置RetentionPolicy为RUNTIME
@Documented
//限制注解使用范围@Target(ElementType.METHOD)
@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
//标注继承：让它允许继承，可作用到子类
@Inherited
//注解保持性策略
//编译器的处理有三种策略：
//SOURCE:此类型会被编译器丢弃;
//CLASS:此类型注解会保留在class文件中，但JVM会忽略它;
//RUNTIME:此类型注解会保留在class文件中，JVM会读取它。
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
	//定义一个枚举类型，然后将参数设置为该枚举类型，并赋予默认值
	enum FontColor {
        BLUE,
        RED,
        GREEN
    }
	int[] value() default {1,2};
    String author() default "NoteShare";
    String date();
    int revision() default 1;
    String comments();
    FontColor fontColor() default FontColor.RED;
}

package com.noteshare.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/** 
 * @Title: AnnotationParsing.java 
 * @Description: 注解代码分析功能测试
 * @author NoteShare
 * @date 2015年6月12日 上午9:56:01 
 * @version V1.0 
 */
public class AnnotationParsing {
    public static void main(String[] args) {
        
        for (Method method : AnnotationDemo.class.getMethods()) {
            // checks if MethodInfo annotation is present for the method
            if (method.isAnnotationPresent(MethodInfo.class)) {
            	System.out.println("===================");
                try {
                    // iterates all the annotations available in the method
                    for (Annotation anno : method.getDeclaredAnnotations()) {
                        System.out.println("Annotation in Method \n'"+ method + "' : " + anno);
                    }
                    MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
                    if (methodInfo.revision() == 1) {
                        System.out.println("Method with revision no 1 = \n"+ method);
                        System.out.println("comments:"+methodInfo.comments());
                    }
                    System.out.println("*******************************************");
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

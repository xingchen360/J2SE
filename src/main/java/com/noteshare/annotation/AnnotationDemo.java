package com.noteshare.annotation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.noteshare.annotation.MethodInfo.FontColor;

/** 
 * @Title: AnnotationDemo.java 
 * @Description: 自定义注解使用测试
 * @author NoteShare
 * @version V1.0 
 */
public class AnnotationDemo {
    
    @Override
    @MethodInfo(author = "NoteShare", comments = "Main method", date = "June 12 2015", revision = 1,fontColor=FontColor.BLUE)
    public String toString() {
        return "Overriden toString method";
    }
 
    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "June 12 2015",value={1,2,3})
    public static void outdatedMethod() {
        System.out.println("outdated method, don't use it.");
    }
 
    @MethodInfo(author = "NoteShare", comments = "Main method", date = "June 12 2015", revision = 10)
    public static void genericsTest() throws FileNotFoundException {
        List<String> l = new ArrayList<String>();
        l.add("abc");
        outdatedMethod();
    }
}

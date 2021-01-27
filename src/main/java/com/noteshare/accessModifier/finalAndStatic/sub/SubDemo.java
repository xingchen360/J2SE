package com.noteshare.accessModifier.finalAndStatic.sub;

import com.noteshare.accessModifier.Modifier;
import com.noteshare.swing.selectfiles.Main;

/**
 * @title: SubDemo
 * @description:
 * @author: haixin
 * @date: 2021/1/23
 * @version: V1.0
 */
public class SubDemo extends Modifier{
    public static void main(String[] args) {
        Modifier m = new Modifier();
        m.a();
        SubDemo s = new SubDemo();
        s.b();
        s.a();
    }
}

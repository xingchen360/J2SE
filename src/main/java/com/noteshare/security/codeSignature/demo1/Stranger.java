package com.noteshare.security.codeSignature.demo1;

import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.poi.ss.formula.functions.T;

/**
* @Title:  访问控制的栈检查机制
* @author  NoteShare
* @since   JDK1.8
* @history 2017年10月31日
*/
public class Stranger implements Doer {

    
    private Doer next;
    
    private boolean direct;
    
    public Stranger(Doer next,boolean direct) {
        this.next = next;
        this.direct = direct;
    }
    
    @Override
    public void doYourThing() {
        if(direct){
            next.doYourThing();
        }else{
            AccessController.doPrivileged(new PrivilegedAction<T>() {
                @Override
                public T run() {
                    next.doYourThing();
                    return null;
                }
            });
        }
    }
}

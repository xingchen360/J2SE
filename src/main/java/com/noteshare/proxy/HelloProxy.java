package com.noteshare.proxy;

/** 
 * @Title: HelloProxy.java 
 * @date 2015年6月3日 下午1:26:43 
 * @version V1.0 
 */
public class HelloProxy  implements Hello{
    private HelloImpl helloImpl;
    public HelloProxy(){}
    
    public HelloProxy(HelloImpl helloImpl){
        this.helloImpl = helloImpl;
    }

    @Override
    public void say(String name) {
        before();
        helloImpl.say(name);
        after();
    }
 
    private void before() {
        System.out.println("Before");
    }
 
    private void after() {
        System.out.println("After");
    }
}
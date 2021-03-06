/******************************************************************************
 * ThreadLocal类的使用说明
 * ThreadLocal：我们一般称之为线程本地变量，也有些地方叫做线程本地存储
 * ThreadLocal会为变量在每个线程中创建一个副本，那么每个线程可以访问自己内部的副本变量，线程之间不相互影响，这样可以处理线程安全问题和避免我们使用同步方法的方式造成的性能
 * 问题，例如我们如果为了创建连接的安全性，把连接的创建放到每个使用的方法内部，这样虽然不存在线程安全问题，但是每次使用方法都创建一次明显是会影响性能的。
 * 但是要注意，虽然ThreadLocal能够解决上面说的问题，但是由于在每个线程中都创建了副本，所以要考虑它对资源的消耗，比如内存的占用会比不使用ThreadLocal要大
*****************************************************************************/
package com.noteshare.thread.threadlocal;
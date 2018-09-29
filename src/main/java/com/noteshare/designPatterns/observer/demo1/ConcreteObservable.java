package com.noteshare.designPatterns.observer.demo1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 目标被观察者
 */
public class ConcreteObservable extends Observable {
	private static ConcreteObservable instance = null;
	private ConcreteObservable(){};

	public static synchronized ConcreteObservable getInstance(){
		if(instance == null){
			instance=new ConcreteObservable();
		}
		return instance;
	}

	@Override
	public <T> void notifyObserver(T t, Object... objs) {
		if (t == null)
			throw new NullPointerException();
		this.notifyObserver(t.getClass(), objs);
	}

	@Override
	public void notifyObservers(Object... objs) {
		for (Class<?> cls : obserList) {
			this.notifyObserver(cls, objs);
		}
	}

	// 通过java反射机制实现调用
	@Override
	public void notifyObserver(Class<?> cls, Object... objs) {
		if (cls == null)
			throw new NullPointerException();
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals("update")) {
				try {
					method.invoke(cls, objs);
					break;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

package com.noteshare.designPatterns.observer.demo1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 被观察者（一个抽象类，方便扩展）
 */
public abstract class Observable {
	public final ArrayList<Class<?>> obserList = new ArrayList<Class<?>>();

	/**
	 * AttachObserver（通过实例注册观察者）
	 * <b>Notice:</b>obcan'tbenull,oritwillthrowNullPointerException
	 **/
	public <T> void registerObserver(T ob) {
		if (ob == null)
			throw new NullPointerException();
		this.registerObserver(ob.getClass());
	}

	/**
	 * AttachObserver（通过Class注册观察者）
	 * 
	 * @paramcls
	 */
	public void registerObserver(Class<?> cls) {
		if (cls == null)
			throw new NullPointerException();
		synchronized (obserList) {
			if (!obserList.contains(cls)) {
				obserList.add(cls);
			}
		}
	}

	/**UnattachObserver（注销观察者）
    *<b>Notice:</b>
    *<b>ItreverseswithattachObserver()method</b>
    **/
    public<T>void unRegisterObserver(T ob){
        if(ob==null) throw new NullPointerException();
        this.unRegisterObserver(ob.getClass());
    }

	/**
	 * UnattachObserver（注销观察者，有时候在未获取到实例使用） <b>Notice:</b>
	 * <b>ItreverseswithattachObserver()method</b>
	 **/
	public void unRegisterObserver(Class<?> cls) {
		if (cls == null)
			throw new NullPointerException();
		synchronized (obserList) {
			Iterator<Class<?>> iterator = obserList.iterator();
			while (iterator.hasNext()) {
				if (iterator.next().getName().equals(cls.getName())) {
					iterator.remove();
					break;
				}
			}
		}
	}

	/** detachallobservers */
	public void unRegisterAll() {
		synchronized (obserList) {
			obserList.clear();
		}
	}

	/** Ruturnthesizeofobservers */
	public int countObservers() {
		synchronized (obserList) {
			return obserList.size();
		}
	}

	/**
	 * notify all observer（通知所有观察者，在子类中实现）
	 * 
	 * @paramobjs
	 */
	public abstract void notifyObservers(Object... objs);

	/**
	 * notify one certain observer（通知某一个确定的观察者）
	 * 
	 * @paramcls
	 * @paramobjs
	 */
	public abstract void notifyObserver(Class<?> cls, Object... objs);

	/**
	 * notifyonecertainobserver
	 * 
	 * @paramcls
	 * @paramobjs
	 */
	public abstract <T> void notifyObserver(T t, Object... objs);
}

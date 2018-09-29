package com.noteshare.designPatterns.observer.demo1;

/**
 * 观察者，需要用到观察者模式的类需实现此接口
 */
public interface Observer {
	void update(Object ... objs);
}

package com.noteshare.designPatterns.observer.demo1;

/**
 * 使用(实现Observer接口）
 */
public class Text extends Activity implements Observer {
	public void onCreate(Object ... objs){
      ConcreteObservable.getInstance().registerObserver(Text.class);
      //...
  }

	// 实现接口处理
	public void update(Object ... objs) {
		// 做操作，比如更新数据，更新UI等
	}
}

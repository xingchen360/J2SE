package com.noteshare.designPatterns.singleton;

/**
 * @ClassName			: StaticSingleton 
 * @Description			: 静态内部类懒汉模式
 * 静态内部类在没有显示调用的时候是不会进行加载的，当执行了return InstanceHolder.instance后才加载初始化，这样就实现了正确的单例模式。
 * @author 				： NoteShare 
 * @date 				： 2017年10月30日 下午11:06:12
 */
public class StaticSingleton {
	
	private StaticSingleton(){
		
	}
	
	public static StaticSingleton getInstance(){
		return InstanceHandler.staticSingleton;  
	}
	
	static class InstanceHandler{
		private static StaticSingleton staticSingleton = new StaticSingleton();
	}
	
}

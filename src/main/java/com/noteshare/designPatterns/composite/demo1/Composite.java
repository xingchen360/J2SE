package com.noteshare.designPatterns.composite.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式第一种实现：将管理子元素的方法定义在Composite类中。
 * @author Administrator
 *
 */

public class Composite implements Component{
	
	private List<Component> list = new ArrayList<Component>();
	
	public void add(Component component){
		list.add(component);
	}
	public void remove(Component component){
		list.remove(component);
	}
	public List<Component> getAll(){
		return this.list;
	}
	@Override
	public void doSomething() {
		for(Component component : list){
			//经典：非常好的利用了方法的多态，方法是动态绑定的。
			component.doSomething();
		}
	}
}

package com.noteshare.designPatterns.composite2;

import java.util.ArrayList;
import java.util.List;
/**
 * 组合模式的第二种实现：将管理子元素的方法定义在Component接口中，这样Leaf类
 * 就需要对这些方法空实现。
 * @author Administrator
 *
 */

public class Composite implements Component{

	private List<Component> list = new ArrayList<Component>();
	
	@Override
	public void doSomething() {
		for(Component component : list){
			component.doSomething();
		}
	}
	
	@Override
	public void add(Component component) {
		list.add(component);
	}

	@Override
	public void remove(Component component) {
		list.remove(component);
	}
	
	@Override
	public List<Component> getAll() {
		return this.list;
	}

}

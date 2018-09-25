package com.noteshare.designPatterns.prototype.demo1;

import java.util.ArrayList;

public abstract class Shape implements Cloneable {

	private String id;
	protected String type;
	// 模拟深克隆添加的额外属性
	private ArrayList<String> list = new ArrayList<String>();

	abstract void draw();
	
	public void addList(String item){
		this.list.add(item);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	@SuppressWarnings("unchecked")
	public Shape clone() {
		try {
			// 浅克隆 只需以下一行代码即可
			Shape shape = (Shape) super.clone();
			// 深克隆 需要添加的代码
			shape.list = (ArrayList<String>) this.list.clone();
			return shape;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
package com.noteshare.designPatterns.memento.demo1;

/**
 * 备忘录类:备忘录有两个接口，Caretaker只能看到备忘录的窄接口，它只能将备忘录传递给其他对象。Originator能够看到一个宽接口，
 * 允许它访问返回到先前状态所需的所有数据。
 */
public class Memento {
	// 需要备份的属性
	private String state;

	/**
	 * 通过构造方法导入需要备份的属性，需要保存的数据属性，可以是多个
	 */
	public Memento(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}

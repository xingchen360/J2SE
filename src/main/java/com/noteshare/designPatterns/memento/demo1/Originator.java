package com.noteshare.designPatterns.memento.demo1;

/**
 * 发起人:负责创建一个备忘录（Memento），用以记录当前时刻的内部状态，并可使用备忘录恢复内部状态。
 * Originator可根据需要决定Memento存储Originator的哪些内部状态。
 */
public class Originator {
	// 状态属性
	private String state;

	/**
	 * 将需要保存的属性保存到备忘录中
	 * 
	 * @param state
	 *            : 需要保存的状态属性值
	 * @return 返回备忘录对象
	 */
	public Memento saveStateToMemento() {
		return new Memento(state);
	}

	/**
	 * 从备份录对象中恢复对象属性，此处可以是多个属性
	 * 
	 * @param memento
	 */
	public void recoverStateFromMemento(Memento memento) {
		state = memento.getState();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}

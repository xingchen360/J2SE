package com.noteshare.designPatterns.memento.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理者:负责保存好备忘录Memento，不能对备忘录的内容进行操作或检查。
 */
public class CareTaker {
	private List<Memento> mementoList = new ArrayList<Memento>();

	public void add(Memento memento) {
		mementoList.add(memento);
	}

	public Memento getMemento(int index) {
		return mementoList.get(index);
	}
}

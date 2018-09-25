package com.noteshare.designPatterns.composite.demo3;

/**
 * 文本文件类：叶子构件 --leaf
 */
public class TextFile extends AbstractFile {
	private String name;

	public TextFile(String name) {
		this.name = name;
	}

	public void killVirus() {
		// 模拟杀毒
		System.out.println("----对文本文件'" + name + "'进行杀毒");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

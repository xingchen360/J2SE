package com.noteshare.designPatterns.composite.demo3;

import java.util.ArrayList;

/**
 * 文件夹类：容器构件 --Composite
 */
public class Folder extends AbstractFile {
	// 定义集合fileList，用于存储AbstractFile类型的成员
	private ArrayList<AbstractFile> fileList = new ArrayList<AbstractFile>();
	private String name;

	public Folder(String name) {
		this.name = name;
	}

	public void add(AbstractFile file) {
		fileList.add(file);
	}

	public void remove(AbstractFile file) {
		fileList.remove(file);
	}

	public AbstractFile getChild(int i) {
		return (AbstractFile) fileList.get(i);
	}

	public void killVirus() {
		System.out.println("****对文件夹'" + name + "'进行杀毒"); // 模拟杀毒

		// 递归调用成员构件的killVirus()方法
		for (Object obj : fileList) {
			((AbstractFile) obj).killVirus();
		}
	}

	public ArrayList<AbstractFile> getFileList() {
		return fileList;
	}

	public void setFileList(ArrayList<AbstractFile> fileList) {
		this.fileList = fileList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

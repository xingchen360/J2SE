package com.noteshare.designPatterns.composite.demo3;

/**
 * 视频文件类：叶子构件 --leaf
 */
public class VideoFile extends AbstractFile {
	private String name;

	public VideoFile(String name) {
		this.name = name;
	}

	public void killVirus() {
		// 模拟杀毒
		System.out.println("----对视频文件'" + name + "'进行杀毒");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

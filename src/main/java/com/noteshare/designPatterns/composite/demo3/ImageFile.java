package com.noteshare.designPatterns.composite.demo3;

/**
 * 图像文件类：叶子构件--leaf
 */
public class ImageFile extends AbstractFile {

	private String name;

	public ImageFile(String name) {
		this.name = name;
	}

	@Override
	public void killVirus() {
		// 模拟杀毒
		System.out.println("----对图像文件'" + name + "'进行杀毒");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

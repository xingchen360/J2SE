package com.noteshare.designPatterns.proxy.staticProxy;

/**
 * @Title:ILawSuit.java
 * @Description:定义接口
 * @author:NoteShare
 * @date:2019年4月30日 上午11:19:48
 */
public interface ILawSuit {
	/**
	 * 提起诉讼
	 * @param proof 证据
	 * @return 返回成功标识
	 */
	boolean submit(String proof);
}

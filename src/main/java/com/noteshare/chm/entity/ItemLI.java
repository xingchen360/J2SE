package com.noteshare.chm.entity;

public class ItemLI {
	//TODO:对各个属性为空的情况进行处理
	/**
	 * Object对象类型：<LI><OBJECT type="text/sitemap">
	 */
	private String type;
	/**
	 * Name：Name参数的value值 <param name="Name" value="树形标题名称">
	 */
	private String name;
	/**
	 * Local:Local参数的value值(即点击左侧树形时跳转到的页面地址)<param name="Local" value="DHTMLref/default.html">
	 */
	private String local;
	/**
	 * @return String type:Object对象类型 例如： OBJECT type="text/sitemap"
	 */
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return String Name：Name参数的value值
	 *         例如： param name="Name" value="树形标题名称"
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return String Local:Local参数的value值(即点击左侧树形时跳转到的页面地址)
	 *         例如：param name="Local" value="DHTMLref/default.html"
	 */
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
}

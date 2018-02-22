package com.noteshare.chm.entity;

/**
 * 
 * 类名称：ItemFile 类描述：w文件 创建人：chx 创建时间：2014-6-10 下午09:41:29 修改人：Administrator
 * 修改时间：2014-6-10 下午09:41:29 修改备注：
 * 
 * @version 1.1
 */
public class ItemFile {
	/**
	 * 文件深度0代表是最顶层文件
	 */
	private int deep;
	/**
	 * 文件项其实就是一个li，但是注意一个li并不一定是一个文件项，也可能是一个文件夹项的描述
	 */
	private ItemLI li;
	/**
	 * 该文件所属文件夹，也就是他的父类
	 */
	private ItemFiles parentItemFiles;

	/**
	 * @param deep 深度
	 * @param li 文件项的li对象
	 */
	public ItemFile(int deep, ItemLI li) {
		this.deep = deep;
		this.li = li;
	}

	/**
	 * @return 获取文件相对应于hhc中的字符串
	 */
	public String getFileHHCString() {
		if (null == li) {
			return null;
		}
		StringBuffer fileStr = new StringBuffer("");
		// 记录制表符
		StringBuffer ttStr = new StringBuffer("");
		for (int i = 0; i <= deep; i++) {
			fileStr.append("\t");
			ttStr.append("\t");
		}
		// 文件字符串开始
		fileStr.append("<LI>");
		// 文件字符串Object对象拼接开始
		fileStr.append("<OBJECT type=\"");
		// --------------------拼接Object类型开始-------------
		fileStr.append(li.getType());
		fileStr.append("\">\n");
		// ---------------------参数拼接开始-------------------
		// Name参数拼接
		// 制表符
		fileStr.append(ttStr.toString() + "\t");
		fileStr.append(" <param name=\"Name\" value=\"");
		fileStr.append(li.getName() + "\">\n");
		// Local参数拼接
		// 制表符
		fileStr.append(ttStr.toString() + "\t");
		fileStr.append(" <param name=\"Local\" value=\"");
		fileStr.append(li.getLocal() + "\">\n");
		// 拼接制表符
		fileStr.append(ttStr.toString());
		fileStr.append("    </OBJECT>\n");
		// --------------------拼接Object类型结束-------------
		return fileStr.toString();
	}
	/**
	 * @return 获取文件相对应于hhk中的字符串
	 */
	public Object getFileHHKString() {
		String ttStr = "\t";
		if (null == li) {
			return null;
		}
		StringBuffer fileStr = new StringBuffer("");
		// 文件字符串开始
		fileStr.append(ttStr);
		fileStr.append("<LI>");
		// 文件字符串Object对象拼接开始
		fileStr.append("<OBJECT type=\"");
		// --------------------拼接Object类型开始-------------
		fileStr.append(li.getType());
		fileStr.append("\">\n");
		// ---------------------参数拼接开始-------------------
		// Name参数拼接
		// 制表符
		fileStr.append(ttStr + "\t");
		fileStr.append(" <param name=\"Name\" value=\"");
		fileStr.append(li.getName() + "\">\n");
		// Local参数拼接
		// 制表符
		fileStr.append(ttStr + "\t");
		fileStr.append(" <param name=\"Local\" value=\"");
		fileStr.append(li.getLocal() + "\">\n");
		// 拼接制表符
		fileStr.append(ttStr + "\t");
		fileStr.append("</OBJECT>\n");
		// --------------------拼接Object类型结束-------------
		return fileStr.toString();
	}
	public ItemLI getLi() {
		return li;
	}

	public void setLi(ItemLI li) {
		this.li = li;
	}

	public int getDeep() {
		return deep;
	}

	public void setDeep(int deep) {
		this.deep = deep;
	}

	public ItemFiles getParentItemFiles() {
		return parentItemFiles;
	}

	public void setParentItemFiles(ItemFiles parentItemFiles) {
		//TODO:需要从新调整下层目录的深度
		this.parentItemFiles = parentItemFiles;
	}
}

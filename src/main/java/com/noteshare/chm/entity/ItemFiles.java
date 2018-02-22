package com.noteshare.chm.entity;

import java.util.List;


/**
  *    
  * 类名称：ItemFiles
  * 类描述： 文件夹:既可以包含文件也可以包含文件夹
  * 创建人：chx   
  * 创建时间：2014-6-10 下午09:54:43   
  * 修改人：Administrator   
  * 修改时间：2014-6-10 下午09:54:43   
  * 修改备注：   
  * @version 1.1
 */
public class ItemFiles {
	/**
	 * 文件夹的深度 0 代表最顶层文件夹
	 */
	private int deep;
	/**
	 * 文件夹其实也是一个特殊的文件
	 */
	private ItemFile mySelf;
	/**
	 * 文件夹下的文件
	 */
	private List<ItemFile> fileList;
	/**
	 * 文件夹下的文件夹
	 */
	private List<ItemFiles> filesList;
	/**
	 * 该文件夹所属的父文件夹
	 */
	private ItemFiles parentItemFiles;
	/**
	 * 空的构造方法
	 */
	public ItemFiles(){
		
	}
	/**
	 * 只包含文件的文件夹
	 * @param myself:文件夹本身
	 * @param fileList：文件夹中包含的文件项
	 */
	public ItemFiles(ItemFile mySelf,List<ItemFile> fileList){
		this.mySelf = mySelf;
		this.fileList = fileList;
	}
	/**
	 * 包含文件和文件夹的文件夹
	 * @param mySelf
	 * @param fileList
	 * @param filesList
	 */
	public ItemFiles(ItemFile mySelf,List<ItemFile> fileList,List<ItemFiles> filesList){
		this.mySelf = mySelf;
		this.fileList = fileList;
		this.filesList = filesList;
	}
	/**
	 * @return 返回文件夹对应的HHC字符串
	 */
	public String getFilesHHCStr(){
		String filesStr = "";
		// 记录制表符
		StringBuffer ttStr = new StringBuffer("");
		for (int i = 0; i <= deep; i++) {
			ttStr.append("\t");
		}
		//文件夹对应的字符串
		//顶层文件夹
		//文件夹本身项的字符串
		String mySelfStr = getMyselfStr();
		//文件夹的字符串的生成
		String filesListItemStr = getFilesListStr();
		//文件列表的字符串生成
		String fileListStr = getFileListStr();
		filesStr = mySelfStr + ttStr + "<UL>\n" + filesListItemStr + fileListStr + ttStr + "</UL>\n";
		return filesStr;
	}
	/**
	 * @return 得到文件夹本身对应的字符串
	 */
	private String getMyselfStr(){
		return  mySelf.getFileHHCString();
	}
	/**
	 * @return 返回文件夹列表对应的字符串
	 */
	private String getFilesListStr(){
		String filesListItemStr = "";
		if(null != filesList && filesList.size() != 0){
			for(ItemFiles itemFiles : filesList){
				filesListItemStr += itemFiles.getFilesHHCStr();
			}
		}
		return filesListItemStr;
	}
	/**
	 * @return 返回文件列表对应的字符串
	 */
	private String getFileListStr(){
		String fileListStr = "";
		if(null != fileList && fileList.size() != 0){
			for(ItemFile itemFile : fileList){
				fileListStr += itemFile.getFileHHCString();
			}
		}
		return fileListStr;
	}
	public ItemFile getMySelf() {
		return mySelf;
	}
	public void setMySelf(ItemFile mySelf) {
		this.mySelf = mySelf;
	}
	public List<ItemFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<ItemFile> fileList) {
		this.fileList = fileList;
	}
	public List<ItemFiles> getFilesList() {
		return filesList;
	}
	public void setFilesList(List<ItemFiles> filesList) {
		this.filesList = filesList;
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
		//TODO需要从新调整下层目录的深度
		
		this.parentItemFiles = parentItemFiles;
	}
}

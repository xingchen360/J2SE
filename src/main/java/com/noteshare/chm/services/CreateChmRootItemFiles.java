package com.noteshare.chm.services;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.noteshare.chm.entity.ItemFile;
import com.noteshare.chm.entity.ItemFiles;
import com.noteshare.chm.entity.ItemLI;

public class CreateChmRootItemFiles {
	/**
	 * 生成的顶层ItemFiles对象,方便生成hhk，可以省去从新生成itemFiles的步骤
	 */
	private ItemFiles itemFiles;
	/**
	 * 顶层文件夹路径
	 */
	private String rootPath;
	/**
	 * 顶层文件夹深度
	 */
	private int rootDeep;
	/**
	 * 顶层文件夹相对路径
	 */
	private String rootFatherRelativePath;
	/**
	 * 根目录下包含的所有文件
	 */
	private List<ItemFile> itemFileList;
	
	/**
	 * 顶层ItemFiles的构造方法
	 */
	public CreateChmRootItemFiles(String rootPath,int rootDeep,String rootFatherRelativePath){
		this.itemFileList = new LinkedList<ItemFile>();
		this.rootPath = rootPath;
		this.rootDeep = rootDeep;
		this.rootFatherRelativePath = rootFatherRelativePath;
		this.itemFiles = getItemFiles(rootPath,rootDeep,rootFatherRelativePath);
	}
	/**
	 * @Description 生成文件夹对应的ItemFiles，屏蔽掉了最底层文件夹中不含有.html或.htm的文件夹对象
	 * @param path 文件绝对路径
	 * @param deep 文件深度
	 * @param fatherRelativePath 父文件相对路径
	 * @return null代表该文件中不包含.html或.htm的文件也不包含包含.html或.htm的文件夹
	 */
	private ItemFiles getItemFiles(String path,int deep,String fatherRelativePath){
		ItemFiles itemFiles = new ItemFiles();
		File file = new File(path);
		List<ItemFiles> filesList = new ArrayList<ItemFiles>();
		List<ItemFile> fileList = new ArrayList<ItemFile>();
		File[] files = file.listFiles();
		//判断这个文件夹中是否包含.html的文件或文件夹,包含flag为true，不包含为false
		//默认不包含
		boolean flag = false;
		for(File ff : files){
			if(ff.isDirectory()){
				flag = true;
				break;
			}
			String name = ff.getName();
			if(name.contains(".")){
				String type = name.substring(name.lastIndexOf("."));
				if(null != type && (".html".equals(type) || ".htm".equals(type))){
					flag = true;
					break;
				}
			}
		}
		//如果不包含则返回null
		if(!flag){
			return null;
		}
		
		for(File tempFile : files){
			if(tempFile.isDirectory()){
				ItemFiles itemFilesTemp = getItemFiles(tempFile.getAbsolutePath(), deep + 1,fatherRelativePath + "/" + tempFile.getName());
				if(null != itemFilesTemp){
					filesList.add(itemFilesTemp);
				}
			}else{
				//----------------生成文件夹中的文件对象开始------------------------------
				String liNameTemp = tempFile.getName();
				if(liNameTemp.contains(".")){
					String type = liNameTemp.substring(liNameTemp.lastIndexOf("."));
					if(!".html".equals(type) && !".htm".equals(type)){
						continue;
					}
				}else{
					continue;
				}
				liNameTemp = liNameTemp.substring(0,liNameTemp.lastIndexOf("."));
				String liTypeTemp = "text/sitemap";
				String liLocalTemp = fatherRelativePath + "/" + tempFile.getName();
				ItemLI liTemp = new ItemLI();
				liTemp.setName(liNameTemp);
				liTemp.setType(liTypeTemp);
				liTemp.setLocal(liLocalTemp);
				ItemFile itemFile = new ItemFile(deep + 1, liTemp);
				fileList.add(itemFile);
				//将所有文件项添加到itemFileList，以便生成HHK文件
				itemFileList.add(itemFile);
				//----------------生成文件夹中的文件对象结束------------------------------
			}
		}
		//判断是否包含包含.html或.htm的文件夹和.html或.htm的文件
		if(filesList.size() == 0 && fileList.size() == 0){//说明此文件夹不需要添加进去
			return null;
		}
		//需要返回的ItemFiles对象结果
		itemFiles.setFilesList(filesList);
		itemFiles.setFileList(fileList);
		//-----------------生成文件夹本身对象开始-----------------------------
		itemFiles.setDeep(deep);
		String liName = file.getName();
		String liType = "text/sitemap";
		String liLocal = "";
		ItemLI li = new ItemLI();
		li.setLocal(liLocal);
		li.setName(liName);
		li.setType(liType);
		ItemFile mySelf = new ItemFile(deep,li);
		itemFiles.setMySelf(mySelf);
		//-----------------生成文件夹本身对象结束-----------------------------
		return itemFiles;
	}
	
	public ItemFiles getItemFiles() {
		return itemFiles;
	}

	public void setItemFiles(ItemFiles itemFiles) {
		this.itemFiles = itemFiles;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public int getRootDeep() {
		return rootDeep;
	}

	public void setRootDeep(int rootDeep) {
		this.rootDeep = rootDeep;
	}

	public String getRootFatherRelativePath() {
		return rootFatherRelativePath;
	}

	public void setRootFatherRelativePath(String rootFatherRelativePath) {
		this.rootFatherRelativePath = rootFatherRelativePath;
	}
	public List<ItemFile> getItemFileList() {
		return itemFileList;
	}
	public void setItemFileList(List<ItemFile> itemFileList) {
		this.itemFileList = itemFileList;
	}
}

package com.noteshare.chm.test.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.noteshare.chm.entity.ItemFile;
import com.noteshare.chm.entity.ItemFiles;
import com.noteshare.chm.entity.ItemLI;

public class ItemFilesTest {
	@Test
	public void getFilesStrTest(){
		ItemFiles itemFiles = new ItemFiles();
		ItemFiles itemFilesResult =  getItemFiles(itemFiles,"D:\\chmtest\\DHTMLref",0,"DHTMLref");
		String str = itemFilesResult.getFilesHHCStr();
		System.out.println(str);
	}
	
	private ItemFiles getItemFiles(ItemFiles itemFiles,String path,int deep,String fatherPath){
		File file = new File(path);
		List<ItemFiles> filesList = new ArrayList<ItemFiles>();
		List<ItemFile> fileList = new ArrayList<ItemFile>();
		//需要返回的ItemFiles对象结果
		itemFiles.setDeep(deep);
		//-----------------生成文件夹本身对象开始-----------------------------
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
		File[] files = file.listFiles();
		for(File tempFile : files){
			ItemFiles itemFiles2 = new ItemFiles();
			if(tempFile.isDirectory()){
				filesList.add(getItemFiles(itemFiles2, tempFile.getAbsolutePath(), deep + 1,fatherPath + "/" + tempFile.getName()));
			}else{
				//----------------生成文件夹中的文件对象开始------------------------------
				String liNameTemp = tempFile.getName();
				String liTypeTemp = "text/sitemap";
				String liLocalTemp = fatherPath + "/" + tempFile.getName();
				ItemLI liTemp = new ItemLI();
				liTemp.setName(liNameTemp);
				liTemp.setType(liTypeTemp);
				liTemp.setLocal(liLocalTemp);
				ItemFile itemFile = new ItemFile(deep + 1, liTemp);
				fileList.add(itemFile);
				//----------------生成文件夹中的文件对象结束------------------------------
			}
		}
		itemFiles.setFileList(fileList);
		itemFiles.setFilesList(filesList);
		return itemFiles;
	}
}

package com.noteshare.freemarker.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.noteshare.freemarker.entity.Menu;
import com.noteshare.freemarker.entity.Person;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
/**
  * @ClassName: Demo1 
  * @Description:  入门知识demo：主要是一些基本的插值何集合或对象的插值。
  * @author xingchen 
  * @date 2015-9-12 下午04:52:26
 */
public class Demo1 {
	public static void main(String[] args) throws Exception {
		 // 设置模板的home目录，默认从此目录获取模板（此设置只需在应用中设置一次） 
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File("D:/study/freemarker_template_home/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        // 获取模板
        Template temp = cfg.getTemplate("demo1.ftl");
        String filename = temp.getName();
        filename = filename.substring(0, filename.lastIndexOf(".") + 1) + "html";
        FileOutputStream fos = new FileOutputStream(new File("D:/study/freemarker_output_home/" + filename));
        Writer out = new OutputStreamWriter(fos);
        //创建插值的Map 
        Map<String,String> map = new HashMap<String,String>(); 
        map.put("user", "<span style='color:red'>星辰</span>"); 
        // 将模板和数据模型合并 
        //Writer out = new OutputStreamWriter(System.out);
        //temp.process(map, out);
        //entity
        Person person = new Person();
        person.setAge(10);
        person.setPrice(100.5);
        person.setName("星辰");
        //list
        List<Person> list = new ArrayList<Person>();
        list.add(person);
        Map<String,Object> resultMap = new HashMap<String, Object>(); 
        resultMap.put("persons", list);
        //map
        Map<String,String> attrMap = new HashMap<String, String>();
        attrMap.put("attr2", "attr2:value2");
        attrMap.put("attr1", "attr1:value1");
        resultMap.put("attrMap", attrMap);
        resultMap.put("str", "aaa,bbb,ccc,ddd");
		//list-list list的嵌套
		List<Menu> menuList1 = new ArrayList<Menu>();
		Menu menu11 = new Menu("00010001", "0001", null);
		menuList1.add(menu11);
		List<Menu> menuList2 = new ArrayList<Menu>();
		Menu menu21 = new Menu("00020001", "0002", null);
		Menu menu22 = new Menu("00020002", "0002", null);
		menuList2.add(menu21);
		menuList2.add(menu22);
		//parent
		List<Menu> menuList = new ArrayList<Menu>();
		Menu menu1 = new Menu("0001","0000", menuList1);
		Menu menu2 = new Menu("0002","0000",menuList2);
		menuList.add(menu1);
		menuList.add(menu2);
		resultMap.put("menus", menuList);
        
        temp.process(resultMap,out);
        out.flush();
        out.close();
	}
}

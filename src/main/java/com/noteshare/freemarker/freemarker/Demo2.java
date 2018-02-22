package com.noteshare.freemarker.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class Demo2 {
	public static void main(String[] args) throws Exception {
		// 设置模板的home目录，默认从此目录获取模板（此设置只需在应用中设置一次） 
	    Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
	    cfg.setDirectoryForTemplateLoading(new File("D:/study/freemarker_template_home/"));
	    cfg.setDefaultEncoding("UTF-8");
	    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	    // 获取模板
	    Template temp = cfg.getTemplate("demo2.ftl");
        String filename = temp.getName();
        filename = filename.substring(0, filename.lastIndexOf(".") + 1) + "html";
        FileOutputStream fos = new FileOutputStream(new File("D:/study/freemarker_output_home/" + filename));
        Writer out = new OutputStreamWriter(fos);
        //格式化输出数值
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("answer", Double.valueOf(54));
        //格式化输出日期
        Date nowTime = new Date();
        map.put("nowTime", nowTime);
        temp.process(map,out);
        out.flush();
        out.close();
	}
}

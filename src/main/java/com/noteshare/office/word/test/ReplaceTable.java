package com.noteshare.office.word.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.docx4j.Docx4J;
import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.finders.ClassFinder;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;

/**
 * 循环替换表格内容循环替换标签${name}${sex}${age
 * https://blog.csdn.net/weixin_34295316/article/details/86022702}
 * 
 * @author QiaoLiQiang
 * @time 2018年10月28日下午8:51:41
 */
public class ReplaceTable {

    public static void main(String[] args) throws JAXBException {
        org.docx4j.wml.ObjectFactory objectFactory = Context.getWmlObjectFactory();

        String template = "D:/temp/日报.docx";

        boolean save = true;
        String outputfilepath = "D:/temp/日报_表格变量循环替换.docx";

        WordprocessingMLPackage wordMLPackage;
        try {
            wordMLPackage = WordprocessingMLPackage.load(new java.io.File(template));
            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

            // 构造循环列表的数据
            ClassFinder find = new ClassFinder(Tbl.class);
            new TraversalUtil(wordMLPackage.getMainDocumentPart().getContent(), find);
            Tbl table = (Tbl) find.results.get(0);//获取到第一个表格元素
            Tr dynamicTr = (Tr) table.getContent().get(1);// 第二行约定为模板，获取到第二行内容
            String dynamicTrXml = XmlUtils.marshaltoString(dynamicTr);// 获取模板行的xml数据

            List<Map<String, Object>> dataList = getDataList();
            for (Map<String, Object> dataMap : dataList) {
                Tr newTr = (Tr) XmlUtils.unmarshallFromTemplate(dynamicTrXml, dataMap);// 填充模板行数据
                table.getContent().add(newTr);
            }

            // 删除模板行的占位行
            table.getContent().remove(1);

            Docx4J.save(wordMLPackage, new File(outputfilepath));
        } catch (Docx4JException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, Object>> getDataList() {
        List list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Map map = new HashMap();
            map.put("name", "name" + i);
            map.put("sex", "sex" + i);
            map.put("age", "age" + i);
            list.add(map);
        }
        return list;
    }
}
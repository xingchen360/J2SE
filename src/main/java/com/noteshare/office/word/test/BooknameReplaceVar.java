package com.noteshare.office.word.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.docx4j.Docx4J;
import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.finders.RangeFinder;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Body;
import org.docx4j.wml.CTBookmark;
import org.docx4j.wml.CTMarkupRange;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Document;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.ParaRPr;
import org.docx4j.wml.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过书签替换变量
 * https://blog.csdn.net/weixin_34295316/article/details/86022702
 * @author QiaoLiQiang
 * @time 2018年10月28日下午9:35:52
 */
public class BooknameReplaceVar {
	private static final Logger log = LoggerFactory.getLogger(BooknameReplaceVar.class);
	private static WordprocessingMLPackage wordMLPackage;
	private static ObjectFactory factory;

	public static void main(String[] args) {
		bookReplaceVarText();
	}

	private static void bookReplaceVarText() {
		String template = "D:/temp/日报.docx";

		try {
			wordMLPackage = WordprocessingMLPackage.load(new java.io.File(template));
			MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();
			factory = Context.getWmlObjectFactory();

			Document wmlDoc = (Document) mainDocumentPart.getJaxbElement();
			Body body = wmlDoc.getBody();
			// 提取正文中所有段落
			List<Object> paragraphs = body.getContent();
			// 提取书签并创建书签的游标
			RangeFinder rt = new RangeFinder("CTBookmark", "CTMarkupRange");
			new TraversalUtil(paragraphs, rt);
			// 遍历书签
			for (CTBookmark bm : rt.getStarts()) {
				log.info("标签名称:" + bm.getName());
				// 这儿可以对单个书签进行操作，也可以用一个map对所有的书签进行处理
				if (bm.getName().equals("name")) {
					replaceText(bm, "qlq");
				}
				if (bm.getName().equals("pic")) {
					addImage(wordMLPackage, bm, "D:/temp/timg.jpg");
				}
			}
			Docx4J.save(wordMLPackage, new File("D:/temp/日报_书签替换.docx"));

		} catch (Docx4JException e) {
			log.error("bookReplaceVarText error:Docx4JException ", e);
		} catch (Exception e) {
			log.error("bookReplaceVarText error:Docx4JException ", e);
		}
	}

	/**
	 * 在标签处插入内容
	 * 
	 * @param bm
	 * @param wPackage
	 * @param object
	 * @throws Exception
	 */
	public static void replaceText(CTBookmark bm, Object object) throws Exception {
		if (object == null) {
			return;
		}
		// do we have data for this one?
		if (bm.getName() == null)
			return;
		String value = object.toString();
		try {
			// Can't just remove the object from the parent,
			// since in the parent, it may be wrapped in a JAXBElement
			List<Object> theList = null;
			ParaRPr rpr = null;
			if (bm.getParent() instanceof P) {
				PPr pprTemp = ((P) (bm.getParent())).getPPr();
				if (pprTemp == null) {
					rpr = null;
				} else {
					rpr = ((P) (bm.getParent())).getPPr().getRPr();
				}
				theList = ((ContentAccessor) (bm.getParent())).getContent();
			} else {
				return;
			}
			int rangeStart = -1;
			int rangeEnd = -1;
			int i = 0;
			for (Object ox : theList) {
				Object listEntry = XmlUtils.unwrap(ox);
				if (listEntry.equals(bm)) {

					if (((CTBookmark) listEntry).getName() != null) {

						rangeStart = i + 1;

					}
				} else if (listEntry instanceof CTMarkupRange) {
					if (((CTMarkupRange) listEntry).getId().equals(bm.getId())) {
						rangeEnd = i - 1;

						break;
					}
				}
				i++;
			}
			int x = i - 1;
			// if (rangeStart > 0 && x >= rangeStart) {
			// Delete the bookmark range
			for (int j = x; j >= rangeStart; j--) {
				theList.remove(j);
			}
			// now add a run
			org.docx4j.wml.R run = factory.createR();
			org.docx4j.wml.Text t = factory.createText();
			// if (rpr != null)
			// run.setRPr(paraRPr2RPr(rpr));
			t.setValue(value);
			run.getContent().add(t);
			// t.setValue(value);

			theList.add(rangeStart, run);
			// }
		} catch (ClassCastException cce) {
			log.error("error", cce);
		}
	}

	/**
	 * 插入图片
	 * 
	 * @param wPackage
	 * @param bm
	 * @param file
	 */
	public static void addImage(WordprocessingMLPackage wPackage, CTBookmark bm, String file) {
		log.info("addImage :->{},{},{}", wPackage, bm, file);
		try {
			// 这儿可以对单个书签进行操作，也可以用一个map对所有的书签进行处理
			// 获取该书签的父级段落
			P p = (P) (bm.getParent());
			// R对象是匿名的复杂类型，然而我并不知道具体啥意思，估计这个要好好去看看ooxml才知道
			R run = factory.createR();
			// 读入图片并转化为字节数组，因为docx4j只能字节数组的方式插入图片
			byte[] bytes = IOUtils.toByteArray(new FileInputStream(file));

			// InputStream is = new FileInputStream;
			// byte[] bytes = IOUtils.toByteArray(inputStream);
			// byte[] bytes = FileUtil.getByteFormBase64DataByImage("");
			// 开始创建一个行内图片
			BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wPackage, bytes);
			// createImageInline函数的前四个参数我都没有找到具体啥意思，，，，
			// 最有一个是限制图片的宽度，缩放的依据
			Inline inline = imagePart.createImageInline(null, null, 0, 1, false, 0);
			// 获取该书签的父级段落
			// drawing理解为画布？
			Drawing drawing = factory.createDrawing();
			drawing.getAnchorOrInline().add(inline);
			run.getContent().add(drawing);
			p.getContent().add(run);
		} catch (Exception e) {
			log.error("", e);
		}
	}
}
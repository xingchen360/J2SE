package com.noteshare.officeToHtmlOrPdf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PPTToHtml {
	public static void main(String[] args) {
		PPTToHtml pptToHtml = new PPTToHtml();
		pptToHtml.pptToHtml("D:\\test\\环境监管产品一部概况v2.0.ppt");
	}

	private int pptToHtml(String path) {
		int rv = 0;
		// word路径
		String wordPath = "d:\\test";
		// 文件夹名
		String folderName = UUID.randomUUID().toString();
		List<String> imgList = new ArrayList<String>();
		File file = new File(path);
		File folder = new File(wordPath + File.separator + folderName);
		try {
			folder.mkdirs();
			FileInputStream is = new FileInputStream(file);
			SlideShow ppt = new SlideShow(is);
			is.close();
			Dimension pgsize = ppt.getPageSize();
			org.apache.poi.hslf.model.Slide[] slide = ppt.getSlides();
			for (int i = 0; i < slide.length; i++) {
				TextRun[] truns = slide[i].getTextRuns();
				for (int k = 0; k < truns.length; k++) {
					RichTextRun[] rtruns = truns[k].getRichTextRuns();
					for (int l = 0; l < rtruns.length; l++) {
						rtruns[l].setFontIndex(1);
						rtruns[l].setFontName("宋体");
					}
				}
				BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = img.createGraphics();
				graphics.setPaint(Color.BLUE);
				graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
				slide[i].draw(graphics);
				// 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
				String imgName = folderName + File.separator +"pict_"+ (i + 1) + ".jpeg";
				FileOutputStream out = new FileOutputStream(wordPath + File.separator + imgName);
				javax.imageio.ImageIO.write(img, "jpeg", out);
				out.close();

				imgList.add(imgName);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return rv;
		} catch (IOException e) {
			e.printStackTrace();
			return rv;
		}
		rv = createHtml(wordPath, imgList);
		return rv;
	}

	/**
	 * ppt转html时生成html
	 * 
	 * @param wordPath
	 *            upload根目录
	 * @param imgList
	 *            所有幻灯片路径
	 * @param tempContextUrl
	 *            项目访问路径
	 * @return
	 */
	private int createHtml(String wordPath, List<String> imgList) {
		int rv = 0;
		StringBuilder sb = new StringBuilder(
				"<!doctype html><html><head><meta charset='utf-8'><title>无标题文档</title></head><body>");
		if (imgList != null && !imgList.isEmpty()) {
			for (String img : imgList) {
				sb.append("<img src='" + img + "' /><br>");
			}
		}
		sb.append("</body></html>");
		String uuid = UUID.randomUUID().toString();
		try {
			File file = new File(wordPath + File.separator + uuid + ".html");
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			bufferedWriter.write(sb.toString());
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return rv;
		}
		// presentationDto.setHtmlPath(tempContextUrl + "upload" +
		// File.separator + uuid + ".html");
		return 1;
	}
}

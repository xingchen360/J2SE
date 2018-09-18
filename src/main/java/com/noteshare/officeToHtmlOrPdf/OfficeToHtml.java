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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.IURIResolver;
import org.apache.poi.xwpf.converter.core.XWPFConverterException;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import fr.opensagres.xdocreport.converter.BasicURIResolver;

public class OfficeToHtml {
	/**
	 * docx文件转html
	 * @param tempContextUrl 项目访问名
	 * @return
	 */
	public int Word2007ToHtml(String path,String tempContextUrl) {
		int rv = 0;
		try {
			// word路径
			String wordPath = path.substring(0, path.indexOf("upload") + 6);
			// word文件名
			String wordName = path.substring(path.lastIndexOf(File.separator) + 1, path.lastIndexOf("."));
			// 后缀
			String suffix = path.substring(path.lastIndexOf("."));
			// 生成html路径
			String htmlPath = wordPath + File.separator + System.currentTimeMillis() + "_show" + File.separator;
			// 生成html文件名
			String htmlName = System.currentTimeMillis() + ".html";
			// 图片路径
			String imagePath = htmlPath + "image" + File.separator;

			// 判断html文件是否存在
			File htmlFile = new File(htmlPath + htmlName);

			// word文件
			File wordFile = new File(wordPath + File.separator + wordName + suffix);

			// 1) 加载word文档生成 XWPFDocument对象
			InputStream in = new FileInputStream(wordFile);
			XWPFDocument document = new XWPFDocument(in);

			// 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
			File imgFolder = new File(imagePath);
			XHTMLOptions options = XHTMLOptions.create();
			options.setExtractor(new FileImageExtractor(imgFolder));
			// html中图片的路径 相对路径
			options.URIResolver((IURIResolver) new BasicURIResolver("image"));
			options.setIgnoreStylesIfUnused(false);
			options.setFragment(true);

			// 3) 将 XWPFDocument转换成XHTML
			// 生成html文件上级文件夹
			File folder = new File(htmlPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			OutputStream out = new FileOutputStream(htmlFile);
			XHTMLConverter.getInstance().convert(document, out, options);

			// 4) 转换为项目访问路径
			String absolutePath = htmlFile.getAbsolutePath();
			htmlPath = tempContextUrl + absolutePath.substring(absolutePath.indexOf("upload"));
			//presentationDto.setHtmlPath(htmlPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return rv;
		} catch (XWPFConverterException e) {
			e.printStackTrace();
			return rv;
		} catch (IOException e) {
			e.printStackTrace();
			return rv;
		}
		rv = 1;
		return rv;
	}

	private int xlsToHtml(String path) {
		int rv = 0;
		// word路径
		String wordPath = "d:\\test";
		try {
			InputStream input = new FileInputStream(path);
			HSSFWorkbook excelBook = new HSSFWorkbook(input);
			ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(
					DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
			excelToHtmlConverter.processWorkbook(excelBook);
			List<?> pics = excelBook.getAllPictures();
			if (pics != null) {
				for (int i = 0; i < pics.size(); i++) {
					Picture pic = (Picture) pics.get(i);
					try {
						pic.writeImageContent(new FileOutputStream(wordPath + pic.suggestFullFileName()));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			Document htmlDocument = excelToHtmlConverter.getDocument();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			DOMSource domSource = new DOMSource(htmlDocument);
			StreamResult streamResult = new StreamResult(outStream);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty(OutputKeys.METHOD, "html");
			serializer.transform(domSource, streamResult);
			outStream.close();

			String content = new String(outStream.toByteArray(), "utf-8");

			String uuid = UUID.randomUUID().toString();
			FileUtils.writeStringToFile(new File(wordPath, uuid + ".html"), content, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return rv;
		}
		rv = 1;
		return rv;
	}

	/**
	 * ppt转html
	 * 
	 * @param tempContextUrl
	 * @return
	 */
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
                for ( int k=0;k<truns.length;k++){      
                   RichTextRun[] rtruns = truns[k].getRichTextRuns();      
                  for(int l=0;l<rtruns.length;l++){      
                        rtruns[l].setFontIndex(1);      
                        rtruns[l].setFontName("宋体");  
                   }      
                }      
                BufferedImage img = new BufferedImage(pgsize.width,pgsize.height, BufferedImage.TYPE_INT_RGB);   
                Graphics2D graphics = img.createGraphics();   
                graphics.setPaint(Color.BLUE);   
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));   
                slide[i].draw(graphics);   
 
                // 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径   
                String imgName = File.separator + folderName + File.separator +"pict_"+ (i + 1) + ".jpeg";
                
                FileOutputStream out = new FileOutputStream(wordPath + imgName);   
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
        rv = createHtml(wordPath,imgList);
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
		//presentationDto.setHtmlPath(tempContextUrl + "upload" + File.separator + uuid + ".html");
		return 1;
	}

	/**
	 * pdf转html
	 * 
	 * @param tempContextUrl
	 * @return
	 */
	/*private int pdfToHtml(String path,String tempContextUrl) {
		int rv = 0;
		// word路径
		String wordPath = path.substring(0, path.indexOf("upload") + 6);
		// 文件夹名
		String folderName = UUID.randomUUID().toString();
		List<String> imgList = new ArrayList<String>();
		File file = new File(path);
		try {
			PDDocument doc = PDDocument.load(path);
			int pageCount = doc.getPageCount();
			System.out.println(pageCount);
			List pages = doc.getDocumentCatalog().getAllPages();
			for (int i = 0; i < pages.size(); i++) {
				PDPage page = (PDPage) pages.get(i);
				BufferedImage image = page.convertToImage();
				Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
				ImageWriter writer = (ImageWriter) iter.next();
				String imgName = File.separator + folderName + File.separator + i + ".jpg";
				File folder = new File(wordPath + File.separator + folderName); // 先创建文件夹
				folder.mkdirs();
				File outFile = new File(wordPath + imgName); // 再创建文件
				imgList.add(File.separator + "upload" + imgName);
				outFile.createNewFile();
				FileOutputStream out = new FileOutputStream(outFile);
				ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
				writer.setOutput(outImage);
				writer.write(new IIOImage(image, null, null));
			}
			doc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return rv;
		} catch (IOException e) {
			e.printStackTrace();
			return rv;
		}
		rv = createHtml(wordPath, imgList, tempContextUrl);
		return 1;
	}*/
	
	public static void main(String[] args) {
		OfficeToHtml officeToHtml = new OfficeToHtml();
		//officeToHtml.xlsToHtml("D:\\test\\1.xls");
		officeToHtml.pptToHtml("D:\\test\\环境监管产品一部概况v2.0.ppt");
	}
}

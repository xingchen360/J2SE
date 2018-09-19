package com.noteshare.officeToHtmlOrPdf.toHtml;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;

public class TestDemo {

	final static String path = "D:\\test\\";
	final static String file = "1.xls";
    private static final String EXCEL_XLS = "xls";  
    private static final String EXCEL_XLSX = "xlsx";  
	     
	public static void main(String[] args)
	{
		try{
	        InputStream input = new FileInputStream(path +"/"+ file);  
	        HSSFWorkbook excelBook = new HSSFWorkbook();
	        //�ж�Excel�ļ���07+�汾ת��Ϊ03�汾
	        if(file.endsWith(EXCEL_XLS)){  //Excel 2003  
	        	excelBook = new HSSFWorkbook(input);  
	        }
	        else if(file.endsWith(EXCEL_XLSX)){  // Excel 2007/2010  
	        	Transform xls = new Transform();
	        	XSSFWorkbook workbookOld = new XSSFWorkbook(input); 
	            xls.transformXSSF(workbookOld, excelBook);
	        }  
	        
	        ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()); 	        
	        //ȥ��Excelͷ��  
	        excelToHtmlConverter.setOutputColumnHeaders(false);  
	        //ȥ��Excel�к�  
	        excelToHtmlConverter.setOutputRowNumbers(false);  
	          
	        excelToHtmlConverter.processWorkbook(excelBook); 
	          
	        Document htmlDocument = excelToHtmlConverter.getDocument();  
	  
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        DOMSource domSource = new DOMSource(htmlDocument);  
	        StreamResult streamResult = new StreamResult(outStream);  
	        TransformerFactory tf = TransformerFactory.newInstance();  
	        Transformer serializer = tf.newTransformer();  
	          	        
	        serializer.setOutputProperty(OutputKeys.ENCODING, "gb2312");  
	        serializer.setOutputProperty(OutputKeys.INDENT, "yes");  
	        serializer.setOutputProperty(OutputKeys.METHOD, "html");  
	          
	        serializer.transform(domSource, streamResult);  
	        outStream.close();  
	  
	        //Excelת����ԭʼHtml
	        String content = new String(outStream.toByteArray());  
	        
	        //��ȡ���λ��
	        ArrayList<Integer> LeftMarkIndex=new ArrayList<Integer>();
	        ArrayList<Integer> RightMarkIndex=new ArrayList<Integer>();	        
			for(int i=-1; i<=content.lastIndexOf("(");++i)
			{
			     i=content.indexOf("(",i);
			     LeftMarkIndex.add(i);		     
			} 
			
			for(int i=-1; i<=content.lastIndexOf(")");++i)
			{
			     i=content.indexOf(")",i);
			     RightMarkIndex.add(i);		     
			} 
			
			//�滻��Ϣ��
			ArrayList<String> MarkInfoList=new ArrayList<String>();
			ArrayList<String> InsteadHtmlInfoList=new ArrayList<String>();
			//������ǵ�
			for(int i=0;i<LeftMarkIndex.size();i++)
			{				
				//��ȡ��ǵ�����
				String MarkInfo=content.substring(LeftMarkIndex.get(i).intValue(),RightMarkIndex.get(i).intValue()+1);
				//��������Ϣ
				String InsteadHtmlInfo=getInsteadHtmlInfo(MarkInfo);
                //����滻��Ϣ
				MarkInfoList.add(MarkInfo);
				InsteadHtmlInfoList.add(InsteadHtmlInfo);				
			}
	       
			//��Html�б�ʶ���������滻
			for(int i=0;i<MarkInfoList.size();i++)
			{
				content=content.replace(MarkInfoList.get(i), InsteadHtmlInfoList.get(i));
			}
			System.out.println(content);
		}
		catch(Exception e) {
			e.printStackTrace();			
		}
	}
	
	//Excel��ǩ�滻
	public static String getInsteadHtmlInfo(String MarkInfo)
	{
		//ɾ�����
		MarkInfo=MarkInfo.replace("(","");
		MarkInfo=MarkInfo.replace(")","");
		String InsteadHtmlInfo="";
		if(MarkInfo.contains("area"))
		{
            //���ı����滻
			String Name=MarkInfo.split("_")[1];
			String Rows=MarkInfo.split("_")[2];
			InsteadHtmlInfo="<textarea name='"+Name+"' rows='"+Rows+"' style='width:100%;height:100%'></textarea>";
		}
		else if(MarkInfo.contains("text"))
		{
            //�ı����滻
			String Name=MarkInfo.split("_")[1];
			InsteadHtmlInfo="<input name='"+Name+"'type='textbox' style='width:100%;height:100%'>";
		}
		else if(MarkInfo.contains("label"))
		{
            //�ı����滻
			String Name=MarkInfo.split("_")[1];
			InsteadHtmlInfo="<label name='"+Name+"'></label>";
		}
		else if(MarkInfo.contains("img"))
		{
            //�ı����滻
			String Name=MarkInfo.split("_")[1];
			InsteadHtmlInfo="<img name='"+Name+"' src='img/Q1.jpg' height='30' width='80' />";
		}
		return InsteadHtmlInfo;		
	}
}

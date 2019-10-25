/******************************************************************************
* Copyright (C) 2019 NoteShare 
* 网站：itnoteshare.com
*****************************************************************************/
package com.noteshare.officeToHtmlOrPdf.export;

/**
 * @Title:IreportUtil.java
 * @Description:IreportUtil
 * @author:NoteShare
 * @date:2019年10月22日 上午9:16:17
 * 
 * TODO:excle打印测试与学习该框架
 * <!-- https://mvnrepository.com/artifact/net.sf.jasperreports/jasperreports -->
<dependency>
    <groupId>net.sf.jasperreports</groupId>
    <artifactId>jasperreports</artifactId>
    <version>6.10.0</version>
</dependency>
 */
public class IreportUtil {
	/*
	 * private static final Logger log =
	 * LoggerFactory.getLogger(IreportUtil.class);
	 * 
	 * public static void exportExcel(HttpServletResponse response, String path,
	 * Map parameters, String fileName) { Connection conn = null; try { conn =
	 * ((DataSource) SpringUtil.getBean(DataSource.class)).getConnection();
	 * 
	 * fileName = URLEncoder.encode(fileName, "UTF-8");
	 * response.setHeader("Content-Disposition",
	 * MessageFormat.format("attachment;filename={0}.xls", new Object[] {
	 * fileName })); response.setContentType("application/vnd.ms-excel");
	 * 
	 * JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameters,
	 * conn);
	 * 
	 * JRXlsExporter exporter = new JRXlsExporter();
	 * 
	 * exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
	 * exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
	 * response.getOutputStream());
	 * exporter.setParameter(JRXlsExporterParameter.
	 * IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	 * exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
	 * Boolean.FALSE);
	 * exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
	 * Boolean.FALSE); exporter.exportReport(); } catch (Exception e) {
	 * log.debug(ExceptionUtil.getExceptionDetail(e)); throw new
	 * RuntimeException("excel报表打印出错"); } finally { if (conn != null) try {
	 * conn.close(); } catch (SQLException e) { e.printStackTrace(); } } }
	 * 
	 * public static void main(String[] args) {
	 * System.out.println(MessageFormat.format("attachment;filename={0}.xls",
	 * new Object[] { "周报表" })); }
	 */
}

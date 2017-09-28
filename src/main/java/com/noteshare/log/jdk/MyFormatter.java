package com.noteshare.log.jdk;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @ClassName : MyFormatter
 * @Description : 自定义日志过滤器
 * @author ： NoteShare
 * @date ： 2017年9月28日 下午11:10:57
 */
public class MyFormatter extends Formatter {
	private static final String format = "%s %s %s %s %s %n";
	private final Date dat = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String format(LogRecord record) {
		dat.setTime(record.getMillis());
		String source;
		if (record.getSourceClassName() != null) {
			source = record.getSourceClassName();
			if (record.getSourceMethodName() != null) {
				source += " " + record.getSourceMethodName();
			}
		} else {
			source = record.getLoggerName();
		}
		String message = formatMessage(record);
		String throwable = "";
		if (record.getThrown() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			pw.println();
			record.getThrown().printStackTrace(pw);
			pw.close();
			throwable = sw.toString();
		}
		//sdf.format(new Date())
		return String.format(format, sdf.format(dat), "调用日志的类：" + source, "级别：" + record.getLevel().getName(),
				"日志内容为：" + message, "throwable:" + throwable);
	}

}

package com.noteshare.log.log4j2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * @ClassName			: Log4j2 
 * @Description			: TODO
 * @author 				： NoteShare 
 * @date 				： 2017年10月6日 下午3:44:15
 */
public class Log4j2 {
	public static void main(String[] args) {
		//testNoConfiguration();
		//testAddConfiguration();
		//testMyLog();
		//testMyRollingFile();
		testFileLocation();
	}
	
	/**
	 * @Title			: testNoConfiguration 
	 * @Description		: 入门示例，在没有引入配置文件的情况下运行报如下错误
ERROR StatusLogger No log4j2 configuration file found. Using default configuration: logging only errors to the console. Set system property 'log4j2.debug' to show Log4j2 internal initialization logging.
15:43:50.883 [main] ERROR  - error level
15:43:50.887 [main] FATAL  - fatal level
	    在没有引入配置文件时，默认控制台输出，并提示缺失配置文件。
	 * @author 			： NoteShare
	 * @date 			： 2017年10月6日 下午3:44:52 
	 * @throws
	 */
	public static void testNoConfiguration(){
		Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);  
	    logger.trace("trace level");  
	    logger.debug("debug level");  
	    logger.info("info level");  
	    logger.warn("warn level");  
	    logger.error("error level");  
	    logger.fatal("fatal level");  
	}
	/**
	 * @Title			: testAddConfiguration 
	 * @Description		: 在src/mian/resources下添加log4j2.xml后运行
	 * 输出结果为（没有再提示找不到文件了）：
16:18:29.473 [main] ERROR  - error level
16:18:29.476 [main] FATAL  - fatal level
此处之所以只输出两行适合配置文件中指定的日志级别有关系的，测试的配置文件日志级别为
<Root level="error">  
    <AppenderRef ref="Console" />  
</Root> 
	 * @author 			： NoteShare
	 * @date 			： 2017年10月6日 下午4:17:04 
	 * @throws
public static final Level OFF;
public static final Level FATAL;
public static final Level ERROR;
public static final Level WARN;
public static final Level INFO;
public static final Level DEBUG;
public static final Level TRACE;
public static final Level ALL;
	 */
	public static void testAddConfiguration(){
		Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);  
	    logger.trace("trace level");  
	    logger.debug("debug level");  
	    logger.info("info level");  
	    logger.warn("warn level");  
	    logger.error("error level");  
	    logger.fatal("fatal level");  
	}
	
	/**
	 * @Title			: testMyLog 
	 * @Description		: 自定义log测试，需配置配置文件
	 * Logger从获取Root Logger改为尝试获得一个名称为mylog的Logger，在配置文件中一无所得后，又找到了Root Logger，所以执行结果相同。
	 * 如果找到了的话将根据自定的Log进行输出
	 * @author 			： NoteShare
	 * @date 			： 2017年10月6日 下午4:51:01 
	 * @throws
	 */
	public static void testMyLog(){
		Logger logger = LogManager.getLogger("mylog");  
	    logger.trace("trace level");  
	    logger.debug("debug level");  
	    logger.info("info level");  
	    logger.warn("warn level");  
	    logger.error("error level");  
	    logger.fatal("fatal level");  
	}
	/**
	 * @Title			: testMyRollingFile 
	 * @Description		: 测试日志文件回滚
	 * @author 			： NoteShare
	 * @date 			： 2017年10月6日 下午5:20:42 
	 * @throws
	 */
	public static void testMyRollingFile(){
		Logger logger = LogManager.getLogger("MyRollingFile");  
		logger.trace("trace level");  
		logger.debug("debug level");  
		logger.info("info level");  
		logger.warn("warn level");  
	}
	/**
	 * @Title			: testFileLocation 
	 * @Description		: 测试自定义log4j2的配置文件路径
	 * @throws Exception 
	 * @author 			： NoteShare
	 * @date 			： 2017年10月6日 下午5:59:43 
	 * @throws
	 */
	public static void testFileLocation(){
		BufferedInputStream in = null;
		try {
			File file = new File("D:/log4j2.xml");  
			in = new BufferedInputStream(new FileInputStream(file));  
			final ConfigurationSource source = new ConfigurationSource(in);  
			Configurator.initialize(null, source);
			Logger logger = LogManager.getLogger("mylog");
			logger.info("=====");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null != in)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
}

package com.noteshare.log.jdk;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
* @Title:  jdk自带的日志组件功能测试
* @des  :  读取src/main/resources/logging.properties文件中的日志配置信息，将日志根据配置进行持久化
* @history 2017年9月28日
* @author NoteShare
*/
public class JdkLog {
    static Logger logger = null;
    
    static{
        //获取编译文件根路径
        String path = Class.class.getClass().getResource("/").getPath();
        //加载配置文件,此必须放置在创建logger之前，否则配置文件不起作用,即日志只会输出到控制台，不写入文件
        System.setProperty("java.util.logging.config.file",path + "logging.properties");
        logger = Logger.getLogger(JdkLog.class.getName());
    }
    
    public static void main(String[] args) {
        JdkLog jdkLog = new JdkLog();
        //jdkLog.printLogLevel();
        //jdkLog.testMemoryHandler();
        jdkLog.testSocketHandler();
    }
    /**
     * 禁用: OFF
     * 严重: SEVERE
     * 警告: WARNING
     * 信息: INFO
     * =========================java.util.logging和其他日志组件等级对应情况
     * error            SEVERE
     * warn             WARNING
     * info             INFO、CONFIG
     * debug            FINE、FINER
     * trace            FINEST
     */
    private void printLogLevel(){
    	/*
    	九月 28, 2017 10:55:25 下午 com.noteshare.log.jdk.JdkLog printLogLevel
    	禁用: OFF
    	九月 28, 2017 10:55:25 下午 com.noteshare.log.jdk.JdkLog printLogLevel
    	严重: SEVERE
    	九月 28, 2017 10:55:25 下午 com.noteshare.log.jdk.JdkLog printLogLevel
    	警告: WARNING
    	九月 28, 2017 10:55:25 下午 com.noteshare.log.jdk.JdkLog printLogLevel
    	信息: INFO
    	九月 28, 2017 10:55:25 下午 com.noteshare.log.jdk.JdkLog printLogLevel
    	信息: ===============================
    	*/    	
        logger.log(Level.OFF, "OFF");
        logger.log(Level.SEVERE, "SEVERE");
        logger.log(Level.WARNING, "WARNING");
        logger.log(Level.INFO, "INFO");
        logger.log(Level.CONFIG, "CONFIG");
        logger.log(Level.FINE, "FINE");
        logger.log(Level.FINER, "FINER");
        logger.log(Level.FINEST, "FINEST");
        logger.log(Level.ALL, "ALL");
        //log(Level.INFO, msg);
        logger.info("===============================");
        //手工代码中改变过滤器（原先是按配置文件来），只有级别大于info才会被输出
        logger.setFilter(new Filter() {
			@Override
			public boolean isLoggable(LogRecord record) {
				return record.getLevel().intValue() > Level.INFO.intValue();
			}
		});
        /* 	九月 28, 2017 10:55:25 下午 com.noteshare.log.jdk.JdkLog printLogLevel
        	禁用: OFF
        	九月 28, 2017 10:55:25 下午 com.noteshare.log.jdk.JdkLog printLogLevel
        	严重: SEVERE
        	九月 28, 2017 10:55:25 下午 com.noteshare.log.jdk.JdkLog printLogLevel
        	警告: WARNING
        */        
        logger.log(Level.OFF, "OFF");
        logger.log(Level.SEVERE, "SEVERE");
        logger.log(Level.WARNING, "WARNING");
        logger.log(Level.INFO, "INFO");
        logger.log(Level.CONFIG, "CONFIG");
        logger.log(Level.FINE, "FINE");
        logger.log(Level.FINER, "FINER");
        logger.log(Level.FINEST, "FINEST");
        logger.log(Level.ALL, "ALL");
        /* 	信息:++++++++++++++++++++++++
        	九月 28, 2017 10:59:34 下午 com.noteshare.log.jdk.JdkLog printLogLevel
        	警告: warning
        */        
        logger.info("++++++++++++++++++++++++");
        logger.log(new LogRecord(Level.WARNING, "warning"));
    }
    /**
     * 测试内存处理器
     * 输出结果：
     *  2017-09-29 16:51:25 调用日志的类：com.noteshare.log.jdk.JdkLog printLog2 级别：INFO 日志内容为：=====================MemoryHandler============== throwable: 
        2017-09-29 16:51:25 调用日志的类：com.noteshare.log.jdk.JdkLog printLog2 级别：INFO 日志内容为：========noteShare2============ throwable: 
        2017-09-29 16:51:25 调用日志的类：com.noteshare.log.jdk.JdkLog printLog2 级别：INFO 日志内容为：========你好啊啊啊啊啊 啊3============ throwable: 
        2017-09-29 16:51:25 调用日志的类：com.noteshare.log.jdk.JdkLog printLog2 级别：WARNING 日志内容为：========你好啊啊啊啊啊 啊4============ throwable:
                        由此可见：第二条被过滤器给过滤掉了，只运行了前5条，因为我们配置的缓存记录数为5，为打印控制台，是因为我们配置的target是控制台，如果我们配置的是文件输出则将输出到文件中，此也经过测试确实如此
                        此处其记录条数把过滤器排除的也算上感觉不太合理，此处不做过分评论，个人理解。 
        
     */
    private void testMemoryHandler(){
        /*测试MemoryHandler的功能*/
        logger.info("=====================MemoryHandler==============");
        logger.info("========NoteShare1============");
        logger.info("========noteShare2============");
        logger.info("========你好啊啊啊啊啊 啊3============");
        logger.warning("========你好啊啊啊啊啊 啊4============");
        logger.info("========NoteShare5============");
        logger.info("========你好啊啊啊啊啊 啊6============");
    }
    /**
     * @Title			: testSocketHandler 
     * @Description		: 测试将日志发送到socket端
     * socket端接收到如下信息：com.noteshare.socket.Server
     * 服务器接收到客户端的连接请求1：九月 29, 2017 9:09:48 下午 com.noteshare.log.jdk.JdkLog testSocketHandler
     * @author 			： NoteShare
     * @date 			： 2017年9月29日 下午9:10:01 
     * @throws
     */
    private void testSocketHandler(){
    	logger.info("hello world");
    }
}

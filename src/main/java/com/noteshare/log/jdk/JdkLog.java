package com.noteshare.log.jdk;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
* @Title:  jdk自带的日志组件功能测试
* @des  :  读取src/main/resources/logging.properties文件中的日志配置信息，将日志根据配置进行持久化
* @since   JDK1.6
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
        for (int i = 0; i < 10; i++) {
            jdkLog.printLogLevel();
        }
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
        logger.log(Level.OFF, "OFF");
        logger.log(Level.SEVERE, "SEVERE");
        logger.log(Level.WARNING, "WARNING");
        logger.log(Level.INFO, "INFO");
        logger.log(Level.CONFIG, "CONFIG");
        logger.log(Level.FINE, "FINE");
        logger.log(Level.FINER, "FINER");
        logger.log(Level.FINEST, "FINEST");
        logger.log(Level.ALL, "ALL");
    }
}

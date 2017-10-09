package com.noteshare.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @Title:  Sl4j简单日志门面组件测试
* @author  NoteShare
* @history 2017年9月30日
*/
public class Slf4jLog {
   
    public static void main(String[] args) {
        Slf4jLog slf4jLog = new Slf4jLog();
        //测试slf4j  to  java.util.logging
        //slf4jLog.testSlf4jTojdkLog();
        //测试slf4j  to  simple
        slf4jLog.testSlf4jToSimple();
    }
    /**
     * 测试slf4j  to java.util.logging 
     * 使用slf4j时使用哪套日志系统主要根据你引入的slf4j相关的jar有关系，比如我想使用jdk自带的日志系统，则我引入的是如下jar
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-jdk14</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
                        只需引入以上jar即可，其内部依赖了
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
                        所以会自动下载slf4j-api的jar。
     */
    private void testSlf4jTojdkLog(){
        /**
         * 注意：此处我写死了引入的是java.util.logging的配置文件，其实实际项目中只需把相应的日志组件的配置文件放入根目录即可，此处是为了测试所以故意写死依赖
         * 方便区分使用的是那套日志组件
         */
        //获取编译文件根路径
        String path = Class.class.getClass().getResource("/").getPath();
        //加载配置文件,此必须放置在创建logger之前，否则配置文件不起作用,即日志只会输出到控制台，不写入文件
        System.setProperty("java.util.logging.config.file",path + "logging.properties");
        Logger logger = LoggerFactory.getLogger(Slf4jLog.class);
        logger.info("Hello World Slf4jLog");
        /*
         *  测试输出为：注意：此处之所以输出格式和网上不一致是因为我再logging.properties中指定了自己的输出格式。详细请查看NoteShare日志专题关于java.util.logging 相关课程的详细说明
            2017-09-30 15:34:18 调用日志的类：com.noteshare.log.slf4j.Slf4jLog main 级别：INFO 日志内容为：Hello World Slf4jLog throwable: 
         */
    }
    
    /**
     * 测试slf4j  to  simple
     * 输出结果：
        SLF4J: Class path contains multiple SLF4J bindings.
        SLF4J: Found binding in [jar:file:/D:/myNewPrograms/Repositories/org/slf4j/slf4j-jdk14/1.7.2/slf4j-jdk14-1.7.2.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        SLF4J: Found binding in [jar:file:/D:/myNewPrograms/Repositories/org/slf4j/slf4j-simple/1.7.2/slf4j-simple-1.7.2.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
        SLF4J: Actual binding is of type [org.slf4j.impl.JDK14LoggerFactory]
                        九月 30, 2017 4:04:11 下午 com.noteshare.log.slf4j.Slf4jLog testSlf4jToSimple
                        信息: Hello World Slf4jLog to Simple
     */
    private void testSlf4jToSimple(){
        Logger logger = LoggerFactory.getLogger(Slf4jLog.class);
        logger.info("Hello World Slf4jLog to Simple");
    }
    
    
    
}

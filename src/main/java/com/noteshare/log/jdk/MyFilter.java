package com.noteshare.log.jdk;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
* @Title:  自定义过滤器
* @author  NoteShare
* @history 2017年9月29日
*/
public class MyFilter implements Filter{

    @Override
    public boolean isLoggable(LogRecord record) {
        if(record.getMessage().contains("NoteShare")){
            return false;
        }else{
            return true;
        }
    }
}

package com.noteshare.mongo.gridfs.exceptions;

import com.mongodb.MongoException;

/**
 * @Title: mongo数据库操作异常类
 * @author itnoteshare
 * @since JDK1.6
 * @history 2017年4月1日 itnoteshare 新建
 */
public class MongoDBException extends MongoException {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -4127414714432646066L;

    /**
     * Constructs a new instance.
     *
     * @param message
     *            the message
     */
    public MongoDBException(final String message) {
        super(message);
    }

    /**
     * Constructs a new instance.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public MongoDBException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

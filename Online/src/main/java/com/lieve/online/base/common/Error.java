package com.lieve.online.base.common;

import org.apache.log4j.Logger;

/**
 * 系统执行过程中出错处理
 * 
 * @author liuyazhong
 * 
 */
public class Error extends BaseError {

    private static Error error = new Error();

    public Error() {

    }

    /**
     * 抛出异常错误信息同时页面提示
     * 
     * @param logger
     * @param key
     * @param e
     */
    public static void write(Logger logger, String key, Exception e) {
        error.bWrite(logger, key, e);
    }

    /**
     * 抛出异常错误信息同时页面提示
     * 
     * @param logger
     * @param key
     */
    public static void write(Logger logger, String key) {
        error.bWrite(logger, key);
    }

    /**
     * 抛出异常错误信息
     * 
     * @param key
     */
    public static void write(String key) {
        error.bWrite(key);
    }

    /**
     * 把service层的异常信息写入日志文件，同时抛出异常信息
     * 
     * @param logger
     * @param e
     */
    public static void write(Logger logger, ServiceException e) {
        error.bWrite(logger, e);
    }

    public static void writeErrorMsg(Logger logger, String msg, Throwable e) {
        error.bWriteErrorMsg(logger, msg, e);
    }

}

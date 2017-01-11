package com.lieve.online.base.common;


import org.apache.log4j.Logger;
import org.guzz.exception.DaoException;
import org.guzz.exception.GuzzException;

/**
 * @author zhenjw
 * @version 1.0
 * @createDate 2009-12-4 下午03:34:48
 * @since 1.0
 */
public abstract class BaseError<T extends BaseMessages> {

    private Logger log = Logger.getLogger(this.getClass());

    /**
     * 抛出异常错误信息同时页面提示
     *
     * @param logger
     * @param key
     * @param e
     */
    public void bWrite(Logger logger, String key, Exception e) {
        String msg = Messages.getString(key);
        logger.debug(msg, e);
        this.judgeException(msg, e);
    }

    /**
     * 抛出异常错误信息同时页面提示
     *
     * @param logger
     * @param key
     */
    public void bWrite(Logger logger, String key) {
        String error = Messages.getString(key);
        logger.debug(error);
        throw new ActionException(error);
    }

    /**
     * 抛出异常错误信息
     *
     * @param key
     */
    public void bWrite(String key) {
        String error = Messages.getString(key);
        throw new ActionException(error);
    }

    /**
     * 把service层的异常信息写入日志文件，同时抛出异常信息
     *
     * @param logger
     * @param e
     */
    public void bWrite(Logger logger, ServiceException e) {
        logger.debug(e);
        throw new ActionException(e.getMessage());
    }

    public void bWriteErrorMsg(Logger logger, String msg, Throwable e) {

        logger.debug(msg, e);
        this.judgeException(msg, e);
    }

    private void judgeException(String msg, Throwable e) {

        Throwable d = e.getCause();

        String sqlExceptionMessage = null;

        while (d != null) {
            d.getCause();
            log.debug(" error ", d);

            if (d instanceof java.sql.BatchUpdateException
                    || d instanceof java.sql.SQLException
                    || d instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {

                sqlExceptionMessage = d.getMessage();

                log.debug(sqlExceptionMessage);

                break;
            }

            d = d.getCause();

        }

        String errorMsg = null;

        if (sqlExceptionMessage != null)

            errorMsg = ErrorPool.getErrMsg(sqlExceptionMessage);

        // 如果不是由于SQL引起的
        if (errorMsg == null) {
            if (e instanceof ServiceException || e instanceof DaoException
                    || e instanceof RuntimeException) {
                throw new ActionException(e.getMessage());

            } else if (e instanceof GuzzException) {
                throw new ActionException("访问数据库错误");
            } else {
                throw new ActionException(msg);
            }
        } else {
            throw new ActionException(errorMsg);
        }

    }

}

package com.lieve.online.base.common;

import org.apache.log4j.Logger;
import org.guzz.exception.DaoException;

/**
 * 
 * @author Administrator
 * 
 */
public class ErrorTools {  
	
	private Logger log=Logger.getLogger(this.getClass());

	/**
	 * 抛出异常错误信息同时页面提示
	 * 
	 * @param logger
	 * @param key
	 * @param e
	 */
	public void write(Logger logger, String key, Exception e) {

		String msg = Messages.getString(key) ;
		logger.debug(msg, e) ;
		this.judgeException(msg, e) ;
	}
	
	public void write(Logger logger, String key, Exception e,Object ... arguments) {

		String msg = Messages.getString(key,arguments) ;
		logger.debug(msg, e) ;
		this.judgeException(msg, e) ;
	}

	/**
	 * 抛出异常错误信息同时页面提示
	 * 
	 * @param logger
	 * @param key
	 */
	public void write(Logger logger, String key) {

		String errorMes = Messages.getString(key) ;
		this.writeLog(logger, errorMes); 
	}
	
	public void write(Logger logger, String key,Object ... arguments) {
		String errorMes = Messages.getString(key,arguments) ;
		this.writeLog(logger, errorMes); 
	}
	
	public void writeLog(Logger logger ,String errorMes)
	{
		logger.debug(errorMes) ;
		throw new ActionException(errorMes) ;
	}
	 
	

	/**
	 * 抛出异常错误信息
	 * 
	 * @param key
	 */
	public void write(String key) {

		String error = Messages.getString(key) ;
		throw new ActionException(error) ;
	}
	public void write(String key ,Object ... arguments) {
		
		String error = Messages.getString(key,arguments) ;
		throw new ActionException(error) ;
	}

	/**
	 * 把service层的异常信息写入日志文件，同时抛出异常信息
	 * 
	 * @param logger
	 * @param e
	 */
	public void write(Logger logger, ServiceException e) {

		logger.debug(e) ;
		throw new ActionException(e.getMessage()) ;
	}

	public void writeErrorMsg(Logger logger, String msg, Throwable e) {

		this.judgeException(msg, e) ;
	}

	private void judgeException(String msg, Throwable e) {
		
		Throwable d = e.getCause() ;
		
		log.debug("msg="+msg+", error ", d);
		
		String sqlExceptionMessage = null ; 

		while (d != null) {
			 log.debug(" error ", d);

			if (d instanceof java.sql.BatchUpdateException || d instanceof java.sql.SQLException || d instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
				sqlExceptionMessage = d.getMessage() ;
				log.debug(sqlExceptionMessage);
				break ;
			}
			d = d.getCause() ;
		}

		String errorMsg = null ;

		if (sqlExceptionMessage != null)
		{
			errorMsg = ErrorPool.getErrMsg(sqlExceptionMessage) ;			
		}

		//System.out.println("msg="+msg+",errorMsg="+errorMsg+",sqlExceptionMessage="+sqlExceptionMessage+", e.classname="+e.getClass().getName());
		
		// 如果不是由于SQL引起的
		if (errorMsg == null) {
			if (e instanceof DaoException) {
				throw new ActionException("数据库错误") ;
			}else if (e instanceof ServiceException || e instanceof RuntimeException) {
				
				throw new ActionException(e.getMessage()) ;
			} else 
			{
				throw new ActionException(msg) ;
			}
		} else {
			throw new ActionException(errorMsg) ;
		}

	}

}

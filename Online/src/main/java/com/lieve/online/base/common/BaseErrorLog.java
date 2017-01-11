package com.lieve.online.base.common;

import org.apache.log4j.Logger;


public class BaseErrorLog {

	protected Logger logger=Logger.getLogger(this.getClass()); 
	
	protected ErrorTools Error = new ErrorTools() ;
}

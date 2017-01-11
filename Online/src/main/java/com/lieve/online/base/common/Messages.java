/**
 * 
 */
package com.lieve.online.base.common;

import java.text.MessageFormat ;
import java.util.MissingResourceException ;
import java.util.ResourceBundle ;

/**
 * @author zhenjw
 * 
 */
public class Messages { 


	private static final String BUNDLE_NAME = "messages" ; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME) ;

	private Messages() {

	}

	public static String getString(String key) {

		try {
			return RESOURCE_BUNDLE.getString(key) ;
		} catch (MissingResourceException e) {
			return '!' + key + '!' ;
		}
	}
	
	public static String getString(String key, Object ... arguments)
	{
		return MessageFormat.format(getString( key),arguments);
	}
	

}

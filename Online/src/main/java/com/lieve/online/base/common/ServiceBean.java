/**
 * 2009-6-11
 * zhenjw
 */
package com.lieve.online.base.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 得到Spring中配置的实现类
 * @author zhenjw
 *
 */
public class ServiceBean {

    /**
     * 
     * 2009-6-11
     * zhenjw
     */
    public ServiceBean() {
	// TODO Auto-generated constructor stub
    }

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");
    
    public static Object getBean(String beanname) {
    	return context.getBean(beanname);
    }

}

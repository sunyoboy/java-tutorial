/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: ReflectTest.java
 * Author:   DD240
 * Date:     2015-11-30 上午9:04:04
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.usual.reflect;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
public class ReflectTest {
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see 
     * @since 
     */
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see 
     * @since 
     */
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see 
     * @since 
     */
    public static void main(String[] args) {
        String d1 = Demo.class.getName();
        System.out.println(d1);
        
        Demo demo = new Demo();
        String d2 = demo.getClass().getName();
        
        System.out.println(d2);
        
        System.out.print("Demo.class == demo.getClass() : " + Demo.class + " :");
        System.out.println(Demo.class == demo.getClass());
        
        System.out.println(Demo.class.toString());
        
        InputStream is = demo.getClass().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        try {
            prop.load(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println(prop.getProperty("age"));
        
    }
}

class Demo {
    
}

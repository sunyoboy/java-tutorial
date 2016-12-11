/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: ReflectObject.java
 * Author:   DD240
 * Date:     2015-11-30 上午10:50:10
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.usual.reflect;

import java.lang.reflect.Field;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
public class ReflectObject {
    public static void main(String[] args) {
        Class<?> c1 = null;
        try {
            c1 = Class.forName("com.usual.reflect.Person"); // 运行时加载类
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Class<?> c2 = null;
        try {
            c2 = Class.forName("java.lang.String"); // 运行时加载类
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
        
        System.out.println("ClassLoader -> " + c1.getClassLoader());
        
        System.out.println("ClassLoader -> " + c2.getClassLoader());
        
        Field fields[] = c1.getDeclaredFields();
        System.out.println("===============本类属性================");
        for(int i=0; i<fields.length; i++) {
            System.out.println(fields[i].getName());
        }
        
        Field fields1[] = c1.getFields();
        System.out.println("===============继承接口或超类的属性================");
        for(int i=0; i<fields1.length; i++) {
            System.out.println(fields1[i].getName());
        }        
        
    }
}

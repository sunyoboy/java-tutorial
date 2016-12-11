/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: ReflectDemo3.java
 * Author:   DD240
 * Date:     2015-11-30 上午10:10:16
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.usual.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * 取构造函数的修饰符<br> 
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
public class ReflectDemo3 {
    public static void main(String[] args) {
        Class<?> c1 = null;
        try {
            c1 = Class.forName("com.usual.reflect.Person");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Constructor<?> cons[] = c1.getConstructors();
        
        for(int i=0; i<cons.length; i++) {
            Class<?> parameter[] = cons[i].getParameterTypes();
            
            System.out.println(cons[i].getModifiers());
            System.out.println("构造函数修饰符 ： " + Modifier.toString(cons[i].getModifiers()));
            
            System.out.println(cons[i].getName()); // 构造函数名
            System.out.println(cons[i].toString()); // 构造函数"声明"
            System.out.println(cons[i].DECLARED); //
            System.out.println(cons[i].PUBLIC); // 
            System.out.println();            
        }
        
        
    }
}

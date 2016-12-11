/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: ReflectDemo4.java
 * Author:   DD240
 * Date:     2015-11-30 上午10:22:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.usual.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 获取类的返回值类型 和方法列表
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
public class ReflectDemo4 {
    public static void main(String[] args) {
        Class<?> c1 = null;
        try {
            c1 = Class.forName("java.lang.String"); // 运行时加载类
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("c1 simple name -> " + c1.getSimpleName());
        
        
        Method methods[] = c1.getMethods();
        
        for(int j=0; j<methods.length; j++) {
            System.out.print("Method[" + j + "]-> " + methods[j]); // 获取String类的方法列表
            System.out.print(" returnType -> " + methods[j].getReturnType());
            System.out.println(" parameterType -> " + methods[j].getParameterTypes().toString());
            
            // System.out.println();
            
            
        }
        
        String substr = null;
        try {
                substr = (String)methods[49].invoke("String", new Object[]{1});
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println(substr);
        
        
    }
}

package com.usual.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestGeneric {
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		List<Integer> list2 = new ArrayList<Integer>();
		System.out.println("list1.class :" + list1.getClass());
		
		/* 一个泛型类的所有实例在运行时具有相同的运行时类(class)，而不管他们的实际类型参数。 */
		System.out.println("list1.getClass()  == list2.getClass() :" + (list1.getClass() == list2.getClass()));
		
		System.out.println("Class.class : " + Class.class);
		System.out.println("Serializable.class : " + Serializable.class);
		
		
		/*  可以使用带范型参数值的类声明数组，却不可有创建数组  */
		List<Integer>[] iListArray;
		// iListArray =  new ArrayList<Integer>[10];//编译时错误
	}
}


/* result: 
list1.class :class java.util.ArrayList
list1.getClass()  == list2.getClass() :true
Class.class : class java.lang.Class
Serializable.class : interface java.io.Serializable
 * */

package com.lieve.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/19/17
 * Time: 1:16 PM
 */
public class PersonTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Class clazz = Class.forName("com.lieve.test.reflect.Person");
        Constructor c = clazz.getDeclaredConstructor(new Class[]{String.class});

        //
        c.setAccessible(true);
        Person person = (Person)c.newInstance(new Object[]{"I'm a reflect construce"});
        System.out.println(person);

        Field f = clazz.getDeclaredField("name");
        f.setAccessible(true);
        Object value = f.get(person);
        System.out.println(value);
        System.out.println(f.getType());
        person.getClass();

        Constructor c1 = clazz.getDeclaredConstructor(null);
        Person p = (Person) c1.newInstance(null);

        Method method = clazz.getMethod("func", new Class[]{String.class});
        method.invoke(p, new Object[]{"I'm a reflect method"});
        System.out.println();
    }
}

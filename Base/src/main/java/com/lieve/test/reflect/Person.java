package com.lieve.test.reflect;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/19/17
 * Time: 1:11 PM
 */
public class Person implements Cloneable {

    private String name;

    private int age;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void func(String name) {
        System.out.println(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        Object o = null;
        o = (Person)super.clone();
        return o;
    }
}

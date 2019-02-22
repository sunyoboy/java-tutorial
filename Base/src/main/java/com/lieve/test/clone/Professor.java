package com.lieve.test.clone;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/19/17
 * Time: 1:51 PM
 */
public class Professor implements Cloneable {

    String name;

    int age;

    Professor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
        /*Professor professor = null;
        professor = (Professor) super.clone();
        return professor;*/
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}

class Student implements Cloneable {

    String name;

    int age;

    Professor professor;

    Student(String name, int age, Professor professor) {
        this.name = name;
        this.age = age;
        this.professor = professor;
    }

    public Object clone() throws CloneNotSupportedException {
        Student student = null;

        student = (Student) super.clone();

        student.professor = (Professor) professor.clone();
        return student;
    }

    @Override
    public String toString() {
        return name + " " + age + ", " + professor.toString();
    }
}

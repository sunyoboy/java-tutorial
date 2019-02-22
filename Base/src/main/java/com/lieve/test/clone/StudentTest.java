package com.lieve.test.clone;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/19/17
 * Time: 1:59 PM
 */
public class StudentTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Professor professor = new Professor("wangwu", 15);
        Student s1 = new Student("zhangsan", 18, professor);
        Student s2 = (Student)s1.clone();
        s2.age = 1;
        s2.professor.age = 35;
        System.out.println(s1);
        System.out.println(s2);
    }
}

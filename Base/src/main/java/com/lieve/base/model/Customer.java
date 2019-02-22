package com.lieve.base.model;

/**
 * 客户类
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 8:37 AM
 */

public class Customer {

    private long id;

    private String name;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 手机号
     */
    private String telephone;

    private String email;

    /**
     * 备注
     */
    private String mark;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}

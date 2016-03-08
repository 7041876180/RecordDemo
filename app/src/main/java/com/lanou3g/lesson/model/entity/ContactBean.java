package com.lanou3g.lesson.model.entity;

/**
 * 本类由: Risky 创建于: 15/11/19.
 */
public class ContactBean {

    private String name;
    private String telPhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    @Override
    public String toString() {
        return "ContactBean{" +
                "name='" + name + '\'' +
                ", telPhone='" + telPhone + '\'' +
                '}';
    }
}

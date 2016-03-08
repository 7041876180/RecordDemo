package com.example.equip;

/**
 * 本类由: Risky 创建于: 15/12/15.
 */
public class Equip {
    private String type;

    public Equip(String type) {
        this.type = type;
    }

    public void on(){
        System.out.println(type + "-->打开");
    }

    public void off(){
        System.out.println(type + "-->关闭");
    }
}

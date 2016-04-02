package com.lanou3g.mvvm.vm;

/**
 * 本类由: Risky57 创建于: 16/4/2.
 */
public class User {
    private final String name;
    private final String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}

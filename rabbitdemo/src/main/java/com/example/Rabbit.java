package com.example;

/**
 * 本类由: Risky 创建于: 16/1/5.
 */
public class Rabbit {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addAge() {
        ++this.age;
    }

    public Rabbit born() {
        Rabbit r = null;
        if (this.age >= 2) {
            return new Rabbit();
        }
        return r;
    }
}

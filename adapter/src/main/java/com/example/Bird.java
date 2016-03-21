package com.example;

/**
 * 本类由: Risky57 创建于: 16/3/21.
 */
public class Bird implements FlyAnimal {
    @Override
    public void flyWithWing() {
        System.out.println("在天空翱翔");
    }

    @Override
    public void lifeMode() {
        System.out.println("吃吃虫");
    }
}

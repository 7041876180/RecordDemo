package com.example;

/**
 * 本类由: Risky57 创建于: 16/3/21.
 */
public class Pig implements RunAnimal {
    @Override
    public void runWithLegs() {
        System.out.println("猪在跑");
    }

    @Override
    public void lifeMode() {
        System.out.println("跑两米就睡一会");
    }
}

package com.example;

/**
 * 本类由: Risky 创建于: 15/12/14.
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}

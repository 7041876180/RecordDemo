package com.example;

/**
 * 本类由: Risky 创建于: 15/12/14.
 */
public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;

    public CondimentDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public abstract String getDescription();
}

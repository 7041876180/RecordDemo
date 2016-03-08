package com.example;

/**
 * 本类由: Risky 创建于: 15/12/14.
 */
public abstract class Beverage {
    String description = "Unknown Beverage!";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}

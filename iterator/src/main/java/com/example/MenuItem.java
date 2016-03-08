package com.example;

/**
 * 本类由: Risky 创建于: 15/12/17.
 */
public class MenuItem {
    private String name;
    private double price;
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

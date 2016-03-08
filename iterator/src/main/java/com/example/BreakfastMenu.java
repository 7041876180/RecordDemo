package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 本类由: Risky 创建于: 15/12/17.
 */
public class BreakfastMenu {
    private List<MenuItem> menuItems;

    public BreakfastMenu() {
        menuItems = new ArrayList<>();
        addItem("豆浆", 1.0);
        addItem("鸡蛋", 1.0);
        addItem("肉包子", 2.5);
        addItem("豆腐脑", 2.0);
    }

    private void addItem(String name, double price) {
        MenuItem item = new MenuItem(name, price);
        menuItems.add(item);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Iterator createIterator(){
        return new BreakfastMenuIterator(menuItems);
    }
}

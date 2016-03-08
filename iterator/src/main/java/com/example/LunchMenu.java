package com.example;

/**
 * 本类由: Risky 创建于: 15/12/17.
 */
public class LunchMenu {
    private static final int MAX_ITEMS = 3;
    private MenuItem[] menuItems;
    private int index;

    public LunchMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("鸡排饭", 8.0);
        addItem("蛋炒饭", 7.0);
        addItem("白菜盖饭", 9.0);

    }

    private void addItem(String name, double price) {
        MenuItem item = new MenuItem(name, price);
        menuItems[index] = item;
        index++;
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public Iterator createIterator(){
        return new LunchMenuIterator(menuItems);
    }
}

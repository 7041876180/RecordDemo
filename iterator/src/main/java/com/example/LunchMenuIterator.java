package com.example;

/**
 * 本类由: Risky 创建于: 15/12/17.
 */
public class LunchMenuIterator implements Iterator<MenuItem> {
    private MenuItem [] items;
    private int index;

    public LunchMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        if (index < items.length) return true;
        return false;
    }

    @Override
    public MenuItem next() {
        MenuItem item = items[index++];
        return item;
    }
}

package com.example;

import java.util.List;

/**
 * 本类由: Risky 创建于: 15/12/17.
 */
public class BreakfastMenuIterator implements Iterator<MenuItem> {
    private List<MenuItem> items;
    private int index;

    public BreakfastMenuIterator(List<MenuItem> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        if (index < items.size()) return true;
        return false;
    }

    @Override
    public MenuItem next() {
        return items.get(index++);
    }
}

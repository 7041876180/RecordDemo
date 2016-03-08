package com.example;

import java.util.*;

public class Hotel {
    public static void main(String[] args) {
        BreakfastMenu breakfastMenu = new BreakfastMenu();
        LunchMenu lunchMenu = new LunchMenu();
        Waitress waitress = new Waitress(breakfastMenu, lunchMenu);
        waitress.printMenu();
        List<MenuItem> breakfastMenus = new ArrayList<>();
        java.util.Iterator listIterator = breakfastMenus.iterator();
        Map<String,MenuItem> map = new HashMap<>();
        java.util.Iterator mapIterator = map.values().iterator();

    }
}

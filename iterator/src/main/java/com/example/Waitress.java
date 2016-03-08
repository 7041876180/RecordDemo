package com.example;

import java.util.List;

/**
 * 本类由: Risky 创建于: 15/12/17.
 */
public class Waitress {
    private final Iterator breakfastIterator;
    private final Iterator dinerIterator;

    public Waitress(BreakfastMenu breakfastMenu, LunchMenu lunchMenu) {
        breakfastIterator = breakfastMenu.createIterator();
        dinerIterator = lunchMenu.createIterator();
    }
    public void printMenu(){
        printBreakfastMenu();
        printLunchMenu();
    }

    private void print(Iterator iterator){
        while (iterator.hasNext()){
            MenuItem item = (MenuItem) iterator.next();
            System.out.println(item.toString());
        }
    }

    public void printBreakfastMenu(){
        System.out.println("---早餐---");
        print(breakfastIterator);
    }
    public void printLunchMenu(){
        System.out.println("---午餐---");
        print(dinerIterator);
    }
}

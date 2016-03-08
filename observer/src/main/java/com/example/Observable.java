package com.example;

/**
 * 本类由: Risky 创建于: 15/12/14.
 */
public interface Observable {
    void registObserver(Observer o);
    void notifyObserver();
    void removeObserver(Observer o);
}

package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 本类由: Risky 创建于: 15/12/14.
 */
public class SubjectData implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private String msg;

    @Override
    public void registObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        int index = observers.indexOf(o);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    public void sendMsg(String msg){
        this.msg = msg;
        notifyObserver();
    }
}

package com.lanou3g.record.observer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.BooleanSubscription;

/**
 * 本类由: Risky57 创建于: 16/3/16.
 */
public class MySubject implements Subject {
    private static final String TAG = "MySubject";
    private static Subject sub = new MySubject();
    private List<Observer> observers = new ArrayList<>();
    public static Subject getInstance(){
        return sub;
    }
    public static void register(Observer ob){
        getInstance()._register(ob);
    }
    public static void unRegister(Observer ob){
        getInstance()._unRegister(ob);
    }
    public static void notifySubs(Object ety){
        getInstance()._notifySubs(ety);
    }
    public static void removeAll(){
        getInstance()._removeAll();
    }
    @Override
    public void _register(Observer ob) {
        if (!observers.contains(ob)) {
            observers.add(ob);
            Log.d(TAG, "_register: ");
        }
    }

    @Override
    public void _unRegister(Observer ob) {
        if (observers.contains(ob)) {
            observers.remove(ob);
            Log.d(TAG, "_unRegister: ");
        }
    }

    @Override
    public void _notifySubs(Object ety) {
        for (Observer observer : observers) {
            observer.update(ety);
            Log.d(TAG, "_notifySubs: ");
        }
    }
    @Override
    public void _removeAll(){
        observers.clear();
    }
}

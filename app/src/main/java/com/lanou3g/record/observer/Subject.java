package com.lanou3g.record.observer;

/**
 * 本类由: Risky57 创建于: 16/3/16.
 */
public interface Subject<T> {
    void _register(Observer<T> ob);
    void _unRegister(Observer<T> ob);
    void _notifySubs(T ety);
    void _removeAll();
}

package com.lanou3g.lesson.network;

/**
 * 本类由: Risky57 创建于: 16/2/29.
 */
public interface NetInterface<T> {
    void onSuccess(T response);
    void onError();

}

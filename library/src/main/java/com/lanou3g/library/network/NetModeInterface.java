package com.lanou3g.library.network;

/**
 * 本类由: Risky57 创建于: 16/2/29.
 */
public interface NetModeInterface {
    void startRequest(String url, NetInterface<String> request);

    <T> void startRequest(String url, Class<T> clazz, NetInterface<T> request);
}

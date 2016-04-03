package com.lanou3g.mvvm.model;

import java.util.List;

/**
 * 本类由: Risky57 创建于: 16/4/3.
 */
public interface ViewCallBack<T> {
    void onResponse(List<T> t);
}

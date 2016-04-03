package com.lanou3g.mvvm.app;

import android.app.Application;

import com.lanou3g.library.volley.VolleySingleton;

/**
 * 本类由: Risky57 创建于: 16/4/3.
 */
public class VMAPP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleySingleton.init(this);
    }
}

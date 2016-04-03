package com.lanou3g.record.application;

import android.app.Application;
import android.content.Context;

import com.lanou3g.library.network.GoNet;
import com.lanou3g.library.volley.VolleySingleton;

/**
 * Created by Risky on 15/10/28.
 */
public class RecordApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        VolleySingleton.init(this);
        GoNet.init(this);
    }

    public static Context getContext() {
        return mContext;
    }

}

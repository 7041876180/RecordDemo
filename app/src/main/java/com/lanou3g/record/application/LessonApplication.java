package com.lanou3g.record.application;

import android.app.Application;
import android.content.Context;

import com.lanou3g.record.network.GoNet;

/**
 * Created by Risky on 15/10/28.
 */
public class LessonApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        GoNet.init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}

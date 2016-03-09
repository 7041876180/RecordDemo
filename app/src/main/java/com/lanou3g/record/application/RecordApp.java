package com.lanou3g.record.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.lanou3g.record.network.GoNet;
import com.tencent.android.tpush.XGNotifaction;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushNotifactionCallback;

import java.util.List;

/**
 * Created by Risky on 15/10/28.
 */
public class RecordApp extends Application {
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

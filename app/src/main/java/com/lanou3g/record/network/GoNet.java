package com.lanou3g.record.network;

import android.content.Context;

/**
 * 本类由: Risky57 创建于: 16/2/29.
 */
public class GoNet {
    private static Context sContext;
    private NetModeInterface netMode;

    public static void init(Context context) {
        if (sContext == null) {
            sContext = context;
        }
    }

    private GoNet() {
        if (sContext == null) {
            throw new NullPointerException("请在Application中调用GoNet.init(this)");
        }
        netMode = getNetMode();
    }

    public NetModeInterface getNetMode() {
        if (netMode == null) {
            netMode = new OkHttpImpl();
        }
        return netMode;
    }

    private static final class InstanceHolder {
        private static final GoNet instance = new GoNet();
    }

    public static GoNet getInstance() {
        return InstanceHolder.instance;
    }

    private void _startRequest(String url,NetInterface<String> request) {
        netMode.startRequest(url, request);
    }

    private <T> void _startRequest(String url,Class<T> clazz, NetInterface<T> request){
        netMode.startRequest(url,clazz,request);

    }

    public static void startRequest(String url, NetInterface<String> request) {
        getInstance()._startRequest(url, request);
    }

    public static <T> void startRequest(String url, Class<T> clazz,NetInterface<T> request){
        getInstance()._startRequest(url,clazz,request);
    }
}

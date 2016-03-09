package com.lanou3g.record.network;

import com.google.gson.Gson;
import com.lanou3g.record.okhttp.OkHttpClientManager;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * 本类由: Risky57 创建于: 16/3/9.
 */
public class OkHttpImpl implements NetModeInterface {
    public OkHttpImpl() {
    }

    @Override
    public void startRequest(String url, final NetInterface<String> requestNet) {
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                requestNet.onError();
            }

            @Override
            public void onResponse(String response) {
                    requestNet.onSuccess(response);

            }
        });
    }

    @Override
    public <T> void startRequest(String url, final Class<T> clazz, final NetInterface<T> requestNet) {
        final Gson gson = new Gson();
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                requestNet.onError();
            }

            @Override
            public void onResponse(String response) {
                    T t = gson.fromJson(response,clazz);
                    requestNet.onSuccess(t);
            }
        });
    }
}

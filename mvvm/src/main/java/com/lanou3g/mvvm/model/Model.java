package com.lanou3g.mvvm.model;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.library.volley.VolleySingleton;

/**
 * 本类由: Risky57 创建于: 16/4/3.
 */
public class Model {

    public void startRequest(String url, final ViewCallBack<MovieInfo> callBack){
        VolleySingleton.addRequest(url, MovieEntity.class, new Response.Listener<MovieEntity>() {
            @Override
            public void onResponse(MovieEntity response) {
                callBack.onResponse(response.getSubjects());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}

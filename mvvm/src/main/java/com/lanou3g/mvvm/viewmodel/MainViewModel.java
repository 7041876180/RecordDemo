package com.lanou3g.mvvm.viewmodel;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.library.volley.VolleySingleton;
import com.lanou3g.mvvm.model.MovieEntity;
import com.lanou3g.mvvm.model.MovieInfo;
import com.lanou3g.mvvm.model.ViewCallBack;
import com.lanou3g.mvvm.ui.adapter.MovieAdapter;

/**
 * 本类由: Risky57 创建于: 16/4/3.
 */
public class MainViewModel {
    private Context mContext;
    private MovieAdapter adapter;

    public MainViewModel(Context context) {
        this.mContext = context;
        adapter = new MovieAdapter(context);
    }

    public void startRequest(String url){
        VolleySingleton.addRequest(url, MovieEntity.class, new Response.Listener<MovieEntity>() {

            @Override
            public void onResponse(MovieEntity response) {
                adapter.addData(response.getSubjects());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public MovieAdapter getAdapter() {
        return adapter;
    }
}

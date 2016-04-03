package com.lanou3g.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lanou3g.mvvm.databinding.ActivityMainBinding;
import com.lanou3g.mvvm.model.Model;
import com.lanou3g.mvvm.model.MovieInfo;
import com.lanou3g.mvvm.model.ViewCallBack;
import com.lanou3g.mvvm.ui.adapter.MovieAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Model model = new Model();
        model.startRequest("https://api.douban.com/v2/movie/top250",
                new ViewCallBack<MovieInfo>() {
                    @Override
                    public void onResponse(List<MovieInfo> t) {
                        Log.d(TAG, "onResponse: " + t.size());
                        MovieAdapter adapter = new MovieAdapter(MainActivity.this, t);
                        binding.setAdapter(adapter);
                    }
                });

    }
}

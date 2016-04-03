package com.lanou3g.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lanou3g.mvvm.databinding.ActivityMainBinding;
import com.lanou3g.mvvm.viewmodel.MainViewModel;
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
        final MainViewModel model = new MainViewModel(this);
        binding.setModel(model);
        model.startRequest("https://api.douban.com/v2/movie/top250");

    }
}

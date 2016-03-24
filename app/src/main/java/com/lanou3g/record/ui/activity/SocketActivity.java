package com.lanou3g.record.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.lanou3g.record.R;
import com.lanou3g.record.ui.fragment.SocketClientFgmt;
import com.lanou3g.record.ui.fragment.SocketServerFgmt;

/**
 * 本类由: Risky57 创建于: 16/3/22.
 */
public class SocketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_double);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_server,new SocketServerFgmt());
        ft.replace(R.id.fragment_client,new SocketClientFgmt());
        ft.commit();
    }
}

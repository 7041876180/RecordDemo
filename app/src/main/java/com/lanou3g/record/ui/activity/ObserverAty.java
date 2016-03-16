package com.lanou3g.record.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lanou3g.record.R;
import com.lanou3g.record.model.entity.Entity;
import com.lanou3g.record.observer.MySubject;
import com.lanou3g.record.ui.fragment.Fragment01;
import com.lanou3g.record.ui.fragment.Fragment02;
import com.lanou3g.record.ui.fragment.Fragment03;
import com.lanou3g.record.ui.fragment.Fragment04;

/**
 * 本类由: Risky57 创建于: 16/3/16.
 */
public class ObserverAty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
        final FragmentManager fm = getSupportFragmentManager();
        final Fragment01 f = new Fragment01();
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                startChange();
                FragmentTransaction ft = fm.beginTransaction();
                if (flag) {
                    ft.replace(R.id.content1, new Fragment02());
                } else {
                    ft.replace(R.id.content1, f);
                }
                ft.commit();
                flag = !flag;

            }
        });

        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content1, f);
        ft.add(R.id.content2, new Fragment02());
        ft.add(R.id.content3, new Fragment03());
        ft.add(R.id.content4, new Fragment04());
        ft.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySubject.removeAll();
    }

    private void startChange() {
        Entity ety = new Entity();
        ety.setMessage("这条消息是在Activity发送过来的,接收的是:");
        MySubject.notifySubs(ety);
    }

    public void showDialog(){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("hello...");
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Fragment01xxxx_aty", "onPause: ");
    }
}

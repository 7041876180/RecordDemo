package com.lanou3g.record.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lanou3g.record.R;
import com.lanou3g.record.ui.adapter.BottomAdapter;
import com.lanou3g.record.ui.adapter.TopAdapter;
import com.lanou3g.record.view.LinkageListView;

/**
 * 本类由: Risky57 创建于: 16/3/8.
 */
public class LinkageActivity extends AppCompatActivity {

    private LinkageListView linkage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkage);
        linkage = (LinkageListView) findViewById(R.id.linkage);
        linkage.setLinkageSpeed(1.2f);
        linkage.setAdapter(new BottomAdapter(this),new TopAdapter(this));
    }
}

package com.lanou3g.record.ui.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.lanou3g.record.ui.adapter.BaseAdapter;
import com.lanou3g.record.ui.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 本类由: Risky57 创建于: 16/3/14.
 */
public class AdapterActivity extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Entity> dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Entity ety = new Entity();
            ety.setPosition(i);
            ety.setName("Hello-->" + i);
            dataList.add(ety);
        }
        TestAdapter adapter = new TestAdapter(this);
        setListAdapter(adapter);
        adapter.addRefreshData(dataList);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseAdapter ba = (BaseAdapter) parent.getAdapter();
                Entity ety = (Entity) ba.getItem(position);
                ety.setName("Hello 个毛线");
                ba.notifyDataSetChanged();
            }
        });
    }

    public class Entity{
        private int position;
        private String name;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

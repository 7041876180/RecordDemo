package com.lanou3g.lesson.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lanou3g.lesson.R;
import com.lanou3g.lesson.utils.ScreenUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * 本类由: Risky 创建于: 15/11/19.
 */
public class Calculate extends Activity {

    private static final int COUNT = 15;
    private GridView gridView;
    private List<TextView> textViews = new ArrayList<>();

    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gridView = new GridView(this);
        gridView.setNumColumns(COUNT);
        setContentView(gridView);

        screenWidth = ScreenUtils.getScreenWidth(this);

        for (int i = 0; i < COUNT * COUNT; i++) {
            TextView tv = new TextView(this);
            tv.setWidth(screenWidth / COUNT);
            tv.setHeight(screenWidth / COUNT);
            tv.setGravity(Gravity.CENTER);

            if ( i % 6 < 3) {
                tv.setBackgroundColor(Color.BLACK);
                tv.setTextColor(Color.WHITE);
            } else {
                tv.setBackgroundColor(Color.WHITE);
                tv.setTextColor(Color.BLACK);
            }
            tv.setTextSize(17);
            tv.setText(i + "");
            tv.setTag(i);
            textViews.add(tv);
        }

        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return COUNT * COUNT;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = textViews.get(position);
                return tv;
            }
        };

        gridView.setAdapter(adapter);

        int white = -1;
        int k = 0;
        for (int i = 0; i < textViews.size(); i++) {
            TextView tv = textViews.get(i);
            Log.d("TAGGG", "------" + i + "------");

            // 第一行时
            if (i /COUNT == 0){
                if (getViewColor(tv)==white){
                    k++;
                    Log.d("TAGGG", "第一行" + k);
                }
            }

            // 第一列

            if (i % COUNT == 0){
                if (getViewColor(tv)==white){
                    k++;
                    Log.d("TAGGG", "第一列" + k);
                }
            }

            // 不在最后一行时
            if (i / COUNT != (COUNT - 1)) {
                TextView tvBottom = textViews.get(i + COUNT);
                if (getViewColor(tv) == white && getViewColor(tvBottom) == white) {
                    k++;
                    Log.d("TAGGG", "对比下方" + k);
                }
            } else {
                if (getViewColor(tv) == white) {
                    k++;
                    Log.d("TAGGG", "最后一行" + k);
                }
            }

            // 不在最后一列时
            if (i % COUNT != (COUNT - 1)) {
                TextView tvRight = textViews.get(i + 1);
                if (getViewColor(tv) == white && getViewColor(tvRight) == white) {
                    k++;
                    Log.d("TAGGG", "对比右侧" + k);
                }
            } else {
                // 在最后一列时
                if (getViewColor(tv) == white) {
                    k++;
                    Log.d("TAGGG", "最右列" + k);
                }
            }


        }

        Log.d("TAGGG", "一共有" + k + "条白线");

    }

    private int getViewColor(TextView tv) {
        ColorDrawable drawable = (ColorDrawable) tv.getBackground();
        int color = drawable.getColor();
        return color;
    }
}

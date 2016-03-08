package com.lanou3g.lesson.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanou3g.lesson.R;


/**
 * 本类由: Risky57 创建于: 16/3/5.
 */
public class TopAdapter extends android.widget.BaseAdapter {
    private LayoutInflater inflater;
    private Context mContext;

    public TopAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 50;
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
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_top,parent,false);
        return convertView;
    }
}

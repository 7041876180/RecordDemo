package com.lanou3g.record.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 本类由: Risky57 创建于: 16/3/1.
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    private List<T> dataList;
    protected static LayoutInflater inflater;
    private Context mContext;

    public BaseAdapter(List<T> dataList , Context context) {
        this.dataList = dataList;
        this.mContext = context;
        if (dataList == null) {
            this.dataList = new ArrayList<>();
        }
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    public BaseAdapter(Context context) {
        this(null,context);
    }

    private void addData(List<T> dataList, boolean isRefresh) {
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
        if (isRefresh) {
            this.dataList.clear();
        }
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }
    public void addRefreshData(List<T> dataList){
        addData(dataList, true);
    }

    public void addLoadDatas(List<T> dataList) {
        addData(dataList, false);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}

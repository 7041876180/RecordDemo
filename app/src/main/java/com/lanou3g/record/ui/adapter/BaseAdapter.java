package com.lanou3g.record.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 本类由: Risky57 创建于: 16/3/14.
 */
public abstract class BaseAdapter<T,VH extends BaseAdapter.ViewHolder> extends AbsAdapter<T> {
    public BaseAdapter(List<T> dataList, Context context) {
        super(dataList, context);
    }

    public BaseAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(getItemLayout(),parent,false);
            holder = createHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (VH) convertView.getTag();
        }
        T t = getItem(position);
        bindData(t,holder);

        return convertView;
    }

    protected abstract void bindData(T data, VH holder);

    public abstract VH createHolder(View view);

    public abstract int getItemLayout();

    public static class ViewHolder{
        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        public View getView() {
            return view;
        }
    }
}

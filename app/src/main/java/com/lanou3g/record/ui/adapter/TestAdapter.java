package com.lanou3g.record.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lanou3g.record.R;
import com.lanou3g.record.ui.activity.AdapterActivity;

import java.util.List;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * 本类由: Risky57 创建于: 16/3/1.
 */
public class TestAdapter extends BaseAdapter<AdapterActivity.Entity,TestAdapter.ViewHolder> {

    public TestAdapter(List<AdapterActivity.Entity> dataList, Context context) {
        super(dataList, context);
    }

    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindData(AdapterActivity.Entity data, ViewHolder holder) {
        holder.tvName.setText(data.getName());
        holder.tvPos.setText(data.getPosition()+"");
    }

    @Override
    public ViewHolder onCreateHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public int onItemCreate() {
        return R.layout.item_test;
    }

    public class ViewHolder extends BaseAdapter.ViewHolder{
        private TextView tvName;
        private TextView tvPos;
        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvPos = (TextView) view.findViewById(R.id.tv_pos);
        }

        public TextView getTvName() {
            return tvName;
        }
    }
}

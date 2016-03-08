package com.lanou3g.lesson.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lanou3g.lesson.R;
import com.lanou3g.lesson.model.entity.greendao.RecommendEntity;
import com.lanou3g.lesson.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Risky on 15/10/28.
 */
public class HeaderGridViewAdapter extends BaseAdapter<RecommendEntity> {
    private ImageLoader imageLoader;

    public HeaderGridViewAdapter(List<RecommendEntity> dataList, Context context) {
        super(dataList, context);
        this.imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    public HeaderGridViewAdapter(Context context) {
        this(null,context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder holder = null;
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_gridview,parent,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }
        holder.image.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.image.setErrorImageResId(R.mipmap.ic_launcher);
        RecommendEntity entity = getItem(position);
        holder.image.setImageUrl(entity.getThumb(), imageLoader);
        holder.text.setText(entity.getTitle());

        return view;
    }

    public static class ViewHolder{
        private NetworkImageView image;
        private TextView text;
        public ViewHolder(View itemView) {
            image = (NetworkImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}

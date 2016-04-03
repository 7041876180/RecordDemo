package com.lanou3g.mvvm.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lanou3g.library.volley.VolleySingleton;
import com.lanou3g.mvvm.R;
import com.lanou3g.mvvm.databinding.ItemMovieBinding;
import com.lanou3g.mvvm.model.MovieInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 本类由: Risky57 创建于: 16/4/3.
 */
public class MovieAdapter extends BaseAdapter {

    private List<MovieInfo> infos;
    private LayoutInflater inflater;

    public MovieAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.infos = new ArrayList<>();
    }

    public void addData(List<MovieInfo> infos){
        this.infos.clear();
        this.infos.addAll(infos);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public MovieInfo getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemMovieBinding binding = null;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.item_movie,parent,false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        }else {
            binding = (ItemMovieBinding) convertView.getTag();
        }

        MovieInfo info = getItem(position);
        binding.setInfo(info);
        loadImage(info.getImages().getMedium(), binding.imgMovie);
        loadImage(info.getCasts().get(0).getAvatars().getMedium(), binding.imgCast0);
        loadImage(info.getCasts().get(1).getAvatars().getMedium(), binding.imgCast1);
        loadImage(info.getCasts().get(2).getAvatars().getMedium(), binding.imgCast2);
        return convertView;
    }

    private void loadImage(String medium, ImageView imgMovie) {
        VolleySingleton.loadImage(medium, imgMovie, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }
}

package com.lanou3g.lesson.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

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
public class HeaderViewPagerAdapter extends PagerAdapter {
    private List<RecommendEntity> recommends;
    private Context context;
    private ImageLoader imageLoader;

    public HeaderViewPagerAdapter(Context context) {
        this.context = context;
        this.imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    public void addRefreshData(List<RecommendEntity> recommends, boolean isRefresh) {
        if (this.recommends == null) {
            this.recommends = new ArrayList<>();
        }
        if (isRefresh) {
            this.recommends.clear();
        }
        this.recommends.addAll(recommends);
        notifyDataSetChanged();
    }

    public void addRefreshData(List<RecommendEntity> recommends){
        addRefreshData(recommends, true);
    }

    public void addLoadDatas(List<RecommendEntity> recommends) {
        addRefreshData(recommends,false);
    }

    @Override
    public int getCount() {
        return recommends != null && recommends.size() > 0 ? Integer.MAX_VALUE : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecommendEntity entity = recommends.get(position % recommends.size());
        NetworkImageView image = new NetworkImageView(context);
        image.setErrorImageResId(R.mipmap.ic_launcher);
        image.setDefaultImageResId(R.mipmap.ic_launcher);
        image.setImageUrl(entity.getThumb(), imageLoader);
        container.addView(image);
        return image;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

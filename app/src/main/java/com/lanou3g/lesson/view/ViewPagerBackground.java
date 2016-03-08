package com.lanou3g.lesson.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.lanou3g.lesson.utils.ScreenUtils;

/**
 * 本类由: Risky 创建于: 15/11/17.
 */
public class ViewPagerBackground extends HorizontalScrollView {
    private static final Float IMAGE_WIDTH = 600f;
    private ViewPager mViewPager;
    private int offsetScroll;
    private LayoutParams lp;
    private int screenWidth;
    private int screenHeight;

    public ViewPagerBackground(Context context) {
        super(context);
        init();
    }

    public ViewPagerBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewPagerBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private ImageView mImageView;
    private Context mContext;
    private void init() {
        mContext = getContext();
//        setFillViewport(true);
        screenWidth = ScreenUtils.getScreenWidth(mContext);
        screenHeight = ScreenUtils.getScreenHeight(mContext);
        Log.d("TAGGG","ScreenWidth:-->"+screenWidth+",getWidth():-->"+getWidth());
        offsetScroll = (int) ScreenUtils.dpToPx(mContext, IMAGE_WIDTH);
        Log.d("TAGGG", "offsetScroll:-->" + offsetScroll);
        lp = new LayoutParams(offsetScroll, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(lp);
        mImageView = new ImageView(mContext);
        addView(mImageView,lp);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.d("TAGGG", "ScreenWidth:-->" + screenWidth + ",onMeasure,getWidth():-->" + getWidth());
        setMeasuredDimension(offsetScroll,screenHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        ImageView childView = (ImageView) getChildAt(0);
        childView.setAdjustViewBounds(true);
        childView.setScaleType(ImageView.ScaleType.FIT_XY);
        childView.layout(0,0,offsetScroll,screenHeight);
//        Log.d("TAGGG", "ScreenWidth:-->" + screenWidth + ",onLayout,getWidth():-->" + getWidth());
    }

    public void setUpWithViewPager(ViewPager viewPager,int imageRes) {
        this.mViewPager = viewPager;
        this.mImageView.setBackgroundResource(imageRes);
        this.mViewPager.addOnPageChangeListener(new ControlWithViewPager());
    }

    public class ControlWithViewPager implements ViewPager.OnPageChangeListener{
        private ViewPagerBackground vb;
        private float offset;

        public ControlWithViewPager() {
            vb = ViewPagerBackground.this;
            lp = (LayoutParams) vb.getLayoutParams();
            PagerAdapter adapter = mViewPager.getAdapter();
            int count = adapter.getCount();
            offset = (offsetScroll-screenWidth) / count;

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int offsetSmooth = (int) (-(position + positionOffset) * offset);
//            Log.d("TAGGG","offsetSmooth:-->"+offsetSmooth);
            lp.leftMargin = offsetSmooth;
            vb.setLayoutParams(lp);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}

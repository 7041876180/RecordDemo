package com.lanou3g.record.ui.activity;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;

import com.lanou3g.record.R;
import com.lanou3g.record.model.entity.BallEntity;
import com.lanou3g.record.model.entity.greendao.RecommendEntity;
import com.lanou3g.library.network.GoNet;
import com.lanou3g.library.network.NetInterface;
import com.lanou3g.library.okhttp.OkHttpClientManager;
import com.lanou3g.record.ui.adapter.HeaderGridViewAdapter;
import com.lanou3g.record.ui.adapter.HeaderViewPagerAdapter;
import com.lanou3g.record.view.HeaderGridView;
import com.lanou3g.library.volley.VolleySingleton;

import java.util.List;

/**
 * Created by Risky on 15/10/28.
 * GridView添加轮播图头视图
 */
public class RoundPagerActivity extends AbsBaseActivity implements AdapterView.OnItemClickListener {
    private static final int ARGS_WHAT = 0x1101;
    private static final int DELAY_TIME = 2000;
    private HeaderGridView headerGridView;
    private ViewPager viewPager;
    private VolleySingleton volleyQueue;
    private HeaderGridViewAdapter adapter;
    private HeaderViewPagerAdapter pagerAdapter;
    private HandlerThread mHandlerThread;
    private Handler mHandler;
    private Handler mainHandler = new Handler();
    private boolean controlThread = false;
    private int index = 1;
    private String URL_PATH = "http://api.dongqiudi.com/tabs_static/2/iphone/"+index+".json";
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        headerGridView = findView(R.id.headerGridView);
        View headerView = getLayoutInflater().inflate(R.layout.header_viewpager,headerGridView,false);
        viewPager = (ViewPager) headerView.findViewById(R.id.viewPager);
        headerGridView.addHeaderView(headerView);
        adapter = new HeaderGridViewAdapter(this);
        headerGridView.setAdapter(adapter);
        pagerAdapter = new HeaderViewPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        headerGridView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        initNetwork();
        initThread();
    }

    private void initThread() {
        mHandlerThread = new HandlerThread("loop_viewPager");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                runChangePager();
                if (controlThread) {
                    mHandler.sendEmptyMessageDelayed(ARGS_WHAT, DELAY_TIME);
                }
            }
        };
    }

    private void runChangePager() {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                int index = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++index);
            }
        });
    }

    private void initNetwork() {
        showDialog();
        GoNet.startRequest(URL_PATH, BallEntity.class, new NetInterface<BallEntity>() {
            @Override
            public void onSuccess(BallEntity response) {
                List<RecommendEntity> recommends = response.getRecommend();
                List<RecommendEntity> articles = response.getArticles();
                adapter.addRefreshData(articles);
                pagerAdapter.addRefreshData(recommends);
                viewPager.setCurrentItem(DELAY_TIME);
                dismissDialog();
            }

            @Override
            public void onError() {
                dismissDialog();
                toastError();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        controlThread = true;
        mHandler.sendEmptyMessage(ARGS_WHAT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        controlThread = false;
        mHandler.removeMessages(ARGS_WHAT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        volleyQueue.removeRequest(tag);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActivityControl.finishAll();
    }
}

package com.lanou3g.record.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou3g.record.R;
import com.lanou3g.record.model.entity.BallEntity;
import com.lanou3g.record.presenter.RoundPresenter;
import com.lanou3g.record.ui.RoundViewInterface;
import com.lanou3g.record.ui.adapter.HeaderGridViewAdapter;
import com.lanou3g.record.ui.adapter.HeaderViewPagerAdapter;
import com.lanou3g.record.view.HeaderGridView;
import com.lanou3g.record.view.SwipeRefreshLoadingLayout;

/**
 * 本类由: Risky 创建于: 15/10/29.
 * <p>继承基类,用于处理有可能发生的内存泄露.
 * <p>实现接口:下拉刷新和上拉加载的接口;View层的接口,泛型为数据的实体类类型
 */
public class RoundMVPActivity
        extends AbsMVPBaseActivity<RoundViewInterface, RoundPresenter>
        implements RoundViewInterface<BallEntity>, SwipeRefreshLoadingLayout.OnRefreshListener, SwipeRefreshLoadingLayout.OnLoadListener {
    /** 用于标记加载时的页数 */
    private int index = 1;
    private HeaderGridView headerGridView;
    private SwipeRefreshLoadingLayout rlLayout;
    private ViewPager viewPager;
    private HeaderGridViewAdapter adapter;
    private HeaderViewPagerAdapter pagerAdapter;

    @Override
    protected RoundPresenter createPresenter() {
        // 初始化Presenter,将本类通过构造方法传递给Presenter
        // *****此处的this代表View层接口类型的数据,非Context*****
        return new RoundPresenter();
    }

    @Override
    protected int getLayout() {
        // 绑定布局
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        // 初始化一系列的控件
        rlLayout = findView(R.id.rlLayout);
        rlLayout.setColor(
                R.color.refresh_load_layout_first,
                R.color.refresh_load_layout_second,
                R.color.refresh_load_layout_third,
                R.color.refresh_load_layout_fourth
        );
        rlLayout.setOnRefreshListener(this);
        rlLayout.setOnLoadListener(this);
        headerGridView = findView(R.id.headerGridView);
        View headerView = getLayoutInflater().inflate(R.layout.header_viewpager, headerGridView, false);
        viewPager = (ViewPager) headerView.findViewById(R.id.viewPager);
        headerGridView.addHeaderView(headerView);
        // 初始化GridView和ViewPager的适配器,并进行绑定
        adapter = new HeaderGridViewAdapter(this);
        headerGridView.setAdapter(adapter);
        pagerAdapter = new HeaderViewPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void initData() {
        // 开始执行Presenter的逻辑
        mPresenter.startNetConnect(getUrl(index));
    }

    @Override
    public void showLoading() {
        // 显示基类中写好的Dialog
        showDialog();
    }

    @Override
    public void hideLoading() {
        // 隐藏Dialog
        dismissDialog();
    }

    /**
     * 停止刷新和加载
     */
    private void stopRL() {
        rlLayout.setRefreshing(false);
        rlLayout.setLoading(false);
    }

    @Override
    public void showRefreshData(BallEntity result) {
        // 将刷新的实体类中的数据展示出来
        // 没有必要考虑数据是从网络获取的,还是从数据库读取的
        adapter.addRefreshData(result.getArticles());
        pagerAdapter.addRefreshData(result.getRecommend());
        stopRL();
    }

    @Override
    public void showLoadData(BallEntity result) {
        // 展示上拉加载之后得到的数据
        adapter.addLoadDatas(result.getArticles());
        stopRL();
    }

    @Override
    public void dealError() {
        // 当出错的时候执行
        toastError();
    }

    @Override
    public void onLoad() {
        // 加载的时候会执行此方法,每次加载,页码+1
        index++;
        // Presenter的加载数据方法
        mPresenter.load(getUrl(index));
    }

    @Override
    public void onRefresh() {
        // 刷新的时候执行,每次刷新,重置页码为1
        index = 1;
        // Presenter的刷新数据方法
        mPresenter.refresh(getUrl(index));
    }

    private String getUrl(int page) {
        return "http://api.dongqiudi.com/tabs_static/2/iphone/" + page + ".json";
    }
}

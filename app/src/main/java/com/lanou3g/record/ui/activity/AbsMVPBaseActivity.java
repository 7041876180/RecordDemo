package com.lanou3g.record.ui.activity;

import com.lanou3g.record.presenter.BasePresenter;

/**
 * 本类由: Risky 创建于: 15/10/29.
 * Activity的基类,处理有可能出现的Activity被Presenter占用出现的内存泄露.
 */
public abstract class AbsMVPBaseActivity<V,T extends BasePresenter<V>> extends AbsBaseActivity {

    /** 声明Presenter对象 */
    protected T mPresenter;

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    /**
     * 创建Presenter
     * @return
     */
    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 清理引用对象
        mPresenter.detachView();

    }
}

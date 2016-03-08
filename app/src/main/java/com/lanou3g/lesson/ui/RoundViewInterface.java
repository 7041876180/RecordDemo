package com.lanou3g.lesson.ui;

/**
 * 本类由: Risky 创建于: 15/10/29.
 * <p>View(也就是Activity或者Fragment)层的接口
 * <p>公布抽象方法,用于处理显示的逻辑</p>
 *
 * @param <T> 泛型为所要处理的数据的实体类类型
 */
public interface RoundViewInterface<T> {
    /**
     * 显示Loading
     */
    void showLoading();

    /**
     * 隐藏Loading
     */
    void hideLoading();

    /**
     * 显示刷新的数据
     * @param result 参数为刷新时返回的网络数据实体类对象
     */
    void showRefreshData(T result);

    /**
     * 显示加载的数据
     * @param result 参数为加载时返回的网络数据实体类对象
     */
    void showLoadData(T result);

    /**
     * 处理一些错误
     */
    void dealError();
}

package com.lanou3g.record.model;

import com.lanou3g.record.presenter.DBReadable;
import com.lanou3g.record.presenter.NetworkConnect;

/**
 * Created by Risky on 15/10/29.
 */
public interface RoundModelInterface {
    /**
     * 刷新网络数据
     * @param refreshUrl
     * @param refreshConnect
     */
    void startRefreshConnect(String refreshUrl, NetworkConnect refreshConnect);

    /**
     * 加载网络数据
     * @param loadUrl
     * @param loadConnect
     */
    void startLoadConnect(String loadUrl,NetworkConnect loadConnect);

    /**
     * 读取数据库
     * @param readable
     */
    void readDB(DBReadable readable);
}

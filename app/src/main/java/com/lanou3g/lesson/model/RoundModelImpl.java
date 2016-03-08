package com.lanou3g.lesson.model;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.lesson.dao.LessonDBHoldable;
import com.lanou3g.lesson.dao.RecommendHelper;
import com.lanou3g.lesson.model.entity.BallEntity;
import com.lanou3g.lesson.model.entity.greendao.RecommendEntity;
import com.lanou3g.lesson.presenter.DBReadable;
import com.lanou3g.lesson.presenter.NetworkConnect;
import com.lanou3g.lesson.volley.VolleySingleton;

import java.util.List;

/**
 * 本类由: Risky 创建于: 15/10/29.
 * 本类可以再次进行优化
 */
public class RoundModelImpl implements RoundModelInterface {
    private LessonDBHoldable dbHoldable;

    /**
     * 构造方法,传递参数初始化数据库操作类
     *
     */
    public RoundModelImpl() {
        this.dbHoldable = new RecommendHelper();
    }

    private void startConnect(String url, final NetworkConnect connect, final boolean isRefresh) {
        VolleySingleton.getInstance()._addRequest(url, BallEntity.class, new Response.Listener<BallEntity>() {
            @Override
            public void onResponse(BallEntity response) {
                if (response != null) {
                    // 数据成功返回时,回调NetworkRefreshConnect接口的成功方法.
                    // NetworkRefreshConnect接口由Presenter实现
                    if (isRefresh) {
                        connect.onRefreshCompleted(response);
                        // 数据库操作类的存数据方法
                        dbHoldable.saveData(response.getRecommend(), LessonDBHoldable.ARGS_RECOMMEND);
                    } else {
                        connect.onLoadCompleted(response);
                    }
                    dbHoldable.saveData(response.getArticles(), LessonDBHoldable.ARGS_ARTICLE);

                } else {
                    // 失败,回调接口的失败方法
                    connect.onFailed();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 失败,回调接口的失败方法
                connect.onFailed();
            }
        });
    }

    @Override
    public void startRefreshConnect(String refreshUrl, final NetworkConnect refreshConnect) {
        startConnect(refreshUrl, refreshConnect, true);
    }

    @Override
    public void startLoadConnect(String loadUrl, final NetworkConnect loadConnect) {
        startConnect(loadUrl, loadConnect, false);

    }

    @Override
    public void readDB(DBReadable readable) {
        BallEntity result = new BallEntity();
        List<RecommendEntity> articles = dbHoldable.readData(RecommendHelper.ARGS_ARTICLE);
        List<RecommendEntity> recommends = dbHoldable.readData(RecommendHelper.ARGS_RECOMMEND);
        result.setArticles(articles);
        result.setRecommend(recommends);
        // 数据库读取完毕后回调
        readable.onReadDB(result);
    }
}

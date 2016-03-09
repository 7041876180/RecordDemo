package com.lanou3g.record.dao;

import java.util.List;

/**
 * Created by Risky on 15/10/29.
 */
public interface LessonDBHoldable<T> {
    String ARGS_ARTICLE = "article";
    String ARGS_RECOMMEND = "recommend";

    /**
     * 通过标签存储数据到数据库
     * @param entities 解析完毕的实体类集合
     * @param tag 数据的标记
     */
    void saveData(List<T> entities,String tag);

    /**
     * 根据标记读取数据库
     * @param tag 数据的标记
     * @return 返回值为读取之后的集合
     */
    List<T> readData(String tag);
}

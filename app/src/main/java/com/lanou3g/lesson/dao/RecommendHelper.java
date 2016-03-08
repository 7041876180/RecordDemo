package com.lanou3g.lesson.dao;

import com.lanou3g.lesson.model.entity.greendao.RecommendEntity;
import com.lanou3g.lesson.model.entity.greendao.RecommendEntityDao;

import java.util.List;

/**
 * 本类由: Risky 创建于: 15/10/29.
 * 实现接口,泛型为数据库中对应表的实体类
 */
public class RecommendHelper implements LessonDBHoldable<RecommendEntity> {

    private DaoSingleton instance = DaoSingleton.getInstance();
    private RecommendEntityDao recommendDao;

    public RecommendHelper() {
        this.recommendDao = instance.getRecommendDao();
    }

    @Override
    public void saveData(List<RecommendEntity> entities, String tag) {
        // 将集合中的数据的每个元素添加一条属性tag,用于区分集合
        for (RecommendEntity entity : entities) {
            entity.setTag(tag);
        }
        // 将集合插入表
        recommendDao.insertOrReplaceInTx(entities);
    }

    @Override
    public List<RecommendEntity> readData(String tag) {
        // 通过tag读取相应的数据集合
        return recommendDao.queryBuilder().where(RecommendEntityDao.Properties.Tag.eq(tag)).list();
    }
}

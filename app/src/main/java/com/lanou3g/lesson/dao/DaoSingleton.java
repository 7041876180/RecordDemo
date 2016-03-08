package com.lanou3g.lesson.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lanou3g.lesson.application.LessonApplication;
import com.lanou3g.lesson.model.entity.greendao.DaoMaster;
import com.lanou3g.lesson.model.entity.greendao.DaoSession;
import com.lanou3g.lesson.model.entity.greendao.RecommendEntityDao;

/**
 * Created by Risky on 15/10/29.
 */
public class DaoSingleton {
    private static final String DATABASE_NAME = "daodemo.db";

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;
    private DaoMaster.DevOpenHelper helper;
    private RecommendEntityDao recommendDao;

    private DaoSingleton() {
        context = LessonApplication.getContext();
    }

    public static DaoSingleton getInstance() {
        return SingletonHolder.instance;
    }
    private static final class SingletonHolder{
        private static final DaoSingleton instance = new DaoSingleton();
    }

    private DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME, null);
        }
        return helper;
    }

    private SQLiteDatabase getDb() {
        if (db == null) {
            db = getHelper().getWritableDatabase();
        }
        return db;
    }

    private DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getDb());
        }
        return daoMaster;
    }

    private DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }

    public RecommendEntityDao getRecommendDao() {
        if (recommendDao == null) {
            recommendDao = getDaoSession().getRecommendEntityDao();
        }
        return recommendDao;
    }
}

package com.lanou3g.record.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 本类由: Risky 创建于: 16/1/12.
 */
public class ControlTest {
    private Context context;

    public ControlTest(Context context) {
        this.context = context;
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,"test.db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        DaoSession session = master.newSession();
        DaoTestDao testDao = session.getDaoTestDao();

    }
}

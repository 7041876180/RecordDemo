package com.lesson.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * 本类由: Risky 创建于: 16/1/12.
 */
public class Generator {
    public static void main(String[] args){
        Schema schema = new Schema(1,"com.lanou3g.lesson.test");
        Entity entity = schema.addEntity("DaoTest");
        entity.addLongProperty("_id").primaryKey().autoincrement();
        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

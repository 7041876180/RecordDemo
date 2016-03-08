package com.lesson.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class LessonDaoGenerator {

    public static void main(String args[]){
        Schema schema = new Schema(1, "com.lanou3g.lesson.model.entity.greendao");
        addBallDatabase(schema);
        try {
            // 创建实体类.第二个参数填Android Module的路径
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addBallDatabase(Schema schema) {
        // 指定需要生成实体类的类名,类名确定了那么表名也是根据这个类名来自动命名的,例如下面这个,生成的表名叫做person_entity
        Entity entity = schema.addEntity("RecommendEntity");
        // 指定自增长主键
//        entity.addIdProperty().autoincrement().primaryKey();
        entity.addLongProperty("id").primaryKey();
        // 添加类的属性,根据属性生成数据库表中的字段
        entity.addStringProperty("title");
        entity.addStringProperty("tag");
        entity.addStringProperty("share_title");
        entity.addStringProperty("description");
        entity.addStringProperty("share");
        entity.addStringProperty("thumb");
        entity.addStringProperty("published_at");
        entity.addStringProperty("url");
        entity.addStringProperty("channel");
        entity.addStringProperty("label");
        entity.addStringProperty("label_color");
        entity.addStringProperty("scheme");
        entity.addIntProperty("comments_total");
    }
}

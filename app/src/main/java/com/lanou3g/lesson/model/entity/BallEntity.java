package com.lanou3g.lesson.model.entity;

import com.lanou3g.lesson.model.entity.greendao.RecommendEntity;

import java.util.List;

/**
 * Created by Risky on 15/10/19.
 */
public class BallEntity {

    private int id;
    private String label;
    private int total;
    private int max;
    private String prev;
    private int min;
    private String next;
    private List<RecommendEntity> articles;
    private List<RecommendEntity> recommend;

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setArticles(List<RecommendEntity> articles) {
        this.articles = articles;
    }

    public void setRecommend(List<RecommendEntity> recommend) {
        this.recommend = recommend;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getTotal() {
        return total;
    }

    public int getMax() {
        return max;
    }

    public String getPrev() {
        return prev;
    }

    public int getMin() {
        return min;
    }

    public String getNext() {
        return next;
    }

    public List<RecommendEntity> getArticles() {
        return articles;
    }

    public List<RecommendEntity> getRecommend() {
        return recommend;
    }

}

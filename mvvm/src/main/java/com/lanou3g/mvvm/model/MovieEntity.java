package com.lanou3g.mvvm.model;

import java.util.List;

/**
 * 本类由: Risky57 创建于: 16/4/3.
 */
public class MovieEntity {

    private int count;
    private int start;
    private int total;
    private List<MovieInfo> subjects;
    private String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MovieInfo> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<MovieInfo> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

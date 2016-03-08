package com.example;

/**
 * 本类由: Risky 创建于: 16/1/5.
 */
public class TestSingleton {

    private TestSingleton(){
    }

    private static final class Holder{
        private static final TestSingleton instance = new TestSingleton();

    }

    public static TestSingleton getInstance() {
        return Holder.instance;
    }
}

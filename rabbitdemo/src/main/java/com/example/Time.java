package com.example;

/**
 * 本类由: Risky 创建于: 16/1/5.
 */
public class Time {
    public static void runTime(RabbitList rl, int n) {
        for (int i = 1; i <= n; i++) {
            rl.bornEach();
            rl.addEachAge();
            System.out.println("第" + i + "个月月末的时候,兔子窝中兔子的数目为" + rl.size());
        }

    }
}
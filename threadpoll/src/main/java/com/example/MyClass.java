package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyClass {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
//            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
//                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
//        executor.shutdownNow();
    }

    private static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            try {
                long sleep = (long) (1000 + Math.random() * 2000l);
                System.out.println("正在执行task " + taskNum + "休眠-->" + sleep + "秒");
                Thread.currentThread().sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + taskNum + "执行完毕");
        }
    }
}

package com.example;


public class MyClass {
    public static void main(String [] args){
        RabbitList rl=new RabbitList();//建立一个兔子窝
        rl.add(new Rabbit());//将一只兔子装入兔子窝
        Time.runTime(rl,10);
    }
}

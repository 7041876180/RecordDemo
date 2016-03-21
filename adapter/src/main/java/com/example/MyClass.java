package com.example;

public class MyClass {
    public static void main(String []args){
        FlyAnimal bird = new Bird();
        RunAnimal pig = new Pig();

        FlyBehaviour flyBehaviour = new FlyBehaviour();

        // 适配器模式
        PigCanFlyAdapter pigAdapter = new PigCanFlyAdapter(pig);

        // 正常
        flyBehaviour.flyAction(bird);
        // 通过适配器,让猪也满足条件
        flyBehaviour.flyAction(pigAdapter);


    }
}

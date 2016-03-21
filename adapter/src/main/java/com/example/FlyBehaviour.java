package com.example;

/**
 * 本类由: Risky57 创建于: 16/3/21.
 */
public class FlyBehaviour {

    public void flyAction(FlyAnimal flyAnimal){

        System.out.print("平时在");
        flyAnimal.flyWithWing();
        System.out.print("还");
        flyAnimal.lifeMode();
    }

}

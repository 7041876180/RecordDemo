package com.example;

/**
 * 本类由: Risky57 创建于: 16/3/21.
 */
public class PigCanFlyAdapter implements FlyAnimal {
    RunAnimal runAnimal;

    public PigCanFlyAdapter(RunAnimal runAnimal) {
        this.runAnimal = runAnimal;
    }

    @Override
    public void flyWithWing() {
        runAnimal.runWithLegs();
    }

    @Override
    public void lifeMode() {
        runAnimal.lifeMode();
    }
}

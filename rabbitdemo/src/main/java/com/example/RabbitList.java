package com.example;

import java.util.ArrayList;

/**
 * 本类由: Risky 创建于: 16/1/5.
 */
public class RabbitList extends ArrayList {
    public void addEachAge() {
        Rabbit r = null;
        for (int i = 0; i < this.size(); i++) {
            r = (Rabbit) this.get(i);
            r.addAge();

        }

    }

    public void bornEach() {
        Rabbit r = null;
        Rabbit rb = null;
        ArrayList tempAr = new ArrayList();
        for (int i = 0; i < this.size(); i++) {
            r = (Rabbit) this.get(i);
            rb = r.born();
            if (rb != null)
                tempAr.add(rb);
        }
        for (int i = 0; i < tempAr.size(); i++) {
            this.add(tempAr.get(i));
        }


    }
}
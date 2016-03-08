package com.example.command;

import com.example.equip.Light;

/**
 * 本类由: Risky 创建于: 15/12/15.
 */
public class LightOnCommand extends OnCommand<Light> {

    public LightOnCommand(Light light) {
        super(light);
    }
}

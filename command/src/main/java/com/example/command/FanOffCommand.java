package com.example.command;

import com.example.equip.Fan;

/**
 * 本类由: Risky 创建于: 15/12/15.
 */
public class FanOffCommand extends OffCommand<Fan> {
    public FanOffCommand(Fan fan) {
        super(fan);
    }
}

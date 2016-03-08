package com.example.command;

import com.example.Command;
import com.example.equip.Equip;

/**
 * 本类由: Risky 创建于: 15/12/15.
 */
public class OnCommand<T extends Equip> implements Command {
    private T t;

    public OnCommand(T t) {
        this.t = t;
    }

    @Override
    public void execute() {
        t.on();
    }

    @Override
    public void undo() {
        t.off();
    }
}

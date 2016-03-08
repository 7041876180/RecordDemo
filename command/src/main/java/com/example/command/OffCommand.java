package com.example.command;

import com.example.Command;
import com.example.equip.Equip;

/**
 * 本类由: Risky 创建于: 15/12/15.
 */
public class OffCommand<T extends Equip> implements Command {
    private T t;

    public OffCommand(T t) {
        this.t = t;
    }

    @Override
    public void execute() {
        t.off();
    }

    @Override
    public void undo() {
        t.on();
    }
}

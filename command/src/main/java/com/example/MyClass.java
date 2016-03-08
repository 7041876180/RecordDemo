package com.example;

import com.example.command.FanOffCommand;
import com.example.command.FanOnCommand;
import com.example.command.LightOffCommand;
import com.example.command.LightOnCommand;
import com.example.equip.Fan;
import com.example.equip.Light;

import java.util.Scanner;

public class MyClass implements Foo{
    public static void main(String [] args){
        CommandControl control = new CommandControl();
        Light light = new Light("客厅顶灯");
        Fan fan = new Fan("卧室风扇");
        LightOnCommand lightOn = new LightOnCommand(light);
        LightOffCommand lightOff = new LightOffCommand(light);
        FanOnCommand fanOn = new FanOnCommand(fan);
        FanOffCommand fanOff = new FanOffCommand(fan);
        control.setCommand(0,lightOn,lightOff);
        control.setCommand(1,fanOn,fanOff);
        control.onPressed(0);
        control.onPressed(1);
        control.undoPressed();
        control.offPressed(0);
        control.undoPressed();

//        char a = (char)true;
//        System.out.println(a);
        byte [] arr1,arr2 [];
//        arr1 = arr2;
        MyClass c = new MyClass();
        int i ;
        i = c.k;
        i = MyClass.k;
        i = Foo.k;

//        Math.abs()

    }
}

interface Foo{
    int k = 0;
}

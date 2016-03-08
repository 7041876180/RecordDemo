package com.example;

import java.util.*;
import java.util.Observer;

public class MyClass {
    public static void main(String []args){

        java.util.Observable o = null;
        Observer observer = null;

        SubjectData subject = new SubjectData();
        OoneImpl one = new OoneImpl();
        OtwoImpl two = new OtwoImpl();

        one.register(subject);
        two.register(subject);

        subject.sendMsg("hello world!");

        one.unRegister();

        subject.sendMsg("hello Android~!");

    }
}

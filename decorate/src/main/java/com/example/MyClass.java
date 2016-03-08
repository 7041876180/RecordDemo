package com.example;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MyClass {
    public static void main(String [] args) throws FileNotFoundException {
        Beverage esp = new Espresso();
        esp = new Mocha(esp);
        esp = new Mocha(esp);

        System.out.println("价格为:"+esp.cost());
        System.out.println("描述为:"+esp.getDescription());


        InputStream fis = new FileInputStream("");
        fis = new BufferedInputStream(fis);
        fis = new DataInputStream(fis);



    }
}

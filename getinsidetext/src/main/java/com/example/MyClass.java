package com.example;

import java.util.List;
import java.util.Map;

public class MyClass {
    public static void main(String[] args) {
        String arg = "dalia";
        ParseStr parse = new ParseStr();
        for (int i = 0; i < ParseStr.ARGS.length; i++) {
            boolean flag = parse.insideStr(ParseStr.ARGS[i], arg);
            System.out.println(ParseStr.ARGS[i] + "-->" + arg + "-->" + flag);
        }

        String str = "石家庄市";
                   //0 1 2 3 4 5 6 7
        List<Map<String,Integer>> list = parse.getIndex(str,"石庄市");
        System.out.println(list);
    }
}

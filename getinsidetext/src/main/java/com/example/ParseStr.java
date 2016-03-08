package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本类由: Risky 创建于: 15/12/18.
 */
public class ParseStr {
    public static final String[] ARGS =
            {"dalian", "shenyang", "jinzhou", "tieling", "fushun", "anshan"};
    public static final String START = "start";
    public static final String END = "end";

    public boolean insideStr(String string, String arg) {
        boolean flag;
        int temp = 0;
        if (string.contains(arg)) return true;
        for (int i = 0; i < arg.length(); i++) {
            flag = false;
            char tempArg = arg.charAt(i);
            for (int j = temp; j < string.length(); j++) {
                char tempStr = string.charAt(j);
                if (tempArg == tempStr) {
                    flag = true;
                    temp = j + 1;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    public List<Map<String, Integer>> getIndex(String string, String arg) {
        List<Map<String, Integer>> list = new ArrayList<>();
        if (string.contains(arg)) {
            int start = string.indexOf(arg);
            int end = start + arg.length();
            Map<String, Integer> map = new HashMap<>();
            map.put(START, start);
            map.put(END, end);
            list.add(map);
            return list;
        } else {
            int temp = 0;
            for (int i = 0; i < arg.length(); i++) {
                String tempArg = arg.charAt(i) + "";
                int start = temp + string.indexOf(tempArg);
                int end = start + 1;
                Map<String, Integer> map = new HashMap<>();
                map.put(START, start);
                map.put(END, end);
                list.add(map);
                string = string.substring(end - temp, string.length());
                temp = 0;
                temp += end;
            }
            return list;
        }
    }
}

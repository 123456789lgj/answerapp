package com.lgj.myflowdemo.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo1 {
    Student hehe;

    public static void main(String[] args) {
        String str = "13.你好吗";
        String zhengli = zhengli(str);
        System.out.println(zhengli);
    }

    public static String zhengli(String str) {
        String temp = "";
        Pattern pattern = Pattern.compile("[1-9]*");
        Matcher isNum = pattern.matcher(str.charAt(0) + "");
        if (isNum.matches()) {
            if (str.length() > 1) {
                temp = zhengli(str.substring(1));
            }
        } else {
            System.out.println("nihao :" + str);

        }
        return temp;
    }


    public void systemOut() {
        System.out.println(hehe);
    }
}

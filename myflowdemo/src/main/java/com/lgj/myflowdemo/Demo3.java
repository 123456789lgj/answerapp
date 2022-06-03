package com.lgj.myflowdemo;

import java.math.BigInteger;

public class Demo3 {
    public static void main(String[] args) {
//        Integer integer = Integer.valueOf("128");
//        int hehe = integer/16;
//        System.out.println(hehe);
        String str = "128a#0#255#255";
//        String str = "100#101#1#5";
        int nihao = str.indexOf(" ");
        char[] chars = str.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            char cc = chars[i];
            if (Character.isDigit(cc) || cc == '#') {

            }else {
                System.out.println("invalid IP");
                return;
            }
        }

        String[] arr = str.split("#");
        if (arr == null || arr.length != 4) {
            System.out.println("invalid IP");
            return;
        }
        if (Integer.valueOf(arr[0]) > 128  && Integer.valueOf(arr[0]) < 1) {
            System.out.println("invalid IP");
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            if (Integer.valueOf(arr[i]) > 255  && Integer.valueOf(arr[i]) < 1) {
                System.out.println("invalid IP");
                return;
            }
        }
        String temp = "";
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            String s2 = Integer.toHexString(Integer.valueOf(s));
            if (s2.length() == 1) {
                s2 = "0" + s2;
            }
            System.out.println(s2);
            temp+=s2;
        }
        System.out.println(temp);
        try {
            int i = Integer.parseInt(temp, 16);
            System.out.println(i);
            return;
        }catch (Exception e){

        }
        System.out.println("invalid IP");

//        Integer.parseInt(temp,16);
//        int i = BigInteger.parseInt(temp,16);
//        System.out.println(i);


    }
}

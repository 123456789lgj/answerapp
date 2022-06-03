package com.lgj.myflowdemo;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();

        String str = "abc";
        String str2 = str.replaceAll("[1-9]", "");
        if (str.equals(str2)) {
            System.out.println(0);
            return;
        }
        char[] chars = str.toCharArray();
        int temp = 0;
        int max = 0;
        for (int i = 0; i < chars.length -1; i++) {
            char c = chars[i];
            char c2 = chars[i + 1];
            if (Character.isDigit(c) && Character.isDigit(c2) && Integer.valueOf(c2) >= Integer.valueOf(c)){
                temp++;
            } else {
                if (temp >= max) {
                    max = temp;
                }
                temp = 0;
            }
        }
        if(temp != 0 && temp >= max) {
            max = temp;
        }
        System.out.println(max+1);

    }
}

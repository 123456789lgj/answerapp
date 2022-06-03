package com.lgj.myflowdemo;

import java.util.ArrayList;

public class Demo2 {
    public static void main(String[] args) {
        int[][] arr = {{2,1,0,3},{0,1,2,1},{0,3,0,0},{0,0,0,0}};
//        int[][] arr = new int[4][4];
        ArrayList<Ren> list = new ArrayList<>();
        ArrayList<Can> list2 = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == 2) {
                    System.out.println(" i: " + i + " j :" + j);
                    Ren ren = new Ren();
                    ren.i = i;
                    ren.j = j;
                    list.add(ren);
                }
                if (arr[i][j] == 3) {
                    System.out.println(" i: " + i + " j :" + j);
                    Can can = new Can();
                    can.i = i;
                    can.j = j;
                    list2.add(can);
                }
            }
        }
        System.out.println(0);
    }
    static class Ren{
        int i;
        int j;
    }
    static class Can{
        int i;
        int j;
    }
}

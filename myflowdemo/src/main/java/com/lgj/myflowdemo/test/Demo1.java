package com.lgj.myflowdemo.test;

public class Demo1 {
    Student hehe;
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.hehe = new Student(demo1, "你好");

    }

    public void systemOut() {
        System.out.println(hehe);
    }
}

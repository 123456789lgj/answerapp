package com.lgj.myflowdemo.test;

public class Student {
    String name;
    Demo1 demo1;
    public Student(Demo1 demo1, String name) {
        this.demo1 = demo1;
        this.name = name;
        Teacher teacher = new Teacher(this, name);
        teacher.addString(name);
    }

    public void notifyData() {
        demo1.systemOut();
    }
}

package com.lgj.myflowdemo.test;

public class Teacher {
    String name;
    Student student;
    public Teacher(Student student, String name){
        this.name = name;
        this.student = student;
    }

    public void addString(String name) {
        System.out.println("lgj Teacher :" + name);
        initData();
    }

    private void initData() {
        System.out.println("lgj Teacher initData:");
        student.notifyData();
    }
}

package com.servlet.http.usetemplate;

public class Student extends PersonTemplate{
    @Override
    public void do1() {
        System.out.println("学生起床");
    }

    @Override
    public void do2() {
        System.out.println("学生上学");
    }

    @Override
    public void do3() {
        System.out.println("学生放学");
    }
}

package com.servlet.http.usetemplate;

public class Worker extends PersonTemplate{
    @Override
    public void do1() {
        System.out.println("工人起床");
    }

    @Override
    public void do2() {
        System.out.println("工人上班");
    }

    @Override
    public void do3() {
        System.out.println("工人下班");
    }
}

package com.servlet.http.usetemplate;

public class Test {

    public static void main(String[] args) {

        //用模板类定义一个Student对象，明显是多态的运用
        PersonTemplate student = new Student();
        PersonTemplate worker = new Worker();

        //子类都可以调用模板方法templateMethod
        //模板发设计模式就是基于多态来实现的
        student.templateMethod();
        worker.templateMethod();

        //其特点就是，这个模板类的子类的核心算法骨架相同，保存在一个模板方法中，这个模板方法
        //在子类中无需重写，模板方法中的内容是各个Servlet实现类中都固定死，模板嘛，顾名思义
        //需要程序员编写的就是模板方法中调用的其他方法，这些方法就是需要程序员写的业务代码
        //且这些方法在子类中各自重写实现，子类含义不同，实现步骤就有所不同
    }

}

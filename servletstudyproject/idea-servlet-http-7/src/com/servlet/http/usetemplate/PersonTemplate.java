package com.servlet.http.usetemplate;


/**
 * PersonTemplate符合模板方法设计
 * PersonTemplate是一个模板类，抽象类
 */
public abstract class PersonTemplate {

    /**
     * templateMethod是一个模板方法，它定义算法核心骨架，具体实现步骤延迟到模板类的子类中实现
     * 为了使核心算法骨架受到保护，模板方法一般被final修饰
     * 作用：算法骨架不需要在每个业务类中编写了，只需要在模板方法中编写一次
     */
    public void templateMethod(){

        //核心算法骨架
        do1();
        do2();
        do3();

    }

    /**
     * 下面方法就是具体的实现步骤之一，可以延迟到子类中完成
     * 通常是抽象方法，所以模板类也是抽象类
     */
    public abstract void do1();
    public abstract void do2();
    public abstract void do3();

}

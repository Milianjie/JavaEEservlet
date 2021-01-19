    package com.javaweb.servlet;

    import javax.servlet.ServletException;
    import javax.servlet.ServletRequest;
    import javax.servlet.ServletResponse;
    import java.io.IOException;
    import java.io.PrintWriter;

    public class HelloServlet extends GenericServlet{

        //子类继承父类，将父类中的方法和变量都继承了，若没有重写父类的方法，子类对象调用某个方法时是执行父类中的
        //方法代码，这可以在父类的方法中添加断点测试，若重写了父类的某个方法，子类对象调用该方法时执行的是重写后的
        //方法代码，此时若想访问父类中的该方法，可以在重写的方法中用super.方法名的方式调用父类中的该方法。super表示
        //子类对象中的父类型特征，不是一个父类型对象的引用

        @Override
        public void init() {
            System.out.println("Hello init...");
        }

        @Override
        public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

            servletResponse.setContentType("text/html;charset=UFT-8");
            PrintWriter out = servletResponse.getWriter();
            out.print("hello servlet!");

        }
    }

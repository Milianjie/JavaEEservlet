package com.javaweb.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * GenericServlet是一个适配器类，这个适配器是一个Servlet
 * 以后程序员无需实现Servlet接口，只需要继承GenericServlet抽象类，单单重写service方法就行了
 */

public abstract class GenericServlet implements Servlet {

    private ServletConfig config;

    @Override
    //有时候我们希望在服务器启动阶段执行一段特殊的代码，如果我们在子类中重写init方法将特殊的代码写进去，那么里面
    //的赋值语句就没法执行了，config是私有的就没法得到成员的ServletConfig对象，然而把定义代码和赋值代码搬到子类中
    //显得有些多余，就不像适配器模式了，那么我们可以在适配器类中定义一个init的无参数方法，在适配器中的init有参数方法
    //中去调用，在子类中覆盖init无参方法，将特殊程序写到重写的无参的init方法中
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
        this.init();
    }

    //定义一个无参的init方法，以防服务器启动阶段有特殊需求执行代码

    /**
     * 在初始化时刻需要执行一段特殊的程序，在子类中重写下面无参数的init方法
     */
    public void init(){}

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;

    @Override
    //该方法是返回一段信息，该信息包含项目的作者、版本以及版权等
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    //-------------------------以下所有方法是扩展方法---------------------------

    public ServletContext getServletContext(){
        return config.getServletContext();
    }

    //.......
}

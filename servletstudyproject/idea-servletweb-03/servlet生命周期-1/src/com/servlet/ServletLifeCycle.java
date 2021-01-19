package com.servlet;

import javax.servlet.*;
import java.io.IOException;

public class ServletLifeCycle implements Servlet {

    public ServletLifeCycle(){
        System.out.println("ServletLifeCycle 无参数构造方法执行");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Method init executed！");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        System.out.println("Method service executed!");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Method destroy executed!");
    }
}

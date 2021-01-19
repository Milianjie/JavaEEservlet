package com.servlet.servletconfig;

import javax.servlet.*;
import java.io.IOException;

public class Bservlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        System.out.println("BServlet's ServletConfig = "+servletConfig.toString());
        //BServlet's ServletConfig = org.apache.catalina.core.StandardWrapperFacade@47eaa5bb

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

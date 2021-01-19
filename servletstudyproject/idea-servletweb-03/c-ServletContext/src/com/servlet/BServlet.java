package com.servlet;

import javax.servlet.*;
import java.io.IOException;

public class BServlet implements Servlet {

    private ServletConfig config = null;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        ServletConfig config = getServletConfig();
        ServletContext application = config.getServletContext();
//        System.out.println(application);

        //ServletContext对象范围中读取数据
        Object value = application.getAttribute("userObj");
        servletResponse.getWriter().print(value);//向浏览器中输出数据
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

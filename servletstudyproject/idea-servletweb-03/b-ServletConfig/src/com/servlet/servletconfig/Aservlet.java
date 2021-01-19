package com.servlet.servletconfig;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class Aservlet implements Servlet {

    private ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        this.config = servletConfig;
        System.out.println("AServlet's ServletConfig = "+servletConfig.toString());
        //AServlet's ServletConfig = org.apache.catalina.core.StandardWrapperFacade@56238272

    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        //更改输出格式类型
        servletResponse.setContentType("text/html;charset=UTF-8");
        //更改输出位置
        PrintWriter out = servletResponse.getWriter();

        //获取ServletConfig对象
        ServletConfig config = getServletConfig();

        //通过初始化参数的name获取value
//        String driver = config.getInitParameter("driver");
//        String url = config.getInitParameter("url");
//        String user = config.getInitParameter("user");
//        String password = config.getInitParameter("password");
//        out.print("driver="+driver);
//        out.print("<br>");
//        out.print("url="+url);
//        out.print("<br>");
//        out.print("user="+user);
//        out.print("<br>");
//        out.print("password="+password);

        //获取初始化参数的name
//        Enumeration<String> names = config.getInitParameterNames();
//        //遍历该集合，并通过name调用获取value，虽然该集合对象的遍历方法名字不一样，但底层还是一样的
//        while (names.hasMoreElements()){
//
//            String name = names.nextElement();
//            String value = config.getInitParameter(name);
//            out.print(name+" = "+value);
//            out.print("<br>");
//        }

        //获取Servlet Name
        String servletName = config.getServletName();
        out.print("<servlet-name>"+servletName+"</servlet-name>");//a
        out.print("<br>");

        //获取Servlet上下文的方法
        ServletContext application = config.getServletContext();
        out.print(application);//AServletorg.apache.catalina.core.ApplicationContextFacade@6b2c87d6

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

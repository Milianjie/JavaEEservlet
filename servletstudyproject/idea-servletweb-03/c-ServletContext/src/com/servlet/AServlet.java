package com.servlet;

import com.entity.User;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

public class AServlet implements Servlet {
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
        //System.out.println(application);

        //ServletContext接口中获取所有上下文初始化参数的name的方法
        Enumeration<String> names = application.getInitParameterNames();
        //遍历集合，并获取上下文初始化参数name对应的value
//        while (names.hasMoreElements()){
//            String name = names.nextElement();
//            String value = application.getInitParameter(name);
//            System.out.println(name+" = "+value);
//        }

        //获取文件绝对路径方法
//        String absolutePath = application.getRealPath("/index.html");//这样写必须webapp根路径(即WEB-INF目录上一级)下有该文件
//        System.out.println(absolutePath);

        //创建User对象，并且给成员变量赋值
        User user = new User();
        user.setUsercode("12335");
        user.setUsername("高比例");

        //给ServletContext范围存储数据
        application.setAttribute("userObj",user);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

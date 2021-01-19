package com.servlet.http;


import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServlet extends GenericServlet {


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        service(request,response);
    }

    //主要的算法股骨架，在Servlet规范中的HttpServlet类中是protected修饰的，当然为了保护也用final修饰
    public void service(HttpServletRequest request,HttpServletResponse response) throws IOException {

        String method = request.getMethod();

        if ("GET".equals(method)){
            doGet(request,response);
        } else if ("POST".equals(method)) {
            doPost(request,response);
        }

    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {

        response.getWriter().print("405: 请发送POST请求！");
        throw new  RuntimeException("405: 请发送POST请求!");

    }


    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.getWriter().print("405: 请发送GET请求！");
        throw new  RuntimeException("405: 请发送GET请求!");
    }

}

package com.servlet.httpservletrequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //获取上下文路径（webapp根路径）
        String webPath = request.getContextPath();
        out.print(webPath);
        out.print("<br>");

        //获取浏览器的请求方式
        String method = request.getMethod();
        out.print(method);
        out.print("<br>");

        //获取URI
        String uri = request.getRequestURI();
        out.print(uri);
        out.print("<br>");

        //获取URL
        StringBuffer url = request.getRequestURL();
        out.print(url);
        out.print("<br>");

        //获取Servlet Path
        String servletPath = request.getServletPath();
        out.print(servletPath);
        out.print("<br>");

        //获取客户端IP地址
        String ip = request.getRemoteAddr();
        out.print(ip);
        out.print("<br>");
    }
}

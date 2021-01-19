package com.servlet.http;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        //将两个形式参数强制转换成HttpServletRequest和HttpServletResponse类型
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //更改输出流位置以及输出信息数据格式和编码方式(注意：要先设置格式后再设置流）
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = servletResponse.getWriter();

        //获取请求方式的字符串返回
        String method = request.getMethod();
        out.print(method);
        out.print("<br>");

        //要求前台需要用POST请求访问这个实现类对象的资源
        if ("GET".equals(method)){
            //前端提供错误提示
            out.print("405-请发送POST请求！");
            //后台要报错误
            throw new RuntimeException("405-请发送POST请求！");
        } else if ("POST".equals(method)) {
            //程序正常运转结束
            out.print("正在登录，请稍后.......");
        }

    }
}

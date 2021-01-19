package com.servlet.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessMySelfSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取每次会话用户的IP地址
        String IP = request.getRemoteAddr();

        //获取每次会话用户的Session对象
        HttpSession session = request.getSession();

        //向session范围添加数据
        session.setAttribute("username","zhangsan");

        //输出
        System.out.println(IP + "'s session = "+session);

    }
}

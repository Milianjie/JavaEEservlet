package com.servlet.http;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RecieveCookiesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //从request对象中获取所有提交的Cookie，放进一个数组中
        Cookie[] cookies = request.getCookies();

        if (cookies != null){
            for (Cookie cookie:
                 cookies) {
                System.out.println(cookie.getName()+" = "+cookie.getValue());
            }
        }

    }
}

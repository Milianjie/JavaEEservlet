package com.servlet.http;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateAndSendCookieToBrowser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //创建Cookie对象
        Cookie cookie1 = new Cookie("username","zhangsan");
        Cookie cookie2 = new Cookie("password","123");

        //绑定Cookie的请求路径
//        cookie1.setPath(request.getContextPath()+"/king");
//        cookie2.setPath(request.getContextPath()+"/king");

        //设置Cookie有效时长
        cookie1.setMaxAge(60*60);//有效时长一个小时
        cookie2.setMaxAge(60*60*24);//有效时长一天

        //将Cookie发送给浏览器客户端，到浏览器肯定是响应，所以用response
        response.addCookie(cookie1);
        response.addCookie(cookie2);

    }
}

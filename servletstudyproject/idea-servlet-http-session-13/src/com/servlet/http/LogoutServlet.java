package com.servlet.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //实现的是退出登录删除session功能，获取session如果获取不到没有必要创建session对象
        HttpSession session = request.getSession(false);

        if (session != null) {

            //销毁session对象
            session.invalidate();

        }

    }
}

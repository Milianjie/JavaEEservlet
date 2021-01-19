package com.servlet.httpservletrequest;

import com.servlet.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //创建一个User对象
        User user = new User();
        user.setUserCode("16020521234");
        user.setUserName("nick");

        //将User对象存放进request范围对象中
        request.setAttribute("user",user);

        //取出request范围中的数据
        //Object obj = request.getAttribute("user");
        //response.getWriter().print(obj);

        //希望在另一个Servlet对象中取出本request范围中的数据
        //将另一个对象的请求容纳进本对象的请求中，就可以共有本对象的request范围了
        //跳转
        //执行完AServlet后，跳转到BServlet继续执行，这样两个类对象就放到同一个请求中了
        //使用转发技术，forward（转发）方法

        //1、获取获取转发器对象(以下转发器指向了BServlet，路径中是url-pattern标签中的内容)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/method/b" );
        //2、调用转发器的forward方法将request和response传过去就可完成转发，共有一个request和response对象
        dispatcher.forward(request,response);
        System.out.println("了了了了了了");

    }
}

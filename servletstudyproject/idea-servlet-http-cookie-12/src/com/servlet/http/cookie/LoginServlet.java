package com.servlet.http.cookie;

import com.servlet.http.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        //获取请求提交表单数据request
        String loginName = request.getParameter("loginName");
        String loginPwd = request.getParameter("loginPwd");
//      System.out.println(loginName+"\n"+loginPwd);

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        String realName = null;

        try {
            //注册驱动和获取连接
            conn = DBUtil.getConnection();

            //获取数据库操作对象
            String sql = "select loginName,loginPwd,realName from t_user where loginName=? and loginPwd=?";
            //需要注意的是先编译sql语句在传？的值，不然报空指针异常
            ps = conn.prepareStatement(sql);
            ps.setString(1,loginName);
            ps.setString(2,loginPwd);


            //执行sql语句
            rs = ps.executeQuery();

            //处理查询结果集
            if (rs.next()){
                loginSuccess = true;
                realName = rs.getString(3);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (loginSuccess){

            //判断是否选择了十天免登录
            if ("ok".equals(request.getParameter("TenDayAutoLoginFlag"))){
                //创建Cookie对象
                Cookie cookie1 = new Cookie("loginName",loginName);
                Cookie cookie2 = new Cookie("loginPwd",loginPwd);
                //绑定Cookie路径
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                //设定有效时长
                cookie1.setMaxAge(60*60*24*10);
                cookie2.setMaxAge(60*60*24*10);
                //发送Cookie
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }

            //设置输出到浏览器的字符类型和编码方式
            response.setContentType("text/html;charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.print("<html >");
            out.print("<head>");
            out.print("</head>");
            out.print("<body>");
            out.print("欢迎用户:"+realName+"<br>登录本操作系统");
            out.print("</body>");
            out.print("</html>");

        }else {
            response.sendRedirect(request.getContextPath()+"/html/login-error.html");
        }
    }
}

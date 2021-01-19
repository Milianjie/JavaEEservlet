### 首先要完成的是创建程序实现登录功能

### 创建的web项目为idea-servlet-http-cookie-12

### 登录功能实现的代码如下

web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    
    <welcome-file-list>
        <welcome-file>html/login.html</welcome-file>
    </welcome-file-list>
    
    <servlet>
        <servlet-name>login</servlet-name>
        <servlet-class>com.servlet.http.cookie.LoginServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>login</servlet-name>
        <url-pattern>/cookie/login</url-pattern>
    </servlet-mapping>
</web-app>
```

login.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
</head>
<body>
    <form action="/idea-servlet-cookie/cookie/login" method="post">
        用户名<input type="text" name="loginName"><br>
        密  码<input type="password" name="loginPwd"><br>
        <input type="submit" value="LOGIN">
    </form>
</body>
</html>
```

login-error.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>输入错误</title>
</head>
<body>
    <form action="/idea-servlet-cookie/html/login.html" method="get">
        用户名或密码错误，请重新<input type="submit" value="登录">
    </form>
</body>
</html>
```

LoginServlet.java

```java
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
            response.sendRedirect("/idea-servlet-cookie/html/login-error.html");
        }
    }
}

```

## 要实现十天免登录，首先在登录页面添加一个“十天免登录”选项

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
</head>
<body>
    <form action="/idea-servlet-cookie/cookie/login" method="post">
        用户名<input type="text" name="loginName"><br>
        密  码<input type="password" name="loginPwd"><br>
        <!--十天免登录选项-->
        <input type="checkbox" name="TenDayAutoLoginFlag" value="ok">十天内免登录<br>
        <input type="submit" value="LOGIN">
    </form>
</body>
</html>
```

## 其次需要修改欢迎页面，不能把登录页面作为欢迎页面，我们创建一个Servlet类CheckLoginStatusServlet.java，将这个判断类作为欢迎页面。这个类获取Cookie，通过获取Cookie中的value值是否为空，判断是否要转到登录页面，还是用Cookie保存的登录名和密码跳转到上次登陆后的页面。

### 修改的web.xml如下

```html
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    
    <welcome-file-list>
        <welcome-file>cookie/checkLogin</welcome-file>
    </welcome-file-list>
    
    <servlet>
        <servlet-name>login</servlet-name>
        <servlet-class>com.servlet.http.cookie.LoginServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>login</servlet-name>
        <url-pattern>/cookie/login</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>checkLogin</servlet-name>
        <servlet-class>com.servlet.http.cookie.CheckLoginStatusServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>checkLogin</servlet-name>
        <url-pattern>/cookie/checkLogin</url-pattern>
    </servlet-mapping>
</web-app>
```

## 判断Servlet类如下：CheckLoginStatusServlet.java

```java
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

public class CheckLoginStatusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取request中的Cookie
        Cookie[] cookies = request.getCookies();

        String loginName = null;
        String loginPwd = null;

        if (cookies != null) {

            for (Cookie cookie:
                 cookies) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                if ("loginName".equals(cookieName)){
                    loginName =  cookieValue;
                }else if ("loginPwd".equals(cookieName)){
                    loginPwd = cookieValue;
                }
            }

        }

        if (loginName != null && loginPwd != null) {


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

            }
        } else {
            response.sendRedirect(request.getContextPath()+"/html/login.html");
        }
        }


}

```

### 然后通过在两个Servle类中添加断点进行Debug，首先是一步一步的往下走，如果走不动了点击debug页面左端的绿色播放按钮，就可以调到另一个Servlet类中的断点，继续一步一步走了
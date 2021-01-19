### 1、web系统中资源的跳转有两种方式：

```sql
--转发：
	forward
代码实现：
	request.getRequestDispatcher("/b").forward(request,response);
--转发是在一个项目内部进行资源跳转，在浏览器中请求路径不变，执行结束只有一个请求
	
--重定向：
	redirect
代码实现：
	response.sendRedirect(request.getContextPath()+"/b");
    //或者说路径是某个webapp项目的资源路径，"部署后的项目名/url-pattern标签中的内容"
--重定向可以是在项目内部跳转，也可以是跨项目跳转，因为路径中可以加项目名。
--原理在
	一次请求中请求该Servle对象资源，请求路径：http://localhost:8080/jd/a ,执行到上面重定向的java语句后:response.sendRedirect("taobao/b"); ，将路径"taobao/b"响应给浏览器，浏览器又发送了一个路径为 http://localhost:8080/taobao/b 的新请求。--重定向是两次请求
	
--跳转的资源有很多，如Servlet、HTML页面、JSP等等。

--怎么选择这两种跳转方式?
*希望跨app跳转，使用重定向（用的多）
*若上一个资源中的request范围存储了数据，希望在下一个资源中从request取出数据用转发

--为什么重定向用的多
	因为重定向可以解决刷新问题（刷新时不用重复执行请求资源的Java程序）
	例如我们在请求中提交注册表单数据到服务器，希望将这些数据存储到数据库中。用if语句判断是否成功，如果存储成功执行跳转到成功的html页面的代码。
	使用转发跳转的话，浏览器跳转到成功页面，当我们刷新时就重新发送请求，由于请求不变，获取request范围中的数据和连接数据将数据存储等等的java程序代码又执行一遍，数据库中就会多出一行记录。
	使用重定向跳转，执行重定向跳转后请求路径改变，无论怎么刷新都还是获取哪个成功页面资源的路径。
```

### 2、创建web项目：idea-servlet-http-10 测试重定向的刷新问题的解决

要注意的是连接数据库的问题，看i用idea开发web程序连接数据库的笔记。其中有问题的解析。

### 代码如下：

### SaveServlet.java

```java
package com.servlet.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置页面数据类型和编码方式
        response.setContentType("text/html;charset=UTF-8");

        //
        request.setCharacterEncoding("UTF-8");

        //获取请求中提交的表单数据
        String usercode = request.getParameter("usercode");
        String username = request.getParameter("username");

        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy?useSSH=false","root","rong195302");

            conn.setAutoCommit(false);

            String sql = "insert t_user_3(usercode,username) vlaue(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,usercode);
            ps.setString(2,username);

            count = ps.executeUpdate();

            conn.commit();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        if (count==1){
            response.sendRedirect("/idea_servlet_http_10_war_exploded/html/result");
        }
    }
}

```

### web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <welcome-file-list>
        <welcome-file>welcome page</welcome-file>
        <welcome-file>html/save.html</welcome-file>
    </welcome-file-list>

    <servlet>
        <servlet-name>save</servlet-name>
        <servlet-class>com.servlet.http.SaveServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>save</servlet-name>
        <url-pattern>/save</url-pattern>
    </servlet-mapping>
</web-app>
```

### save.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>save page</title>
</head>
<body>
    <form action="/idea_servlet_http_10_war_exploded/save" method="post">
        usercode<input type="text" name="usercode"><br>
        username<input type="text" name="username"><br>
        <input type="submit" value="save">
    </form>
</body>
</html>
```



### result.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>successful page</title>
</head>
<body>
    插入数据成功！
</body>
</html>
```


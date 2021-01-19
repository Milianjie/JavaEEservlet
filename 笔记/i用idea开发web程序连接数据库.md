1、首先创建普通的java项目，Project Name命名为idea-servletweb-02，保存在F:\Git_Repositories\J2EE-servelet\servletstudyproject下

2、点击工程名字，右键选择添加框架支持Add Framework Support，选择JavaEE8，选择WebApplication，应用Apply，OK。

3、在新生成的web中选择WEB-INF，打开web.xml文件，编写请求与资源的绑定，此次绑定一个类，命名为servlet jdbc query，完整的类名为com.servletstudy.jdbc.ListServletEmp，请求虚拟路径/query，保存

4、根据web.xml文件中的信息，建包com.servletstudy.jdbc，建ListServletEmp类，实现Servlet规范。

5、配置Tomcat服务器。Run->Edit Configurations->左边点击+号->选择Tomcat->Local->在Application server选项点击Configure->Tomcat Home中选择下载的Tomcat，选择到lib目录的上一级，点击ok。然后点击部署选项Deployment，选择要部署到服务器的项目，没有选择就右边+号点击第一个。注意选择的项目是这样的（项目名:war exploded)，而不是单单一个项目名，下面Application context中修改部署到服务器中的程序名为/JDBCQuery

![](F:\Git_Repositories\J2EE-servelet\截图\idea创建web项目\28.png)

6、添加Servlet规范jar包。File->Project Structure->Libraries->点击+号->java->从Tomcat的lib中选择servlet-api.jar->Apply->ok。重复一遍添加MySQL驱动。

7、编写一个数据库连接工具类JDBCUtil，方便连接数据库。

8、把查询结果按照下面的HTML输出到浏览器

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>员工信息</title>
</head>
<body>
    <h3 align="center">员工列表</h3>
    <hr width="60%">
    <table border="1" align="center" width="50%">
        <tr align="center">
            <th>序号</th>
            <th>员工编号</th>
            <th>员工姓名</th>
            <th>员工薪资</th>
        </tr>
        <tr align="center">
            <td>1</td>
            <td>111</td>
            <td>ccc</td>
            <td>13232</td>
        </tr>
    </table>
</body>
</html>
```

![](F:\Git_Repositories\J2EE-servelet\截图\idea创建web项目\29.png)

9、所以实现类这样写，将从标签<html>到</html>的所有内容复制到类中，批量编辑，按下 alt+caps lock ，用鼠标左键拖动，前面添加out.print("

```java
package com.servletstudy.jdbc;

import com.servletstudy.utils.JDBCUtil;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListServletEmp implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = servletResponse.getWriter();

            out.print("<html>");
            out.print( "   <title>员工信息</title>");
            out.print( "<body>");
            out.print( "<h3 align=\"center\">员工列表</h3>");
            out.print( "<hr width=\"60%\">");
            out.print( "<table border=\"1\" align=\"center\" width=\"50%\">");
            out.print( "   <tr align=\"center\">");
            out.print( "       <th>序号</th>");
            out.print( "       <th>员工编号</th>");
            out.print( "       <th>员工姓名</th>");
            out.print( "       <th>员工薪资</th>");
            out.print( "   </tr>");

        try {

            conn = JDBCUtil.getConnection();

            stmt = conn.createStatement();

            String sql = "select empno,ename,sal from emp";
            rs = stmt.executeQuery(sql);

            int i = 1;
            while (rs.next()){

                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                Double sal = rs.getDouble("sal");
                out.print( "   <tr align=\"center\">");
                out.print( "       <td>"+(i++)+"</td>");
                out.print( "       <td>"+empno+"</td>");
                out.print( "       <td>"+ename+"</td>");
                out.print( "       <td>"+sal+"</td>");
                out.print( "   </tr>");

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(conn,stmt,rs);
        }

        out.print( "</table>");
        out.print( "</body>");
        out.print( "</html>");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
```

写好代码，点击Tomcat部署运行。出现如下异常

![](F:\Git_Repositories\J2EE-servelet\截图\idea创建web项目\30.png)

我们该项目是导入了MySQL驱动jar包的，而且在web目录下新建了一个lib类库目录，同时也将MySQL驱动粘贴进去了，但是还是报了类没找到异常，百度了一下，有一种方法是将MySQL驱动jar报放进Tomcat的lib目录里，没试过。另一种是到Project Structure->Artifacts->选中要部署的项目->选中WEB-INF->鼠标右键->Add Copy Of->Libraries->选择要添加的数据库驱动->Apply应用

![](F:\Git_Repositories\J2EE-servelet\截图\idea创建web项目\31.png)

![](F:\Git_Repositories\J2EE-servelet\截图\idea创建web项目\32.png)

最后我把项目中web目录中lib里的数据库驱动删了也报异常，所以需要两者都存在驱动才行。而且是在lib中先添加MySQL驱动后才能打开Project Structure中在WEB-INF中添加数据库驱动，lib中的删除了得把Project Structure中在WEB-INF中的数据库驱动移除，先在lib加，然后WEB-INF。

![](F:\Git_Repositories\J2EE-servelet\截图\idea创建web项目\33.png)
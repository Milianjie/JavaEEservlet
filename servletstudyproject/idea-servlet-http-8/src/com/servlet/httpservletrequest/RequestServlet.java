package com.servlet.httpservletrequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

public class RequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取表单提交的用户信息，这些数据被封装到request对象中
        //用request对象里的方法就可以获取这些信息了
        /*
            表单提交数据的格式如下，用POST请求方式，数据格式如下：
            username=admin&password=123&sex=m&hobby=sport&hobby=music&grade=college&introduce=ok
            而这些信息封装到request对象中实际是存到一个map集合中的，且集合中的value是一个数组类型
            如下：
            Map<String,String[]>
            key             value
            -----------------------------
            username        {"admin"}
            password        {"123"}
            sex             {"m"}
            hobby           {"sport","music"}
            grade           {"college"}
            introduce       {"ok"}
         */

        //测试方法：String getParameter(String name)
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String hobby = request.getParameter("hobby");
        String grade = request.getParameter("grade");
        String introduce = request.getParameter("introduce");
        System.out.println(username);
        System.out.println(password);
        System.out.println(sex);
        System.out.println(hobby);
        System.out.println(grade);
        System.out.println(introduce);
        System.out.println("==================================================");

        //测试方法：String[] getParameterValues(String name)
        //这里只有爱好hobby可以选择多个，所以下面只获取表单数据中爱好的数据
        String[] interest = request.getParameterValues("hobby");
        //遍历集合interest
        for (String value:
             interest) {
            System.out.println(value);
        }
        System.out.println("==================================================");

        //测试方法：Map getParameterMap()
        Map<String,String[]> data = request.getParameterMap();
        //获取data集合中的key并且存放进一个Set集合中
        Set keys1 = data.keySet();
        //遍历keys集合获取data中的value
        for (Object name:
             keys1) {
            System.out.println(data.get(name));
            String[] values = data.get(name);
            System.out.print(name+" = ");
            for (String value:
                 values) {
                System.out.print(value+" ");
            }
            System.out.println();
        }
        System.out.println("==================================================");
        //从输出结果可以看做出，data集合中的value是每个类型数据的数组对象
        //数组的toString方法输出如：[Ljava.lang.String;@7c910271，即[L+完整的类名;@+内存地址经过hash计算转换成16进制

        //测试方法：Enumeration getParameterNames()
        Enumeration names = request.getParameterNames();
        //遍历names集合获取其中Map集合中的value
        while (names.hasMoreElements()){
            String keys2 = (String)names.nextElement();
            String[] values = request.getParameterValues(keys2);
            System.out.print(keys2+" = ");
            for (String value:
                values ) {
                System.out.print(value+" ");
            }
            System.out.println();
        }

    }
}

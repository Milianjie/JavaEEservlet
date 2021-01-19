```sql
--webapp中常见的状态码：
	-404
	Not Found：资源未找到【请求路径写错了，可能是浏览器上面，也可能是web.xml文件中绑定的写错了，是双向的】
	-500
	Server Inner Error：服务器内部错误【一般都是Java程序出现异常】
	404和500都是HTTP协议状态码，这些状态号都是W3C制定的，所有浏览器和服务器都必须遵守
	
	正常响应的HTTP协议状态码：200
	在一些错误发生后我们想进行一些统一的处理，可以在web.xml文件中做一下配置：
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <welcome-file-list>
        <!--这样的文件路径必须是在web目录下，前面不需要加/-->
    	<welcome-file>welcome.html</welcome-file>
        <!--若不在web目录下应这样写,html目录在web目录下-->
        <welcome-file>html/welcome.html</welcome-file>
    </welcome-file-list>
    
    <servlet>
        <servlet-name>servletcontext1</servlet-name>
        <servlet-class>com.servlet.AServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>servletcontext1</servlet-name>
        <url-pattern>/a</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>servletcontext2</servlet-name>
        <servlet-class>com.servlet.BServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>servletcontext2</servlet-name>
        <url-pattern>/b</url-pattern>
    </servlet-mapping>
    
    <!--下面路径不需要加部署的项目名，但要以/开头-->
    <error-page>
    	<error-code>404</error-code>
        <location>/error/error.html</location>
    </error-page>
     <error-page>
    	<error-code>500</error-code>
        <location>/error/error.html</location>
    </error-page>
</web-app>
```


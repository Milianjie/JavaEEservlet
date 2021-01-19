### 在idea中创建好web项目之后，编写好相应代码以及配置，启动服务器后idea会自动启动我们设置的调试浏览器并转到http://localhost:8080/部署后的项目名称/，在idea中将项目创建为web项目时，会生成一个web目录，该目录中有WEB-INF目录，而且还生成了一个index.jsp文件在web目录下。

##### 注意：我们知道这个web目录我们可以从项目的硬盘文件夹中将其复制到Tomcat的webapps目录下，就算是把这个项目部署好了，部署后的项目名称就叫web，当然可以改成其他名字。这针对的是不用IDE部署webapp的情况下部署项目，idea中该web目录只是一个标识，标识该项目要部署的所有东西都在web目录中，部署完之后服务器上该项目的名字可以在配置服务器时设置为真正的项目名。Myeclipes中是WebRoot目录，两者是一样的，只不过该IDE创建web项目不自动生成index.jsp文件。

### 在idea中点击Tomcat部署运行，就像上面说的，浏览器打开自动跳转到请求路径为    http://localhost:8080/部署后的项目名称/    的页面，当我们没删除index.jsp时，上面的请求路径就会输出该资源文件。前面的测试中，又建了一个index.html文件，在其中编写了一个超链接，连接Servlet对象的执行结果资源，部署运行后浏览器自动输出的是index.html中的内容。

### 出现上面这种情况的原因是有欢迎页面的设置，如果我们想项目部署后输入的请求路径只到项目名称后不加任何资源的名称代号路径能转到一个欢迎页面，我们可以在web.xml文件中添加欢迎页面的标签，设置如下：

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
</web-app>
```

### 上面的这种转到欢迎页面的标签的优先级是从上往下下降的，还有就是为什么没有将web目录下的index.html或者index.jsp的路径添加到欢迎标签中却能将这些页面自动设置为欢迎页面，原因是Tomcat的conf目录中的web.xml中将index这个名字的html文件，jsp文件等设置为欢迎页面，且html文件在jsp文件上面，优先级高，同时存在index.html被设置为欢迎页面。

### 所以我们可以知道服务器其中的web.xml是一个全局配置文件，而服务器上面部署的项目的web.xml是局部配置文件，而且我们还可以将一个Servlet对象的执行结果作为欢迎页面，就是说作为欢迎页面的种类是很多的，欢迎页面文件资源是在web目录下或者是WEB-INF下，所以路径不需要加部署后的项目名称。
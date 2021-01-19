##### 第一步，创建一个名为FirstServletWebApp的项目文件夹，该文件夹里有多个文件夹，有存放css资源的css文件夹，有存放html资源文件的html文件夹，也有存放JavaScript文件的js文件夹等等，但为了遵循Servlet规范，必须创建一个名为WEB-INF的文件夹，不能改变大小写，不然服务器不能辨识。

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE的ServletWebApp\1.png)

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE的ServletWebApp\2.png)

WEB-INF文件夹中要建一个classes文件夹，存放该项目的.class字节码文件；建一个lib文件夹，libraries，存放的是项目所需的jar包，就像Tomcat中的lib一样，Tomcat中的是全局的jar包，本项目的是局部的jar包，虽然我们将数据库的驱动jar包放进了系统的calsspath变量中，但当我们这个项目需要连接数据库，服务器是不会到系统中找数据库连接的jar包的，而是到你这个项目的lib中寻找；建一个web.xml文件，存放的是请求路径和资源的关联信息。

##### 第二步，建一个HelloWorld.java文件，该类实现Servlet接口，得实现该接口的所有方法，具体有什么方法，查阅javaEE版本的帮助文档。一共有五个方法

```java

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class HelloWorld implements Servlet{

    public void init(ServletConfig config) throws ServletException{

    }

    public void service(ServletRequest request,ServletResponse response) throws IOException,ServletException{

        //将信息打印到控制台，而不是输出到浏览器
        System.out.println("Hello World!");

    }

    public void destroy(){

    }

    //本次的项目程序不多，这里的方法用到的不多，直接返回null
    public String getServletInfo(){
        return  null;
    }

    //本次的项目程序不多，这里的方法用到的不多，直接返回null
    public ServletConfig getServletConfig(){
        return null;
    }

}
```

写好保存。现在想要编译，在dos命令窗口javac HelloWorld.java是编译不成功的，因为找不到代码中导入的javax.servlet包下的接口和类，之前我们在Tomcat的lib类库文件夹中发现有jsp和servlet规范的jar包，这是SUN公司写的，Tomcat服务器想要调用我们写的实现Servlet规范的Java Web程序，就得需要面向这两个规范调用，所需要把这两个SUN公司写的规范jar包放到Tomcat的类库lib中。这里我们也需要servlet jar包，直接将Tomcat中lib中的servlet-api.jar中的路径添加到环境变量classpath中。这样只是为了我们编译用的，运行是服务器中找它自己lib类库中的规范包

在编译过程中，由于java文件写完后是已UTF-8的字符编码方式保存，dos命令窗口是GBK简体中文，编译报错，所以将.java文件用记事本打开，然后另存为时选择简体中文字符编码方式ANSI保存，就可以正常编译了

##### 第三步，编译完成后将生成的HelloWorld.class文件放进FirstServletWebApp/WEB-INF/classes中，

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE的ServletWebApp\3.png)

##### 第四步，编写web.xml配置文件。怎么样编写，可以参考Tomcat中conf文件夹中的web.xml文件，将其打开，将头部复制过来，注释不用复制。要注意标签的头和尾<web-app ...后面这里得与前面对应

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
  version="4.0">
    
    <!--这是一个合法的web.xml文件-->
    <!--一个webapp只有一个web.xml文件-->
    <!--web.xml文件主要配置请求路径和实现Servlet的类名之间的绑定关系-->
    <!--web.xml文件在Tomcat服务器启动阶段被解析-->
    <!--web.xml文件解析失败，就会导致服务器启动失败-->
    <!--web.xml文件的标签不能随便编写，Tomcat服务器早就知道该文件中需要编写的标签，该标签也是SUN制定的规范-->
    <!--直接记住下面的个标签写法和含义-->
    <servlet>
        <!--下面这个servlet-name标签位置的名字随便起,但要与下面同一个标签名一致-->
    	<servlet-name>thisIsServletName</servlet-name>
        <!--下面这个servlet-class标签位置写的是项目中的类名-->
        <servlet-class>HelloWorldServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <!--下面这个servlet-name标签位置的名字随便起,但要与上面同一个标签名一致-->
        <servlet-name>thisIsServletName</servlet-name>
        <!--下面这个url-pattern标签位置写的是请求路径，随意编写，但要以/开头，不用添加项目的名称-->
        <!--该请求路径是虚拟的路径，只是代表一个资源的名称，就是上面类的代号-->
        <url-pattern>/zzz/dada/sss/aaa</url-pattern>
        <!--url-pattern标签还可以编写多个，但都得以/开头且不需要添加项目名-->
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
</web-app>
```

##### 第五步，写完后保存，开始部署项目，然后测试，输入网址http://localhost:8080/FirstServletWebApp/zzz/dada/sss/aaa

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE的ServletWebApp\4.png)

我们可以看到每一次刷新该网址Tomcat服务器的黑窗口都输出"Hello World!"

因为我们在service方法中写的是输出到控制台，所以浏览器没有输出，下个项目输出到浏览器。 

##### 第六步，我们在该项目中的html目录中新建一个welcome.html文件，在里面编写超链接代码，里面跳转的连接请求路径不像上面的url-pattern标签不需要写项目名，这个超链接是需要加项目名的，虽然这个html是在同一个项目中，但是其他项目中的html文件打开后点击里面的连接如何跳转到其他项目的资源呢，这就导致超链接里面的请求路径要加上项目的名字

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE的ServletWebApp\5.png)

```html
<html>
    <head>
        welcome page
    </head>
    <body>
        <a href="/FirstServletWebApp/hello">HelloWorldServlet</a>
    </body>
</html>
```

保存重新部署，重启服务器，输入请问求路径，结果如下

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE的ServletWebApp\6.png)

点击HelloWorldServlet，跳转到另一个资源，Tomcat黑窗口输出hello world！

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE的ServletWebApp\7.png)


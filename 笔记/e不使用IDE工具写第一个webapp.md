##### 第一步：在某个文件夹中新建一个名为FirstApp的文件夹，该文件就是一个项目，名字叫FirstApp

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE第一个Web程序\1.png)

##### 第二步，打开FirstApp，在里面新建一个名为login的html文件login.html，并在里面编写简单的静态页面代码

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE第一个Web程序\2.png)

```html
<html>
    <head>
        <title>login page</title>
    </head>
    <body>
        <form action="">
                username<input type="text" name="username"/><br>
                password<input type="password" name="password"/><br>
                <input type="submit" value="login">
        </form>
    </body>
</html>
```

##### 第三步，开发完程序后部署到服务器上，这里是Tomcat服务器，直接将FirstApp文件复制粘贴到Tomcat所在文件夹的webapps文件夹中，这就算部署完成了。

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE第一个Web程序\4.png)

##### 第四步、启动Tomcat服务器，在浏览器输入http://localhost:8080/FirstApp/login.html，回车

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE第一个Web程序\5.png)

##### 第五步，我们在FirstApp文件中新建一个html的文件夹，打开后在里面新建一个welcom.html文件

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE第一个Web程序\6.png)

```html
<html>
    <head>
            <title>welcome page</title>
    </head>
    <body>
        <h1 align="center"><font color="red">welcome page</font> </h1>
    </body>
</html>
```

##### 第六步，需要重新部署该FirstApp项目，先关闭Tomcat服务器，因为Tomcat中关闭的命令shutdown和Windows关闭系统命令冲突，我们将Tomcat的bin文件目录中的shutdown.bat改为stop.bat。此时在dos命令窗口键入stop，回车，关闭服务器。然后删除原先部署的FirstApp，之后的步骤与上一致。启动服务器，浏览器，输入http://localhost:8080/FirstApp/html/welcome.html,可以看到返回一个欢迎页

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE第一个Web程序\7.png)

##### 第七步，我们想在获取的welcome.html页面中添加一个点击按钮名为login page，点击它跳转到login.html资源页面。可以在welcome.html的代码中添加超链接的方式，如下

```html
<html>
    <head>
            <title>welcome page</title>
    </head>
    <body>
        <h1 align="center"><font color="red">welcome page</font> </h1>
        <hr>

        <!--超链接的方式-->
        <a href = "http://localhost:8080/FirstApp/login.html">login page</a>

         <!--为了更好的通用，URL路径中的IP地址、端口号可以省略，但前面的/不能省-->
        <!--以下网络资源路径还是一个绝对路径，目前必须以/开始-->
        <a href = "/FirstApp/login.html">login page</a>
    </body>
</html>
```

##### 第八步，关闭服务器，覆盖webapps中的FirstApp，启动服务器，浏览器输入请求路径，如下：

![](F:\Git_Repositories\J2EE-servelet\截图\无IDE第一个Web程序\8.png)


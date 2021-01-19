![](F:\Git_Repositories\J2EE-servelet\截图\安装Tomcat\73.png)

bin目录：放置的是Tomcat服务器相关的一些命令，如启动与关闭服务器的命令等等

conf目录：configure缩写，配置。里面有大量的配置文件，有.properties结尾的属性配置文件，有.xml结尾是配置文件，后者更复杂（比较html和xml，它们之间有点相似）。例如里面的server.xml文件打开可以看到里面有端口配置信息，是8080，改变它，以后该Tomcat服务器的端口号就变了。

lib目录：libraries，类库。里面存放的各种jar包，是Tomcat服务器运行时的各个组件，解压jar里面是各个模块的类class字节码文件。里面有个servlet-api.jar和jsp.jar，因为Tomcat要依赖于这两个规范，所以把这两个规范jar包放进去，这就是javaEEServlet规范的字节码文件位置。我们编写服务器端java程序也要遵循这两个规范，也需要这两个jar包，JDK中可能自带有，也可能没有，需要我们下载导包。

logs目录：这是Tomcat保存日志的文件夹，例如保存了Tomcat服务器启动的日志等等

temp目录：这Tomcat服务器的临时文件夹。不需要关心

webapps目录：存放的是部署在Tomcat服务器上的项目，Java程序，可以把淘宝放进去，可以放京东进去，可以部署多个


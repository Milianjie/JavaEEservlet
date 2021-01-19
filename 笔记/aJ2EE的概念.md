### 1、理解J2EE

```
刚开始所学的是JavaSE，我们需要下载Oracle公司的java语言开发工具包，即JDK。JDK里面包含了编译时环境(开发人员需要)和运行时环境（jre）等等。
我们可以这样理解：
JavaSE是java一套最基础的库，里面有我们通常开发所需要的各种类和接口，源码保存在sr.zip压缩包下
字节码文件保存在lib/rt.jar包下，我们可以下载这套库不同版本的API帮助文档，从帮助文档学习使用库中各类以及各类中的方法
JavaEE也是一套库，是基于JavaSE库的基础上具有更多类的有版本的类库，企业级开发。其使用的JDK与标准版一样，但开发时需要导入企业级开发的jar包，源码和字节码文件保存位置与上面一样。但库的维护版本不同，下载的帮助文档也不同。
```

### 2、什么是API?

```sql
--API:
Application Programming Interface
应用程序编程接口（这里的接口，不是库中的那种interface）
--API报括：
	源码，字节码，帮助文档（使用时注意版本号要一致）
```

### 3、JavaSE

```sql
--Java的标准版本
SUN公司为java程序员提供的一套基础类库
--这套基础类库包括：基础语法、面向对象、异常、IO、集合、反射、线程...
JavaSE的版本目前由Oracle维护，最高版本到javaSE14
```

### 4、JavaEE

```sql
--Java企业版
SUN公司为java程序员提供的另一套庞大的类库，帮助程序员完成企业级项目的开发
--javaEE的版本号
所学课程视频中用的是JavaEE5，自己用啥不知道
--JavaEE规范
JavaEE规范是一个比较大的规范，该规范又包括13个子规范（每一个子规范下还有自己的子规范）
JDBC也属于JavaEE规范，只不过这套库太常用了，所以把他收录到了SE里
JavaEE5规范下的子规范：
	* Servlet2.5
	* JDBC
	...
JavaEE6下的子规范
	* Servlet3.0
	* JDBC
	...
正如JDBC在java.sql包中的各个接口，是一套规范，各数据库厂家遵循这套规范实现这些接口才能让用java语言连接自家的数据库，我们连接不同数据库要到不同数据库的驱动jar包，JavaEE规范也是如此。
Servlet也是一套接口。
--Tomcat服务器
其中Tomcat6实现了Servlet2.5规范，Tomcat7实现类Servlet3.0规范，教学中使用Tomcat7服务器，自己使用啥不知道	

```


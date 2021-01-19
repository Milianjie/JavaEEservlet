### 第一步：

从Apache官网下载符合自己系统的相应Tomcat的压缩包，需要了解Tomcat版本所实现的规范的版本以及适用的java版本

本电脑是Windows64位系统，安装的是jdk8，所以下载Tomcat9

![](F:\Git_Repositories\J2EE-servelet\截图\安装Tomcat\1.png)

### 第二步：

解压（无需安装），进到bin目录下

![](F:\Git_Repositories\J2EE-servelet\截图\安装Tomcat\2.png)

可以看到该目录下有三个文件：startup.bat和tomcat9.exe，shutdown.bat

这前两个是启动Tomcat服务器的，但怎么点击都没法启动。后一个是关闭服务器的，而且里面中.sh结尾的是在Linux系统中运行的。

原因是Tomcat服务器启动需要Java的运行时环境，虽然我们安装了jdk8，我们并没有配置一个Tomcat需要的Java环境变量。

先用dos命令窗口运行报什么错误

![](F:\Git_Repositories\J2EE-servelet\截图\安装Tomcat\3.png)

Neither the JAVA_HOME nor the JRE_HOME environment variable is defined
At least one of these environment variable is needed to run this program

意思是环境变量中并没有定义变量名为JAVA_HOME（java的家，就是说变量名的值只需要路径到bin文件外就是java的家）或者是 JRE_HOME的环境变量，想要运行该程序至少有这两个环境变量之一

那我们就去配置环境变量，如下:

![](F:\Git_Repositories\J2EE-servelet\截图\安装Tomcat\4.png)

有一点要注意的是变量值不用到java8里的bin文件夹下

再次启动，启动成功如下

![](F:\Git_Repositories\J2EE-servelet\截图\安装Tomcat\5.png)

测试是否启动成功，可以在浏览器输入localhost:8080或者127.0.0.1:8080，可以返回如下页面

![](F:\Git_Repositories\J2EE-servelet\截图\安装Tomcat\6.png)

### 说明：

1）localhost：本地主机域名。计算机网络中，localhost意为“本地主机”，指“这台计算机”，是给回路网络接口（loopback）的一个标准主机名，相对应的[IP地址](https://www.baidu.com/s?wd=IP地址&tn=SE_PcZhidaonwhc_ngpagmjz&rsv_dl=gh_pc_zhidao)为127.0.0.1（IPv4）和[::1]（IPv6）。
2）127.0.0.1：是回送地址，指本地机。 127.0.0.1是用来检测网络中自己的IP。对任何一台电脑来说，不管是否连接到Internet，127.0.0.1对于自己来说都是自己
3）192.168.1.1：本机ip地址，但不固定，也可以手动设置成其他ip地址

前两个基本相等，一个是域名，一个是ip，且一定指本机，最后一个不一定是本地机

本机IP的方式，可以让同局域网内其他机器访问。

##### 此时，我这台电脑（硬件服务器）安装了Tomcat（软件服务器），并且启动了Tomcat（执行Tomcat中的main方法），组合起来就相当于一个Web服务器启动了，我们就可以通过在浏览器输入请求路径请求该服务器，让该服务器响应，只不过Tomcat中还缺少Java程序的部署。

##### 此时要关闭服务器不能直接点小猫窗口右上的叉，需要运行shutdown.bat文件，直接点也行，dos窗口也行

### 有时候我们想不进到Tomcat所在文件夹去启动它，想要直接在命令窗口直接startup，就得将它的bin文件夹配置到环境变量名为path中，当我们配好后打开dos命令窗口直接敲startup命令时提示变量名为CATALINA_HOME的环境变量没有添加，所以添加到path还不够，需要新建变量名为CATALINA_HOME，变量值中的路径因为是家变量，所以是Tomcat里的bin文件夹的上级文件夹（包含）路径


```java
/**
	乱码问题，就是传递的数据的编码与数据所呈现到某个地方的后怎么解码后的结果问题。要解决该问题，要么改变接收数据那头的解码方式，要么就是改变发送数据那头的编码方式。当然，比如浏览器，默认的字符编码方式是UTF-8，无论是发送数据还是接收数据，其编码解码方式都是UTF-8.
*/
1、乱码出现的位置
    *数据传递过程中的乱码
    *数据展示过程中的乱码
    *数据存储过程中的乱码
    
    //存储过程中出现乱码
    即在数据最终保存在数据库表中的时候，数据出现乱码，有以下两种情况：
    第一种：
    	保存之前就是乱码数据，保存之后还是乱码
    第二种：
    	保存之前不是乱码但是该数据是以GBK编码的，由于数据库本身是不支持简体中文GBK编码的数据，就是说数据无法
    GBK解码，保存后就出现乱码
    
    //数据展示过程中的乱码
    最终显示到网页的数据出现乱码
    解决方式：
    	首先你要知道浏览器呈现的内容是以什么编码和解码方式进行的，大多数情况浏览器是使用的字符编码方式是
    UTF-8，所以在Servlet对象中将数据输出到浏览器显示时要将传递的数据的字符编码方式设置为UTF-8。使用下面方法
    //设置响应内容以及字符编码方式（这是执行java程序后，Java程序负责向浏览器响应的内容出现中文乱码）：
    response.setContentType("text/html;charset=UTF-8")
    //当没有经过Java程序，而是将html页面的内容响应到浏览器用以下方法设置页面内容的编码方式：
    <meta charset="UTF-8">
    或
    <meta content="text/html;charset=UTF-8">
    
    //数据传递过程中的乱码
    当我们发送请求时，有时需要将表单的数据以GET或者POST方式发送给服务器，服务器接收的数据是乱码。
    例如：我们在浏览器一个页面上的name为dname中输入大家好，点击提交，浏览器将“dname=市场部”这条数据传给服务器，但是我们用HttpWatch监测请求后从请求协议中看到浏览器是将“dname=%E5%B8%82%E5%9C%BA%E9%83%A8”传给服务器的
    %E5%B8%82%E5%9C%BA%E9%83%A8是“市场部”的ISO-8859-1的编码方式
    这是一种国际标准码，不支持中文编码，兼容ASCII码，又称latin1编码。
    无论哪个国家，其浏览器将数据发送给服务器都用ISO-8859-1的编码方式发送。
    虽然传送中文字符串数据看起来像是乱码，但是服务器接收后用ISO-8859-1解码后，再用UTF-8编码还是能得到原来的中文字符串的。
    当我们发送数据给web服务器后，服务器不知道这些数据都是什么类型的（中文、日文...)
    所以服务器接收到的数据是乱码
    //解决数据传递过程中的乱码：
    我们系统是中文的，我们需要将中文乱码解决
    
    第一种：万能式，可以解决POST请求和GET请求发送数据的乱码问题
   		因为我们程序员是知道发送到服务器中的数据有中文的，就将服务器中接收的数据采用ISO-8859-1解码，回归原始
    状态（最原始的二进制）。然后用一种支持简体中文的编码方式重新组装
    //在Java程序中获取dname
    String dname = request.getParameter("dname");
	//解码
	byte[] bytes = dname.getByte("ISO-8859-1");
	//再用支持中文的编码方式编码组装,要与浏览器中的字符编码方式一致
	dname = new String(bytes,"UTF-8");
	System.out.println(dname);

	第二种：在Java程序中调用request对象的setCharacterEcoding()方法，该方法只解决POST
     //意思是告诉Tomcat服务器，请求体中是数据用UTF-8编码。需要注意的是要先设置，在获取数据
     request.setCharacterEcoding("UTF-8");
	String dname = request.getParameter("dname");
	System.out.println(dname);

	第三种：转门解决GET请求的乱码问题，即只对请求行编码
        修改Tomcat文件中的config目录下的server.xml文件
        CATALINA_HOME/config/server.xml
        F:\Git_Repositories\J2EE-servelet\tools\tomcat9\apache-tomcat-9.0.41\conf\server.xml
        F:\Git_Repositories\J2EE-servelet\tools\tomcat9\apache-tomcat-9.0.41=CATALINA_HOME
        找到Connector标签，如下：
            <Connector port="8080" protocol="HTTP/1.1"
            connectionTimeout="20000"
            redirectPort="8443" />
        //添加 URIEncoding="UTF-8"
             <Connector port="8080" protocol="HTTP/1.1"
            connectionTimeout="20000"
            redirectPort="8443"
            URIEncoding="UTF-8"/>
       该标签中我们还可修改服务器端口号port为80
       还可以修改哪些属性，可以到帮助文档中看，如下：
          帮助文档位置： CATALINA_HOME/config/http.html
          port 端口
          maxThreads 最多支持的线程数量
          URIEncoding 设置请求行上的编码方式，解决GET请求乱码。
```


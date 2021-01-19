import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;//标准字符输出流

public class PrintToClient implements Servlet{

    public void init(ServletConfig config) throws ServletException{

    }

    //五个方法中重点是该方法
    public void service(ServletRequest request,ServletResponse response) throws IOException,ServletException{
        
        //有时候响应到浏览器的内容中文会出现乱码，那么用响应对象设置编码方式
        /*
        	ServletResponse接口有这样一个方法：
        	void setContentType(String type)
          Sets the content type of the response being sent to the client, if the response has not been committed yet.
          该方法设置内容类型和字符编码方式，不能写错，且要在获取标准流之前设置
        */
        response.setContentType("text/html;charset=UTF-8");
        
        //想将信息输出到浏览器,就需要使用标准输出流将程序的输出位置更改
        /*
        	ServletResponse接口有这样一个方法：
        PrintWriter	getWriter()
          Returns a PrintWriter object that can send character text to the client.
          该方法返回一个将信息输出到客户端的PrintWriter类的标准字符输出流对象
        */
        PrintWriter out = response.getWriter();
        //将HTML字符串输出到浏览器上，Browser解释执行HTML语言
        //这里响应HTML代码到浏览器
        //下面不需要用println()方法，没有必要，用的话只是将HTML字符串换行输出到浏览器，浏览器看源码
        //的时候HTML源代码是换行的，这样还会占内存，影响效率。
        //但是你想要将里面夹带的文字什么的信息换行，有其他方法
         out.print("<html>");
         out.print("<head>");
         out.print("<title>welcome servlet</title>");
        //具体信息，h1标记
         out.print("<h1 align=\"center\">welcome study servlet</h1>");//该标题下一信息是自动换行的
        //该输出信息与标题不在同一行
         out.print("hello");
         out.print("world");
        //换行添加的标签<br>
		 out.print("<br>");
         out.print("大家好！");
         out.print("</head>");
         out.print("</html>");

    }

    public void destroy(){

    }

    //本次的项目程序不复杂，这里的方法用到的不多，直接返回null
    public String getServletInfo(){
        return  null;
    }

    //本次的项目程序不复杂，这里的方法用到的不多，直接返回null
    public ServletConfig getServletConfig(){
        return null;
    }

}
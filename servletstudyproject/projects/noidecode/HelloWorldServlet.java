
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class HelloWorldServlet implements Servlet{

    public void init(ServletConfig config) throws ServletException{

    }

    public void service(ServletRequest request,ServletResponse response) throws IOException,ServletException{

        //将信息打印到控制台，而不是输出到浏览器
        System.out.println("Hello World!");

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
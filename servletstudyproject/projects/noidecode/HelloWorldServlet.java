
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

        //����Ϣ��ӡ������̨������������������
        System.out.println("Hello World!");

    }

    public void destroy(){

    }

    //���ε���Ŀ���򲻸��ӣ�����ķ����õ��Ĳ��ֱ࣬�ӷ���null
    public String getServletInfo(){
        return  null;
    }

    //���ε���Ŀ���򲻸��ӣ�����ķ����õ��Ĳ��ֱ࣬�ӷ���null
    public ServletConfig getServletConfig(){
        return null;
    }

}
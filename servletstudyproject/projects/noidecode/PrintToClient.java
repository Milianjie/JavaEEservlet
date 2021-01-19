import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;//��׼�ַ������

public class PrintToClient implements Servlet{

    public void init(ServletConfig config) throws ServletException{

    }

    //����������ص��Ǹ÷���
    public void service(ServletRequest request,ServletResponse response) throws IOException,ServletException{
        
        //��ʱ����Ӧ����������������Ļ�������룬��ô����Ӧ�������ñ��뷽ʽ
        /*
        	ServletResponse�ӿ�������һ��������
        	void setContentType(String type)
          Sets the content type of the response being sent to the client, if the response has not been committed yet.
          �÷��������������ͺ��ַ����뷽ʽ������д����Ҫ�ڻ�ȡ��׼��֮ǰ����
        */
        response.setContentType("text/html;charset=UTF-8");
        
        //�뽫��Ϣ����������,����Ҫʹ�ñ�׼���������������λ�ø���
        /*
        	ServletResponse�ӿ�������һ��������
        PrintWriter	getWriter()
          Returns a PrintWriter object that can send character text to the client.
          �÷�������һ������Ϣ������ͻ��˵�PrintWriter��ı�׼�ַ����������
        */
        PrintWriter out = response.getWriter();
        //��HTML�ַ��������������ϣ�Browser����ִ��HTML����
        //������ӦHTML���뵽�����
        //���治��Ҫ��println()������û�б�Ҫ���õĻ�ֻ�ǽ�HTML�ַ��������������������������Դ��
        //��ʱ��HTMLԴ�����ǻ��еģ���������ռ�ڴ棬Ӱ��Ч�ʡ�
        //��������Ҫ������д�������ʲô����Ϣ���У�����������
         out.print("<html>");
         out.print("<head>");
         out.print("<title>welcome servlet</title>");
        //������Ϣ��h1���
         out.print("<h1 align=\"center\">welcome study servlet</h1>");//�ñ�����һ��Ϣ���Զ����е�
        //�������Ϣ����ⲻ��ͬһ��
         out.print("hello");
         out.print("world");
        //������ӵı�ǩ<br>
		 out.print("<br>");
         out.print("��Һã�");
         out.print("</head>");
         out.print("</html>");

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
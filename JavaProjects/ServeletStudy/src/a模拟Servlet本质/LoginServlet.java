package a模拟Servlet本质;

/**
 * 这是一个java小程序，需要放在服务器上面，是Servlet规范的实现者
 * 所以编写服务器端小程序的时候，不能随意编写,需要实现Servlet接口
 * 由JavaWeb程序员编写
 *下面的Java小程序实现登录功能
 */

public class LoginServlet implements Servlet{

    @Override
    public void service() {
        System.out.println("连接数据库，验证用户名和密码...");
    }
}

package a模拟Servlet本质;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * 这里模拟Tomcat服务器，服务器是Servlet接口的调用者
 * Tomcat，又称：
 *      WebServer
 *      web服务器
 *      Web Container
 *      Web 容器
 *      面向接口Servlet调用
 */
/*
    在运行的时候由于编码方式的不一致导致类加载失败
    我们在设置idea的编码方式时这样设置：File->Settings->Editor->File Encodings,将里面的
    Global Encoding 设为UTF-8
    Project Encoding 设为UTF-8
    下面的properties文件的默认编码是GBK，改为UTF-8
    这样在读取属性配置文件中的中文就不会把中文读成乱码而无法加载了
 */
public class Tomcat {

    public static void main(String[] args) {

        System.out.println("服务器已启动");

        //想让服务器一直接收请求，给个while循环
        while (true){
            System.out.println("请打开浏览器，在浏览器地址栏上输入请求路径");
            Scanner scanner = new Scanner(System.in);
            String request = scanner.nextLine();

        /*
            浏览器上的请求路径和底层资源是一一绑定关系
            怎么去绑定，是由Web程序员来绑定和维护的
            javaWeb程序员要编写一个.xml的配置文件，在其中将请求路径和底层资源建立连接
            进而绑定起来，然后Web服务器通过将接收请求中的key值将配置文件中的与key值对应的value值（即底层资源）
            读取到，进而做出处理，具体如下
         */

            //Tomcat读取web.xml文件
            try {

                FileReader reader = new FileReader("F:\\Git_Repositories\\J2EE-servelet\\JavaProjects\\ServeletStudy\\src\\a模拟Servlet本质\\web.properties");
                Properties properties = new Properties();
                properties.load(reader);

                //通过key获取对象
                String servletClassName = properties.getProperty(request);
                System.out.println(servletClassName);

                //通过反射机制创建对象
                Class c = Class.forName(servletClassName);
                Object obj = c.newInstance();
                //因为三个java程序都实现了Servlet接口,将对象强制转换为Servlet类型,动态绑定
                Servlet st = (Servlet)obj;

                //web服务器只知道Servlet规范中的方法，直接调就行了，但底层还是调我们实现的Servlet接口的方法
                st.service();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }


    }

}

package com.servletstudy.jdbc;

import com.servletstudy.utils.JDBCUtil;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListServletEmp implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = servletResponse.getWriter();

            out.print("<html>");
            out.print( "   <title>员工信息</title>");
            out.print( "<body>");
            out.print( "<h3 align=\"center\">员工列表</h3>");
            out.print( "<hr width=\"60%\">");
            out.print( "<table border=\"1\" align=\"center\" width=\"50%\">");
            out.print( "   <tr align=\"center\">");
            out.print( "       <th>序号</th>");
            out.print( "       <th>员工编号</th>");
            out.print( "       <th>员工姓名</th>");
            out.print( "       <th>员工薪资</th>");
            out.print( "   </tr>");

        try {

            conn = JDBCUtil.getConnection();

            stmt = conn.createStatement();

            String sql = "select empno,ename,sal from emp";
            rs = stmt.executeQuery(sql);

            int i = 1;
            while (rs.next()){

                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                Double sal = rs.getDouble("sal");
                out.print( "   <tr align=\"center\">");
                out.print( "       <td>"+(i++)+"</td>");
                out.print( "       <td>"+empno+"</td>");
                out.print( "       <td>"+ename+"</td>");
                out.print( "       <td>"+sal+"</td>");
                out.print( "   </tr>");

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(conn,stmt,rs);
        }

        out.print( "</table>");
        out.print( "</body>");
        out.print( "</html>");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

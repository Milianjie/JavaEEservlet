package com.servletstudy.utils;

import java.sql.*;

public class JDBCUtil {
    /**
     * 注册MySQL数据库驱动
     */
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private JDBCUtil(){}

    /**
     * 获取MySQL数据库连接对象
     * @return  数据库连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy?useSSL=false","root","rong195302");

    }

    /**
     * 关闭资源
     * @param conn  数据库连接对象
     * @param ps    数据库（预编译）对象
     * @param rs    查询结果集对象
     */
    public static void close(Connection conn, Statement ps, ResultSet rs){

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

}

package com.neusoft.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    //定义成员变量DataSource,可以切换不同的连接池
    private static DataSource ds;

    //初始化设置
    static {
        try{
            //加载配置文件
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //2.获取 定义成员变量DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接
     */
    public static Connection getConnection() throws SQLException
    {
        return ds.getConnection();
    }
    /**
     * 释放资源
     */

    public static void close(ResultSet rs,Statement stmt,Connection conn){
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement stmt, Connection conn){
        close(null,stmt,conn);
    }

    /**
     * 获取连接池方法
     */
    public static DataSource getDs()
    {
        return ds;
    }
}

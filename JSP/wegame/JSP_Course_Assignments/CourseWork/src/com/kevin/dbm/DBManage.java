package com.kevin.dbm;

import java.sql.*;

/**
 * @author Kevin KDA on 2019/11/8 11:44
 * @version 1.0
 * @project JSP_Course_Assignments
 * @package com.kevin.dao
 * @classname DBManage
 * @description TODO 提供数据库管理
 * @interface/enum
 */
public class DBManage {
    static Connection con = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;
    /**
     * 连接MSSQL
     */
//	private final static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private final static String url = "jdbc:sqlserver://localhost;databasename=dvdDB";
    /**
     * 连接MySQL
     */
    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String url = "jdbc:mysql://127.0.0.1:3306/jsp?characterEncoding=UTF-8";
//    private final static String url = "jdbc:mysql://192.168.1.22:3306/jsp?characterEncoding=UTF-8";
    /**
     * 连接Oracle
     */
    //连接驱动
//	private final static String driver = "oracle.jdbc.driver.OracleDriver";
    //访问oracle路径
//	private final static String url = "jdbc:oracle:thin:@localhost:1521:ORCL";

    /**
     * @param []
     * @throws
     * @author Kevin KDA on 2019/11/20 00:49
     * @description DBManage / getConnect TODO 提供一个私有方法来获取数据库连接
     * @returns java.sql.Connection
     */
    private static Connection getConnect() {
        try {
            Class.forName(driver);
            //连接MSSQL
//            con = DriverManager.getConnection(url, "kevin", "0921");
            //连接mysql
            con = DriverManager.getConnection(url, "kevin", "0921");
            //连接mysql
//			con = DriverManager.getConnection(url,"scott","tiger");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static ResultSet getResultSet(String sql) {
        con = getConnect();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getResultSet(String sql, Object[] params) {
        con = getConnect();
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int modifyEntiy(String sql, Object[] params) {
        int num = 0;
        con = getConnect();
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            num = ps.executeUpdate();
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * @param []
     * @throws
     * @author Kevin KDA on 2019/11/20 00:49
     * @description DBManage / closeAll TODO 提供关闭数据库连接的方法
     * @returns void
     */
    public static void closeAll() {
        if (ps != null) {
            try {
                ps.close();
                ps = null;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

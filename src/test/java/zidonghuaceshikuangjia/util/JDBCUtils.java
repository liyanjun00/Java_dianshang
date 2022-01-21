package zidonghuaceshikuangjia.util;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class JDBCUtils {
    private static Connection getConnection() {
        //定义数据库连接
        //Oracle：jdbc:oracle:thin:@localhost:1521:DBName
        //SqlServer：jdbc:microsoft:sqlserver://localhost:1433; DatabaseName=DBName
        //MySql：jdbc:mysql://localhost:3306/DBName
        String url = "jdbc:mysql://mall.lemonban.com/yami_shops?useUnicode=true&characterEncoding=utf-8";
        String user = "lemon";
        String password = "lemon123";
        //定义数据库连接对象
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Object getsingresule(String sql) {
        String newsql=ReplaceparamUtil.paramReplace(sql);
        QueryRunner qr = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        Object data = null;
        try {
            data = qr.query(conn, newsql, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static Map<String, Object> getMapResult(String sql) {
        QueryRunner qr = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        Map<String,Object> data = null;
        try {
            data = qr.query(conn, sql, new MapHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}

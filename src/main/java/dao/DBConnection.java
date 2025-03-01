package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/customer_manager_db"
		    + "?useUnicode=true"
		    + "&characterEncoding=UTF-8"
		    + "&serverTimezone=Asia/Tokyo"
		    + "&useSSL=false";

    private static final String USER = "root"; // ユーザー名
    private static final String PASSWORD = "pass"; // パスワード

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DB接続成功: " + conn.getMetaData().getURL());
            System.out.println("使用データベース: " + conn.getCatalog());
            System.out.println("接続ユーザー: " + conn.getMetaData().getUserName());
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBCドライバが見つからず");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("DB接続エラー");
            e.printStackTrace();
            return null;
        }
    }
}

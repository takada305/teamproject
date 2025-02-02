package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/customer_manager_db?useSSL=false&serverTimezone=UTC";
    // データベース名
    private static final String USER = "root"; // ユーザー名
    private static final String PASSWORD = "pass"; // パスワード

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBCドライバのロードに失敗しました", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

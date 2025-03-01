package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistrictDAO {
    public static List<String> getAllDistricts() {
        List<String> districtList = new ArrayList<>();
        String sql = "SELECT DISTINCT district_name FROM district ORDER BY district_name";

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("DB 接続失敗: conn が NULL です");
                return null;
            }
            System.out.println("DB 接続成功");

            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                System.out.println("SQL 実行: " + sql);

                while (rs.next()) {
                    String district = rs.getString("district_name").trim();
                    System.out.println("取得した district: " + district);
                    districtList.add(district);
                }

                if (districtList.isEmpty()) {
                    System.out.println("districtList が空");
                }

            } catch (SQLException e) {
                System.err.println("SQL 実行エラー:");
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println("DB 接続エラー:");
            e.printStackTrace();
        }

        System.out.println("districtList = " + districtList);
        return districtList;
    }
}

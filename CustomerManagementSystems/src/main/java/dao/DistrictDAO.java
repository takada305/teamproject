package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistrictDAO {

    // 全地区名を取得
	public static List<String> getAllDistricts() {
	    List<String> districts = new ArrayList<>();
	    String sql = "SELECT district_name FROM districts";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            String district = rs.getString("district_name");
	            System.out.println("取得した地区: " + district); // デバッグログ
	            districts.add(district);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    System.out.println("最終的な地区リスト: " + districts); 
	    return districts;
	}
}


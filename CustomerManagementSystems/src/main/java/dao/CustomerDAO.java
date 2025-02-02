package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // すべての顧客情報を取得
    public static List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customer";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setKana(rs.getString("kana"));
                customer.setPostCode(rs.getString("post_code"));
                customer.setAddress(rs.getString("address"));
                customer.setGender(rs.getString("gender"));
                customer.setBirthday(rs.getString("birthday"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    // 顧客を追加
    	public static boolean addCustomer(String name, String kana, String postCode, String address, 
            String district, String gender, String birthday, String phoneNumber) {

    		String sql = "INSERT INTO customer (name, kana, post_code, address, district, gender, birthday, phone_number) "
    		           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // パラメータ
            ps.setString(1, name);
            ps.setString(2, kana);
            ps.setString(3, postCode);
            ps.setString(4, address);
            ps.setString(5, district); 
            ps.setString(6, gender);
            ps.setString(7, birthday);
            ps.setString(8, phoneNumber);

            // SQLを実行
            int result = ps.executeUpdate();
            return result > 0; // 挿入が成功

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // エラー
        }
    }
    	
    	// 顧客情報を更新
    	public static boolean updateCustomer(int id, String name, String kana, String postCode, String address, String district, String gender, String birthday, String phoneNumber) {
    	    String sql = "UPDATE customer SET name = ?, kana = ?, post_code = ?, address = ?, district = ?, gender = ?, birthday = ?, phone_number = ? WHERE customer_id = ?";

    	    try (Connection conn = DBConnection.getConnection();
    	         PreparedStatement ps = conn.prepareStatement(sql)) {

    	        // パラメータを設定
    	        ps.setString(1, name);
    	        ps.setString(2, kana);
    	        ps.setString(3, postCode);
    	        ps.setString(4, address);
    	        ps.setString(5, district); 
    	        ps.setString(6, gender);
    	        ps.setString(7, birthday);
    	        ps.setString(8, phoneNumber);
    	        ps.setInt(9, id);

    	        // 更新を実行
    	        int rowsUpdated = ps.executeUpdate();
    	        return rowsUpdated > 0; // 更新が成功
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        return false; // エラー
    	    }
    	}

   
 // 顧客を削除
    public static boolean deleteCustomerById(int id) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0; // 削除が成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // エラー
        }
    }
    public static Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        Customer customer = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setId(rs.getInt("customer_id"));
                    customer.setName(rs.getString("name"));
                    customer.setKana(rs.getString("kana"));
                    customer.setPostCode(rs.getString("post_code"));
                    customer.setAddress(rs.getString("address"));
                    customer.setDistrict(rs.getString("district")); 
                    customer.setGender(rs.getString("gender"));
                    customer.setBirthday(rs.getString("birthday"));
                    customer.setPhoneNumber(rs.getString("phone_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer; 
    }

}

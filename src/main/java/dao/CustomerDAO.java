package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // 顧客情報を取得
    public static List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT customer_id, name, kana, post_code, district, gender, birthday, phone_number, created_at, updated_at FROM customer";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setKana(rs.getString("kana"));
                customer.setPostCode(rs.getString("post_code"));
                customer.setDistrict(rs.getString("district"));
                customer.setGender(rs.getString("gender"));
                customer.setBirthday(rs.getString("birthday"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setCreatedAt(rs.getTimestamp("created_at"));
                customer.setUpdatedAt(rs.getTimestamp("updated_at"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    // 顧客名で検索
    public static List<Customer> searchCustomersByName(String name) {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT customer_id, name, kana, post_code, district, gender, birthday, phone_number, created_at, updated_at FROM customer WHERE name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%"); // 部分一致で検索
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("customer_id"));
                    customer.setName(rs.getString("name"));
                    customer.setKana(rs.getString("kana"));
                    customer.setPostCode(rs.getString("post_code"));
                    customer.setDistrict(rs.getString("district"));
                    customer.setGender(rs.getString("gender"));
                    customer.setBirthday(rs.getString("birthday"));
                    customer.setPhoneNumber(rs.getString("phone_number"));
                    customer.setCreatedAt(rs.getTimestamp("created_at"));
                    customer.setUpdatedAt(rs.getTimestamp("updated_at"));
                    customerList.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    // 顧客を追加
    public static boolean addCustomer(String name, String kana, String postCode, 
            String district, String gender, String birthday, String phoneNumber) {

        String sql = "INSERT INTO customer (name, kana, post_code, district, gender, birthday, phone_number, created_at, updated_at) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // パラメータ
            ps.setString(1, name);
            ps.setString(2, kana);
            ps.setString(3, postCode);
            ps.setString(4, district); 
            ps.setString(5, gender);
            ps.setString(6, birthday);
            ps.setString(7, phoneNumber);

            int result = ps.executeUpdate();
            return result > 0; // 挿入成功

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // エラー
        }
    }

    // 顧客情報を更新
    public static boolean updateCustomer(int id, String name, String kana, String postCode, String district, String gender, String birthday, String phoneNumber) {
        String sql = "UPDATE customer SET name = ?, kana = ?, post_code = ?, district = ?, gender = ?, birthday = ?, phone_number = ? WHERE customer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, kana);
            ps.setString(3, postCode);
            ps.setString(4, district); 
            ps.setString(5, gender);
            ps.setString(6, birthday);
            ps.setString(7, phoneNumber);
            ps.setInt(8, id);

            // 更新を実行
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // 成功
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
            return rowsDeleted > 0; // 削除成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // エラー
        }
    }

    public static Customer getCustomerById(int id) {
        String sql = "SELECT customer_id, name, kana, post_code, district, gender, birthday, phone_number, created_at, updated_at FROM customer WHERE customer_id = ?";
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
                    customer.setDistrict(rs.getString("district"));
                    customer.setGender(rs.getString("gender"));
                    customer.setBirthday(rs.getString("birthday"));
                    customer.setPhoneNumber(rs.getString("phone_number"));
                    customer.setCreatedAt(rs.getTimestamp("created_at"));
                    customer.setUpdatedAt(rs.getTimestamp("updated_at"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer; 
    }
}

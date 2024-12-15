package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // 顧客情報をデータベースに保存する
    public static boolean addCustomer(String name, String kana, String postCode, String address, String gender, String birthday, String phoneNumber) {
        String sql = "INSERT INTO customer (name, kana, post_code, address, gender, birthday, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, kana);
            ps.setString(3, postCode);
            ps.setString(4, address);
            ps.setString(5, gender);
            ps.setString(6, birthday);
            ps.setString(7, phoneNumber);

            int result = ps.executeUpdate();
            return result > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 全顧客情報を取得
    public static List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        List<Customer> customerList = new ArrayList<>();

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
    public static Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        Customer customer = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setId(rs.getInt("customer_id"));
                    customer.setName(rs.getString("name"));
                    customer.setKana(rs.getString("kana"));
                    customer.setPostCode(rs.getString("post_code"));
                    customer.setAddress(rs.getString("address"));
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

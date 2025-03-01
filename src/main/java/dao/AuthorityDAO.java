package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AuthorityDAO {
    public static boolean updateAuthority(String userId, String newAuthority) {
        String sql = "UPDATE m_user SET authority_code = ? WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newAuthority);
            ps.setString(2, userId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

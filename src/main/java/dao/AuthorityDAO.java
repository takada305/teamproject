package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorityDAO {
    // 権限を更新
    public static boolean updateAuthority(String userId, String newAuthority) {
        String sql = "UPDATE m_user SET authority_code = ? WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newAuthority);
            ps.setString(2, userId);

            int result = ps.executeUpdate();
            return result > 0; // 更新成功
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

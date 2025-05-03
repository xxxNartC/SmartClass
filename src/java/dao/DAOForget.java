package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class DAOForget extends DBContext {
    
    // Kiểm tra xem email có tồn tại trong database hay không
    public User checkEmail(String email) {
        try {
            String sql = "SELECT * FROM account WHERE email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setAccount_id(rs.getInt("account_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setDob(rs.getString("dob")); 
                user.setPhone(rs.getString("phone"));
                user.setAvatar(rs.getString("avatar"));
                user.setActive(rs.getInt("active"));
                user.setRole_id(rs.getInt("role_id"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOForget.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; // Trả về null nếu email không tồn tại
    }

    // Kiểm tra username và phone có khớp với email không
    public boolean checkUsernameAndPhone(String username, String phone, String email) {
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND phone = ? AND email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, phone);
            stm.setString(3, email);
            ResultSet rs = stm.executeQuery();
            return rs.next(); // Trả về true nếu tìm thấy khớp
        } catch (SQLException ex) {
            Logger.getLogger(DAOForget.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // Trả về false nếu không tìm thấy
    }

    public boolean updatePassword(String email, String newPassword) {
    try {
        // Cập nhật mật khẩu vào database với mật khẩu đã mã hóa
        String sql = "UPDATE account SET password = ? WHERE email = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, newPassword); // Đã mã hóa trước đó
        stm.setString(2, email);
        int rowsUpdated = stm.executeUpdate();
        return rowsUpdated > 0; // Trả về true nếu cập nhật thành công
    } catch (SQLException ex) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false; // Trả về false nếu cập nhật thất bại
}
}

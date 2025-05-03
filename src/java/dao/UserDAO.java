/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.*;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class UserDAO extends DBContext {

    public User login(String usernameOrEmail, String password) {
        String sql = "SELECT * FROM account WHERE (username = ? OR email = ?) AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usernameOrEmail);
            statement.setString(2, usernameOrEmail);
            statement.setString(3, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"), rs.getString("dob"), rs.getString("phone"), rs.getString("avatar"), rs.getInt("active"), rs.getInt("role_id"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean register(User user) {
        String sql = "INSERT INTO account (username, password, fullname, email, dob, phone, active, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullname());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getDob());
            statement.setString(6, user.getPhone());
            statement.setInt(7, user.getActive());
            statement.setInt(8, user.getRole_id());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkUsernameExists(String username) {
        String sql = "SELECT * FROM account WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            return rs.next();
            // Returns true if a row is found, indicating username exists   
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM account";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"), rs.getString("dob"), rs.getString("phone"), rs.getString("avatar"), rs.getInt("active"), rs.getInt("role_id"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public boolean approveRoleChangeRequest(int account_id, int newRoleId) {
        String sql = "UPDATE account SET role_id = ? WHERE account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newRoleId);
            statement.setInt(2, account_id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE account SET username = ?, phone = ?, dob = ? WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getDob());
            statement.setString(4, user.getEmail());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateUserPassword(User user) {
        String sql = "UPDATE account SET password = ? WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getPassword()); // Mật khẩu đã được mã hóa
            statement.setString(2, user.getEmail());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkEmailExists(String email) {
        String sql = "SELECT * FROM account WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            return rs.next();
            // Returns true if a row is found, indicating username exists   
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateUserActiveStatus(User user) {
        String sql = "UPDATE account SET active = ? WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getActive());
            statement.setString(2, user.getUsername());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateUser(User user, byte[] avatarBytes) {
        String sql = "UPDATE account SET username = ?, phone = ?, dob = ?"
                + (avatarBytes != null ? ", avatar = ?" : "")
                + " WHERE account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getDob());

            if (avatarBytes != null) {
                statement.setBytes(4, avatarBytes); // Update avatar if new image is uploaded
                statement.setInt(5, user.getAccount_id());
            } else {
                statement.setInt(4, user.getAccount_id());
            }

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateUserAvatar(User user) {
        String sql = "UPDATE account SET avatar = ? WHERE account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getAvatar());
            statement.setInt(2, user.getAccount_id());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public byte[] getAvatar(int accountId) {
        String sql = "SELECT avatar FROM account WHERE account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getBytes("avatar"); // Retrieve binary data
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

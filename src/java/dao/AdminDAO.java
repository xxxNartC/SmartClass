/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Role;

/**
 *
 * @author admin
 */
public class AdminDAO extends DBContext {

    public List<Role> getAllRole() {
        List<Role> list = new ArrayList<>();
        String sql = "SELECT * FROM ROLE WHERE role_id > 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setRole_id(rs.getInt(1));
                r.setRole_name(rs.getString(2));
                list.add(r);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void editRole(String id, String role) {
        String sql = "UPDATE Account SET role_id = ? WHERE account_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, role);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
}

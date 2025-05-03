/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.AccountReport;

/**
 *
 * @author ACER
 */
public class AccountDAO extends DBContext {

    public Account getAccountById(int account_id) {
        String query = "SELECT * FROM Account WHERE account_id = ?";
        Account account = null;

        try {
            Connection conn = connection; // Sử dụng connection từ DBContext
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, account_id); // Gán giá trị account_id cho câu truy vấn

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Tạo đối tượng Account từ kết quả truy vấn
                account = new Account();
                account.setAccount_id(rs.getInt("account_id"));
                account.setUsername(rs.getString("username"));
                account.setFullname(rs.getString("fullname"));
                account.setEmail(rs.getString("email"));
                account.setDob(rs.getString("dob"));
                account.setPhone(rs.getString("phone"));
                account.setAvatar(rs.getString("avatar"));
                account.setRole_id(rs.getInt("role_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account; // Trả về đối tượng Account
    }

    public Account selectTTAcountLecturer(String subject_id) {
        String query = "SELECT a.account_id, a.username, a.fullname, a.email, a.phone, a.avatar, a.role_id "
                + "FROM Account a "
                + "INNER JOIN Subject s ON a.account_id = s.lecturer_id "
                + "WHERE s.subject_id = ?";
        Account lecturer = null;

        try {
            Connection conn = connection; // Sử dụng connection từ DBContext
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, subject_id); // Gán giá trị subject_id cho câu truy vấn

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Tạo đối tượng Account từ kết quả truy vấn
                lecturer = new Account();
                lecturer.setAccount_id(rs.getInt("account_id"));
                lecturer.setUsername(rs.getString("username"));
                lecturer.setFullname(rs.getString("fullname"));
                lecturer.setEmail(rs.getString("email"));
                lecturer.setPhone(rs.getString("phone"));
                lecturer.setAvatar(rs.getString("avatar"));
                lecturer.setRole_id(rs.getInt("role_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lecturer; // Trả về đối tượng Account giảng viên
    }
    public List<Account> getAllAccount() {
    List<Account> listAccounts = new ArrayList<>();
    String sql = "SELECT account_id, username, password, fullname, email, dob, phone, avatar, otp, active, role_id FROM Account"; // Cập nhật tên bảng nếu cần
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Account account = new Account();
            account.setAccount_id(rs.getInt("account_id"));
            account.setUsername(rs.getString("username"));
            account.setPassword(rs.getString("password"));
            account.setFullname(rs.getString("fullname"));
            account.setEmail(rs.getString("email"));
            account.setDob(rs.getString("dob"));
            account.setPhone(rs.getString("phone"));
            account.setAvatar(rs.getString("avatar"));
            account.setOtp(rs.getString("otp"));
            account.setActive(rs.getInt("active"));
            account.setRole_id(rs.getInt("role_id"));         
            listAccounts.add(account);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listAccounts;
}
public List<AccountReport> getAllPendingReports() throws SQLException {
        List<AccountReport> reports = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement("SELECT ar.*, ra.username AS reported_username, a.username AS reporter_username FROM AccountReport ar INNER JOIN Account a ON ar.account_id = a.account_id INNER JOIN Account ra ON ar.reported_account_id = ra.account_id WHERE ar.status = 0"); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AccountReport report = new AccountReport();
                report.setReportId(rs.getInt("report_id"));
                report.setAccountId(rs.getInt("account_id"));
                report.setReportedAccountId(rs.getInt("reported_account_id"));
                report.setReportType(rs.getString("report_type"));
                report.setReportDescription(rs.getString("report_description"));
                report.setReportDate(rs.getDate("report_date"));
                report.setStatus(rs.getInt("status"));
                report.setReporterUsername(rs.getString("reporter_username"));
                report.setReportedUsername(rs.getString("reported_username"));
                reports.add(report);
            }
        }
        return reports;
    }
public String getUsernameById(int accountId) {
        String username = null;
        String sql = "SELECT username FROM Account WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    username = rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }


    public void deleteReport(int reportId) throws SQLException {
        try (
                PreparedStatement ps = connection.prepareStatement("DELETE FROM AccountReport WHERE report_id = ?")) {
            ps.setInt(1, reportId);
            ps.executeUpdate();
        }
    }



public void updateRole(int accountId, int newRoleId) {
        String sql = "UPDATE Account SET role_id = ? WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newRoleId);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
    
 
    
    public String getFullnameById(int accountId) {
        String fullname = null;
        String sql = "SELECT fullname FROM Account WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    fullname = rs.getString("fullname");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullname;
    }
    
    public String getEmailById(int accountId) {
        String email = null;
        String sql = "SELECT email FROM Account WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("email");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    
    // get student information
    public Account getStudentInfoById(String sid) {
        String sql = "select * from account where account_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, sid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setAccount_id(rs.getInt(1));
                acc.setUsername(rs.getString(2));
                acc.setFullname(rs.getString(4));
                acc.setEmail(rs.getString(5));
                acc.setDob(rs.getString(6));
                acc.setPhone(rs.getString(7));
                return acc;
            }
        } catch (SQLException e) {
        }

        return null;
    }

    public Integer getAccountIdByName(String fullName) {
        String sql = "SELECT account_id FROM account WHERE fullname = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, fullName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("account_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Returns null if account not found
    }

    public void updateAccountActiveStatus(int accountId, int activeStatus) throws SQLException {
        String sql = "UPDATE account SET active = ? WHERE account_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, activeStatus);
            st.setInt(2, accountId);
            st.executeUpdate();
        }
    }

}

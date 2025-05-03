/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.AccountReport;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author bacht
 */
public class ReportDAO extends DBContext {

    public List<AccountReport> getReportsByAccountId(int accountId) {
        List<AccountReport> reports = new ArrayList<>();
        String sql = "SELECT ar.report_id, ar.reported_account_id, a.fullname AS account, "
                + "ar.report_description AS reason, ar.status, ar.report_date AS dateSubmitted "
                + "FROM accountreport ar "
                + "JOIN account a ON ar.reported_account_id = a.account_id "
                + "WHERE ar.account_id = ?";

        try (PreparedStatement st = connection.prepareStatement(sql);) {
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                AccountReport report = new AccountReport();
                report.setReportId(rs.getInt("report_id"));
                report.setReportedAccountId(rs.getInt("reported_account_id"));
                report.setReportType(rs.getString("reason"));
                report.setStatus(rs.getInt("status"));
                report.setReportDate(rs.getDate("dateSubmitted"));

                // Assuming `AccountReport` has a field for reported account's name.
                report.setReportedAccountName(rs.getString("account"));

                reports.add(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }

    public void insertReport(AccountReport report) {
        String sql = "INSERT INTO accountreport (account_id, reported_account_id, report_type, report_description, report_date, status) "
                + "VALUES (?, ?, ?, ?, GETDATE(), 0)"; // Using GETDATE() for current date and setting default status to 1

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, report.getAccountId());
            st.setInt(2, report.getReportedAccountId());
            st.setString(3, report.getReportType());
            st.setString(4, report.getReportDescription());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReport(AccountReport report) {
        String sql = "UPDATE accountreport SET reported_account_id = ?, report_type = ?, report_description = ?, report_date = GETDATE() "
                + "WHERE report_id = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, report.getReportedAccountId());
            st.setString(2, report.getReportType());
            st.setString(3, report.getReportDescription());
            st.setInt(4, report.getReportId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AccountReport getReportById(int reportId) {
        String sql = "SELECT ar.report_id, ar.account_id, ar.reported_account_id, ar.report_type, ar.report_description, ar.report_date, ar.status, a.fullname AS account "
                + "FROM accountreport ar "
                + "JOIN account a ON ar.reported_account_id = a.account_id "
                + "WHERE ar.report_id = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, reportId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                AccountReport report = new AccountReport();
                report.setReportId(rs.getInt("report_id"));
                report.setAccountId(rs.getInt("account_id"));
                report.setReportedAccountId(rs.getInt("reported_account_id"));
                report.setReportType(rs.getString("report_type"));
                report.setReportDescription(rs.getString("report_description"));
                report.setReportDate(rs.getDate("report_date"));
                report.setStatus(rs.getInt("status"));
                report.setReportedAccountName(rs.getString("account"));
                return report;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AccountReport> getAllReports() {
        List<AccountReport> reports = new ArrayList<>();
        String sql = "SELECT report_id, account_id, reported_account_id, report_description, status, report_date FROM accountreport";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                AccountReport report = new AccountReport();
                report.setReportId(rs.getInt("report_id"));
                report.setAccountId(rs.getInt("account_id"));
                report.setReportedAccountId(rs.getInt("reported_account_id"));
                report.setReportDescription(rs.getString("report_description"));
                report.setStatus(rs.getInt("status"));
                report.setReportDate(rs.getDate("report_date"));
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public void updateReportStatus(int reportId, int status) throws SQLException {
        String sql = "UPDATE accountreport SET status = ? WHERE report_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, status);
            st.setInt(2, reportId);
            st.executeUpdate();
        }
    }

    public int getReportedAccountIdByReportId(int reportId) throws SQLException {
        String sql = "SELECT reported_account_id FROM accountreport WHERE report_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, reportId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("reported_account_id");
            }
        }
        return -1; // return -1 or handle if report ID not found
    }

}

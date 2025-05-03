package dao;

import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.AccountReport;

public class BlockedAccountDAO extends DBContext {

    public boolean insertAccountReport(AccountReport accountReport) {
        boolean check = false;
        String sql = "INSERT INTO accountreport (account_id, reported_account_id, report_type, report_description, report_date, status) "
                + "VALUES (?, ?, ?, ?, ?, 0)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, accountReport.getAccountId());
            stm.setInt(2, accountReport.getReportedAccountId());
            stm.setString(3, accountReport.getReportType());
            stm.setString(4, accountReport.getReportDescription());
            Date sqlDate = new Date(accountReport.getReportDate().getTime());
            stm.setDate(5, sqlDate);
            int number = stm.executeUpdate();
            check = number > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
}

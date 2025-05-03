/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.ReportDAO;
import dao.AccountDAO;
import model.AccountReport;
import model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/ManageReportController")
public class ManageReportsController extends HttpServlet {

    private ReportDAO reportDAO = new ReportDAO();
    private AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all reports
        List<AccountReport> reports = reportDAO.getAllReports();
        List<AccountReport> reportDisplayList = new ArrayList<>();

        for (AccountReport report : reports) {
            AccountReport displayReport = new AccountReport();

            displayReport.setReportId(report.getReportId());
            displayReport.setReportDescription(report.getReportDescription());
            displayReport.setStatus(report.getStatus());
            displayReport.setReportDate(report.getReportDate());

            // Fetch reported account's name
            Account reportedAccount = accountDAO.getAccountById(report.getReportedAccountId());
            displayReport.setReportedAccountName(reportedAccount != null ? reportedAccount.getFullname() : "Unknown");

            // Fetch account who submitted the report
            Account reportByAccount = accountDAO.getAccountById(report.getAccountId());
            displayReport.setReportByName(reportByAccount != null ? reportByAccount.getFullname() : "Unknown");

            reportDisplayList.add(displayReport);
        }

        // Set attributes and forward to JSP
        request.setAttribute("reportDisplayList", reportDisplayList);
        request.getRequestDispatcher("ManageReportAccount.jsp").forward(request, response);
    }
}

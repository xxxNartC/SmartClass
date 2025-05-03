/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import dao.AccountDAO;
import dao.ReportDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.AccountReport;

@WebServlet("/report")
public class ReportController extends HttpServlet {

    private ReportDAO reportService = new ReportDAO();
    private AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer accountId = (Integer) request.getSession().getAttribute("account_id");

        if (accountId != null) {
            List<AccountReport> reports = reportService.getReportsByAccountId(accountId);
            request.setAttribute("reports", reports);

            String action = request.getParameter("action");
            String reportIdParam = request.getParameter("id");

            if ("edit".equals(action) && reportIdParam != null && !reportIdParam.isEmpty()) {
                int reportId = Integer.parseInt(reportIdParam);
                AccountReport report = reportService.getReportById(reportId);

                if (report != null) {
                    if (report.getStatus() == 1 || report.getStatus() == 2) {
                        // Status is 1 or 2, so editing is not allowed
                        request.setAttribute("errorMessage", "You cannot edit this report as it is either pending or approved.");
                    } else {
                        // Status is 0, editing is allowed
                        request.setAttribute("editMode", true);
                        request.setAttribute("reportId", report.getReportId());
                        request.setAttribute("accountToReport", report.getReportedAccountName());
                        request.setAttribute("reason", report.getReportType());
                        request.setAttribute("details", report.getReportDescription());
                    }
                }
            }

            // Forward to JSP regardless, so it can display the error message or form as needed
            request.getRequestDispatcher("ReportAccount.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountToReport = request.getParameter("accountToReport");
        String reason = request.getParameter("reason");
        String details = request.getParameter("details");
        Integer accountId = (Integer) request.getSession().getAttribute("account_id"); // Logged-in user's ID

        // Check if this is an update action
        String reportIdParam = request.getParameter("reportId");
        boolean isUpdate = reportIdParam != null && !reportIdParam.isEmpty();

        // Validate if the reported account exists
        Integer reportedAccountId = accountDAO.getAccountIdByName(accountToReport);
        if (reportedAccountId == null) {
            // Reported account doesn't exist, set error message and retain form data
            request.setAttribute("errorMessage", "Account name does not exist. Please try again.");
            request.setAttribute("accountToReport", accountToReport);
            request.setAttribute("reason", reason);
            request.setAttribute("details", details);

            // If we're in edit mode, ensure it stays in edit mode by setting editMode to true
            if (isUpdate) {
                request.setAttribute("editMode", true);
                request.setAttribute("reportId", reportIdParam);
            }

            // Reload report list and forward back to the JSP
            List<AccountReport> reports = reportService.getReportsByAccountId(accountId);
            request.setAttribute("reports", reports);
            request.getRequestDispatcher("ReportAccount.jsp").forward(request, response);
            return;
        }

        if (isUpdate) {
            // Update existing report
            int reportId = Integer.parseInt(reportIdParam);
            AccountReport report = new AccountReport();
            report.setReportId(reportId);
            report.setAccountId(accountId);
            report.setReportedAccountId(reportedAccountId);
            report.setReportType(reason);
            report.setReportDescription(details);
            report.setReportDate(new java.util.Date());

            reportService.updateReport(report);
        } else {
            // Insert new report
            AccountReport report = new AccountReport();
            report.setAccountId(accountId);
            report.setReportedAccountId(reportedAccountId);
            report.setReportType(reason);
            report.setReportDescription(details);
            report.setReportDate(new java.util.Date());

            reportService.insertReport(report);
        }

        // Redirect to reload the report list after saving
        response.sendRedirect("report");
    }

}

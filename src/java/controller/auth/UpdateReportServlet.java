package controller.auth;

import dao.ReportDAO;
import dao.AccountDAO;
import model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import controller.common.MailUtil;

@WebServlet("/UpdateReportServlet")
public class UpdateReportServlet extends HttpServlet {

    private ReportDAO reportDAO = new ReportDAO();
    private AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int reportId = Integer.parseInt(request.getParameter("reportId"));

        try {
            if ("approve".equals(action)) {
                // 1. Update the report status to Approved (2)
                reportDAO.updateReportStatus(reportId, 2);

                // 2. Get reported account ID and deactivate it
                int reportedAccountId = reportDAO.getReportedAccountIdByReportId(reportId);
                accountDAO.updateAccountActiveStatus(reportedAccountId, 0);

                // 3. Send notification email to the reported account
                Account reportedAccount = accountDAO.getAccountById(reportedAccountId);
                if (reportedAccount != null && reportedAccount.getEmail() != null) {
                    String email = reportedAccount.getEmail();
                    String subject = "Account Suspension Notification";
                    String content = "Dear " + reportedAccount.getFullname() + ",\n\n"
                            + "Your account on our E-learning platform has been suspended due to a reported violation. "
                            + "If you believe this was a mistake, please contact our support team at support@elearning.com.\n\n"
                            + "Thank you,\nE-learning Support Team";

                    // Send the email
                    MailUtil.sendEmail(email, subject, content);
                }

            } else if ("reject".equals(action)) {
                // Only update the report status to Rejected (1)
                reportDAO.updateReportStatus(reportId, 1);

            } else if ("restore".equals(action)) {
                // 1. Update the report status to Rejected (1)
                reportDAO.updateReportStatus(reportId, 1);

                // 2. Get reported account ID and reactivate it
                int reportedAccountId = reportDAO.getReportedAccountIdByReportId(reportId);
                accountDAO.updateAccountActiveStatus(reportedAccountId, 1);
            }

            // Redirect back to ManageReportController to reload the page
            response.sendRedirect("ManageReportController");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

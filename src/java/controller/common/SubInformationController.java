package controller.common;

import dao.OrderDAO;
import dao.SubjectDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Subject;

@WebServlet(name = "SubInformationController", urlPatterns = {"/infor"})
public class SubInformationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response); // Forward GET requests to doPost
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");

        // Check if the action is "view"
        if ("view".equals(action)) {
            String subjectId = request.getParameter("subject_id");
            SubjectDAO subjectDAO = new SubjectDAO();
            Subject subject = subjectDAO.getSubjectByID(subjectId);

            if (subject != null) {
                boolean loggedIn = session != null && session.getAttribute("user") != null;
                boolean purchased = false; // Default to not purchased

                // If the user is logged in, check if they have purchased the subject
                if (loggedIn) {
                    int accountId = (int) session.getAttribute("account_id");
                    OrderDAO orderDAO = new OrderDAO();
                    purchased = orderDAO.hasPurchasedSubject(accountId, subjectId);
                }

                // Set attributes for the JSP
                request.setAttribute("subject", subject);
                request.setAttribute("purchased", purchased);
                request.setAttribute("loggedIn", loggedIn);

                request.getRequestDispatcher("sub_infor.jsp").forward(request, response);
            } else {
                response.sendRedirect("subjectlist");  // Redirect if subject not found
            }
        } else {
            response.sendRedirect("subjectlist");  // Redirect for any other action
        }
    }
}

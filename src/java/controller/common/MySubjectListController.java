package controller.common;

import dao.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Subject;

@WebServlet(name = "MySubjectListController", urlPatterns = {"/mysubjectlist"})
public class MySubjectListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("account_id") != null) {
            String accountId = session.getAttribute("account_id").toString();
            OrderDAO orderDAO = new OrderDAO();
            List<Subject> purchasedSubjects = orderDAO.getPurchasedSubjectsByAccountId(accountId);
            
            request.setAttribute("purchasedSubjects", purchasedSubjects);
            request.getRequestDispatcher("MySubjectList.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login if not logged in
        }
    }
}

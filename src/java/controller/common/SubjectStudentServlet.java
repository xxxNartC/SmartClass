/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common;

import dao.SubjectDAO;
import dao.SubjectStudentDAO;
import model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.GroupedSubject;

@WebServlet("/subjectStudents")
public class SubjectStudentServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer lecturerId = (Integer) session.getAttribute("account_id");

        if (lecturerId != null) {
            SubjectStudentDAO dao = new SubjectStudentDAO();
            List<GroupedSubject> groupedSubjectStudentList = dao.getGroupedSubjectStudentList(lecturerId);

            request.setAttribute("groupedSubjectStudentList", groupedSubjectStudentList);
            request.getRequestDispatcher("subject_students.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
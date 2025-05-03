/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.LearnerCourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author admin
 */
@WebServlet(name = "RateCourseController", urlPatterns = {"/rateCourse"})
public class RateCourseController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = "";
        LearnerCourseDAO lcd = new LearnerCourseDAO();

        // Lấy các tham số từ request
        HttpSession session = request.getSession();
        String account_id = String.valueOf(session.getAttribute("account_id"));
        String learner_id = account_id;
        request.setAttribute("learner_id", learner_id);
        String subject_id = request.getParameter("subject_id");
        String course_id = request.getParameter("course_id");
        String rating = request.getParameter("rating");
        String feedback = request.getParameter("feedback") != null ? request.getParameter("feedback").trim() : ""; // Kiểm tra null
        java.util.Date date = new java.util.Date();

        if (subject_id != null && course_id != null && rating != null && learner_id != null) {
            try {
                // Convert rating to integer
                int ratingInt = Integer.parseInt(rating);
                
                // Check if learner has already rated the course
                int learnerCourseId = lcd.getLearnerCourseIdByCourseIdAndLearnerId(course_id, learner_id);
                
                if (learnerCourseId > 0) {
                    // Update existing rating
                    if (!feedback.isBlank()) {
                        lcd.updateRateAndFeedBack(learnerCourseId, ratingInt, feedback, date);
                        message = "Thank you for your feedback";
                    } else {
                        lcd.updateRate(learnerCourseId, ratingInt, date);
                        message = "Thank you for your rating";
                    }
                } else {
                    // Insert new rating
                    if (!feedback.isBlank()) {
                        lcd.insertRateAndFeedBack(ratingInt, learner_id, course_id, feedback, date);
                        message = "Thank you for your feedback";
                    } else {
                        lcd.insertRate(ratingInt, learner_id, course_id, date);
                        message = "Thank you for your rating";
                    }
                }
            } catch (NumberFormatException e) {
                // Handle the NumberFormatException
                message = "Invalid rating format.";
            } catch (SQLException ex) {
                Logger.getLogger(RateCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Missing required parameters.";
        }

        request.getSession().setAttribute("message", message);
        // Kiểm tra subject_id và course_id trước khi redirect
        if (subject_id != null && course_id != null) {
            response.sendRedirect("viewquizresult?subject_id=" + subject_id + "&course_id=" + course_id + "&learner_id=" + learner_id);
        } else {
            response.sendRedirect("errorPage.jsp"); // Chuyển đến trang lỗi nếu thiếu subject_id hoặc course_id
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Gọi doGet để xử lý tương tự cho POST và GET
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


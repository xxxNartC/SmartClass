/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.auth;

import com.google.gson.Gson;
import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author slhoa
 */
@WebServlet(name="AddQuestionController", urlPatterns={"/addquestion"})
public class AddQuestionController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddQuestionController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddQuestionController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        QuestionDAO qd = new QuestionDAO();
        String content = request.getParameter("content");
        String op1 = request.getParameter("option1");
        String op2 = request.getParameter("option2");
        String op3 = request.getParameter("option3");
        String op4 = request.getParameter("option4");
        String ans = request.getParameter("ans");
        String sid = request.getParameter("sid");
        String chapid = request.getParameter("chapterid");
        String level = request.getParameter("level");

        System.out.println(content + " --- " + op1 + " --- " + ans + " --- " + sid + " --- " + chapid + " --- " + level + " --- ");

        qd.addQuestion(content, op1, op2, op3, op4, ans, sid, chapid, level);
        Gson gson = new Gson();
        String jsonData = gson.toJson("New question is added successfully");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(jsonData);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

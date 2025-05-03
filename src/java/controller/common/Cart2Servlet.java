/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Cart;
import model.Item;
import model.Subject;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Cart2Servlet", urlPatterns = {"/cart2"})
public class Cart2Servlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cart2Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Cart2Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        SubjectDAO d = new SubjectDAO();
        List<Subject> list = d.getAllSubject();
        Cookie[] arr = request.getCookies();
        String txt ="";
        if(arr!=null){
            for(Cookie o:arr){
                if(o.getName().equals("cart")){
                    txt+=o.getValue();
                }
            }
        }
        Cart c = new Cart(txt, list);
        List<Item> listItem = c.getItems();
        int n;
        if(listItem!= null){
            n=listItem.size();
        }else{
            n=0;
        }
        int total = 0;
        for (Item item : listItem) {
            double price = item.getPrice(); // Lấy giá gốc của sản phẩm
            String discountStr = item.getSubject().getDiscount(); // Lấy discount từ Subject

            // Kiểm tra discount có hợp lệ và chuyển thành số
            double discount = (discountStr != null && !discountStr.isEmpty()) 
                              ? Double.parseDouble(discountStr) 
                              : 0;

            // Tính giá sau khi áp dụng chiết khấu
            double discountedPrice = price - (price * discount / 100);

            // Cộng giá đã chiết khấu vào tổng
            total += discountedPrice;
        }
        request.setAttribute("size", n);
        request.setAttribute("listSubject", c);
        request.setAttribute("totalMoney", total);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

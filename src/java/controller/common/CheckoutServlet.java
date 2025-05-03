/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import dao.Config;
import dao.OrderDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.Orders;
import model.Subject;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

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
        SubjectDAO d = new SubjectDAO();
        OrderDAO order = new OrderDAO();
        List<Subject> list = d.getAllSubject();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, list);
        HttpSession session = request.getSession();
        User a = (User) session.getAttribute("user");
        if (a == null) {
            response.sendRedirect("login.jsp");
        } else {
            try {
//                String payment_method = request.getParameter("payment-method");
//                String address = request.getParameter("diachi");
//                String phone = request.getParameter("SDT");
//                String name = request.getParameter("name");
                String payment = "pending";
                
                
                order.addOrder(a, cart, payment);
                Cookie c = new Cookie("cart", "");
                c.setMaxAge(0);
                response.addCookie(c);
                
                   
                    Orders orders = order.getOrder();
                    String vnp_Version = "2.1.0";
                    String vnp_Command = "pay";
                    String vnp_OrderInfo = "Thanh toan don hang" + orders.getOrderId();
                    String orderType = "billpayment";

                    String vnp_TxnRef = orders.getOrderId() + "";
                    String vnp_IpAddr = Config.getIpAddress(request);
                    String vnp_TmnCode = Config.vnp_TmnCode;

                    int amount = Math.round(orders.getTotalMoney())*100;
                    System.out.println(amount);
                    Map<String, String> vnp_Params = new HashMap<>();
                    vnp_Params.put("vnp_Version", vnp_Version);
                    vnp_Params.put("vnp_Command", vnp_Command);
                    vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                    vnp_Params.put("vnp_Amount", String.valueOf(amount));
                    vnp_Params.put("vnp_CurrCode", "VND");
                    vnp_Params.put("vnp_BankCode", "NCB");

                    vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                    vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
                    vnp_Params.put("vnp_OrderType", orderType);

                    String locate = "vn";
                    if (locate != null && !locate.isEmpty()) {
                        vnp_Params.put("vnp_Locale", locate);
                    } else {
                        vnp_Params.put("vnp_Locale", "vn");
                    }

                    vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
                    vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

                    Date dt = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                    String dateString = formatter.format(dt);
                    String vnp_CreateDate = dateString;
                    
                    vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

                    List fieldNames = new ArrayList(vnp_Params.keySet());
                    Collections.sort(fieldNames);
                    StringBuilder hashData = new StringBuilder();
                    StringBuilder query = new StringBuilder();
                    Iterator itr = fieldNames.iterator();
                    while (itr.hasNext()) {
                        String fieldName = (String) itr.next();
                        String fieldValue = (String) vnp_Params.get(fieldName);
                        if ((fieldValue != null) && (fieldValue.length() > 0)) {
                            //Build hash data
                            hashData.append(fieldName);
                            hashData.append('=');
                            hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                            //Build query
                            query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                            query.append('=');
                            query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                            if (itr.hasNext()) {
                                query.append('&');
                                hashData.append('&');
                            }
                        }
                    }
                    String queryUrl = query.toString();
                    String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
                    queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
                    String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
                    request.setAttribute("code", "00");
                    request.setAttribute("message", "success");
                    request.setAttribute("data", paymentUrl);
                    response.sendRedirect(paymentUrl);
                    
             
            } catch (ParseException ex) {

            }
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
        processRequest(request, response);
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

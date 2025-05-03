package controller.common;

import dao.SubjectDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Subject;

@WebServlet(name = "BuyServlet", urlPatterns = {"/buy"})
public class BuyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO subjectDAO = new SubjectDAO();
        Cookie[] cookies = request.getCookies();
        String cartData = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cartData += cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        String id = request.getParameter("id");
        if (cartData.isEmpty()) {
            cartData = id + ":1";
        } else {
            cartData = cartData + "/" + id + ":1";
        }

        Cookie cartCookie = new Cookie("cart", cartData);
        cartCookie.setMaxAge(2 * 24 * 60 * 60);
        response.addCookie(cartCookie);

        // Set action and subject_id as attributes before forwarding
        request.setAttribute("action", "view");
        request.setAttribute("subject_id", id);
        request.getRequestDispatcher("infor").forward(request, response);
    }
}

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="dao.UserDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body {
                font-family: sans-serif;
            }
            h1 {
                color: #337ab7;
                text-align: center;
            }
            p {
                text-align: center;
                margin: 10px 0;
            }
            a {
                color: #337ab7;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
            }
            .container {
                width: 300px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                text-align: center;
            }
            p.error {
                color: red;
            }
        </style>
    </head>
    <body>
        <h1>Welcome</h1>
        <%
            User user = (User) session.getAttribute("user");
            if (user != null) {
                out.println("<p>Welcome, " + user.getFullname() + "!</p>");
                out.println("<p>You are logged in as a Learner.</p>");
                UserDAO dao = new UserDAO();      
                if (user.getRole_id() == 2) {       
                    out.println("<p><a href='learner?action=requestLecturer&requestedRole=3'>Request to be a Lecturer</a></p>");           
                    out.println("<p><a href='learner?action=requestMarketer&requestedRole=4'>Request to be a Marketer</a></p>");            
            }
            } else {
                response.sendRedirect("login.jsp");
            }
        %>
        <%       
            String message = (String) request.getAttribute("message");     
            if (message != null) {         
            out.println("<p style='color: red;'>"+message+"</p>");      
            }    
        %>
    </body>
</html>




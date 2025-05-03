<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="dao.UserDAO"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
     
    <head>
        
        <style>        
            body {
            font-family: sans-serif;
        }
        h1, h2 {
            color: #337ab7; /* Blue color */
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #337ab7; /* Blue color */
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        form {
            display: inline-block;
        }
        input[type="submit"] {
            background-color: #337ab7; /* Blue color */
            color: white;
            padding: 8px 16px;
            border: none;
            cursor: pointer;
        }    
        a.button {
            background-color: #007BFF; /* Blue color */
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }
        a.button:hover {
            background-color: #0056b3;
        }
        </style>
    </head>
    <body>
     
        <%
            User user = (User) session.getAttribute("user");
            if (user != null && user.getRole_id() == 1) {  // Kiểm tra xem người dùng có phải là admin không
                UserDAO dao = new UserDAO();
                ArrayList<User> allUsers = dao.getAllUsers();

                // Hiển thị danh sách yêu cầu thay đổi role
                if (allUsers != null && !allUsers.isEmpty()) {
                    out.println("<h2>Welcome, " + user.getFullname() + "!</h2>");
                    out.println("<a href='logout' class='button'>Logout</a>");

                    // Thêm nút Order History
                    out.println("<a href='OrderHistoryAdmin.jsp' class='button' style='margin-left: 20px;'>Order History</a>");
                    
                    out.println("<a href='manageReports' class='button' style='margin-left: 20px;'>Manage Account Reports</a>");

                    out.println("<table border='1'>");
                    out.println("<br></br><tr><th>Account ID</th><th>Username</th><th>Full Name</th><th>Role</th><th>Edit</th></tr>");
                
                    for (User u : allUsers) {
                      if (u.getRole_id() != 1) {
                        out.println("<tr>");
                        out.println("<td>" + u.getAccount_id() + "</td>");
                        out.println("<td>" + u.getUsername() + "</td>");
                        out.println("<td>" + u.getFullname() + "</td>");
                        out.println("<td>" + (u.getRole_id() == 1 ? "Admin" : 
                            (u.getRole_id() == 2 ? "Learner" : 
                            (u.getRole_id() == 3 ? "Lecturer" : 
                            (u.getRole_id() == 4 ? "Marketer" : "Unknown Role")))) + "</td>");
                            
                    
                        // Thêm form chỉnh sửa và submit thay đổi vai trò
                        out.println("<td>");
                        out.println("<form method='post' action='admin'>");
                        out.println("<input type='hidden' name='action' value='editRoleChange'>");
                        out.println("<input type='hidden' name='account_id' value='" + u.getAccount_id() + "'>");
                        out.println("<select name='new_role_id'>");
                        out.println("<option value='3'" + (u.getRole_id() == 3 ? " selected" : "") + ">Lecturer</option>");
                        out.println("<option value='4'" + (u.getRole_id() == 4 ? " selected" : "") + ">Marketer</option>");
                        out.println("</select>");
                        out.println("<input type='submit' value='Update Role'>");
                        out.println("</form>");
                        out.println("</td>");
                        out.println("</tr>");
                      }
                    }
                    out.println("</table>");
                }
                
            } else {
                response.sendRedirect("login.jsp");
            }
        %>
    </body>
</html>



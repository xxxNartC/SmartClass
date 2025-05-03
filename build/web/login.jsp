<%-- 
    Document   : login

--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>    

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   

        <title>login</title>    
        <style>
            body {
                font-family: sans-serif;
            }
            h1 {
                color: #337ab7;
                text-align: center;
            }
            form {
                width: 300px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            label {
                display: block;
                margin-bottom: 5px;
            }
            input[type="text"],
            input[type="password"],
            input[type="email"],
            input[type="date"],
            input[type="tel"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
                box-sizing: border-box;
            }
            input[type="submit"] {
                background-color: #337ab7;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #21618c; /* Darker blue on hover */
            }
            a {
                color: #337ab7;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
            }
            p {
                margin-top: 15px;
                text-align: center;
            }
        </style>

    </head>
    <body>
        <h1>Login</h1>
        <form action="login" method="post">
            <label for="usernameOrEmail">Username or Email:</label>     
            <input type="text" id="usernameOrEmail" name="usernameOrEmail" value="<%= request.getAttribute("usernameOrEmail") != null ? request.getAttribute("usernameOrEmail") : "" %>" required><br><br>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required><br><br>
            <input type="submit" value="Login">
        </form>
        <p><a href="forgetpass/ForgetPassword.jsp">Forget Password</a></p>  
        <p>Don't have an account? <a href="register.jsp">Register</a></p>   
        <%     
            String error = (String) request.getAttribute("error");      
            if (error != null) {       
            out.println("<p style='color: red;'>"+error+"</p>");     
            }     
        %>
    </body>
</html>


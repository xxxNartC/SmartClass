<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
        <title>Register</title>    
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
            p.error {
                color: red;
            }
            .center-text {        
                text-align: center;       
            }
            
        </style>  
    </head>
    <body>
        <h1>Register</h1>
        <form action="register" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>" required><br><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br><br>

            <label for="fullname">Full Name:</label>
            <input type="text" id="fullname" name="fullname" value="<%= request.getAttribute("fullname") != null ? request.getAttribute("fullname") : "" %>" required><br><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required><br><br>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" value="<%= request.getAttribute("dob") != null ? request.getAttribute("dob") : "" %>" required><br><br>

            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone" value="<%= request.getAttribute("phone") != null ? request.getAttribute("phone") : "" %>" required><br><br>

            <input type="submit" value="Register">
        </form>
        <p>Already have an account? <a href="login.jsp">Login</a></p>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
                out.println("<p style='color: red;' class='center-text'>"+error+"</p>");
            }
        %>
    </body>
</html>

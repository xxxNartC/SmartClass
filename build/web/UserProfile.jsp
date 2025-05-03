<%-- 
    Document   : UserProfile
    Created on : 13 thg 10, 2024, 10:47:22
    Author     : bacht
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%
    // Get the user from the session
    User user = (User) session.getAttribute("user");

    // If the user is not logged in, redirect to login page
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Get any success or error messages for the profile update
    String successMessage = request.getParameter("success");
    String errorMessage = request.getParameter("error");

    // Get the password success message if it exists
    String passwordSuccess = request.getParameter("passwordSuccess");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Profile</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .profile-container {
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                width: 70%;
                margin: auto;
                border: 1px solid #000;
                padding: 20px;
            }
            .profile-left {
                width: 30%;
                text-align: center;
            }
            .profile-right {
                width: 65%;
            }
            .profile-right table {
                width: 100%;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: left;
                vertical-align: top;
            }
            th {
                background-color: #f2f2f2;
            }
            .edit-icon {
                cursor: pointer;
                color: blue;
                margin-left: 10px;
            }
            input[type="text"], input[type="email"], input[type="tel"], input[type="date"] {
                width: 80%;
                padding: 5px;
                border: 1px solid #ccc;
            }
            input[readonly] {
                background-color: #f9f9f9;
            }
            .save-btn {
                display: none;
                margin-top: 10px;
                padding: 5px 10px;
                background-color: green;
                color: white;
                border: none;
                cursor: pointer;
            }
            .success-message {
                color: green;
                font-size: 14px;
            }
            .error-message {
                color: red;
                font-size: 14px;
            }
            .edit-icons {
                position: absolute;
                top: 5px;
                right: 5px;
                cursor: pointer;
                color: blue;
                background-color: white;
                border-radius: 50%;
                padding: 2px;
                font-size: 16px;
            }

            .save-btn {
                display: none;
                margin-top: 10px;
                padding: 5px 10px;
                background-color: green;
                color: white;
                border: none;
                cursor: pointer;
            }
            .avatar-container {
                position: relative;
                border-radius: 50%;
                border: 1px solid #000;
                width: 150px;
                height: 150px;
                margin: auto;
            }

            .avatar-image {
                width: 100%;
                height: 100%;
                border-radius: 50%;
            }
            .order-history-btn {
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                cursor: pointer;
                text-decoration: none;
                display: inline-block;
            }
            .order-history-btn:hover {
                background-color: #0056b3;
            }
        </style>
        <script>
            // Enable edit mode for input fields except password
            function enableEdit(field) {
                var inputField = document.getElementById(field);
                inputField.removeAttribute('readonly');
                inputField.style.border = '1px solid #000';
                document.getElementById('save-btn').style.display = 'inline'; // Hiện nút Save
            }

            // Redirect to change password page
            function redirectToChangePassword() {
                window.location.href = '<%= request.getContextPath() %>/ChangePassword.jsp';
            }

// Submit the form
            function validateAndSubmit() {
                document.getElementById('userForm').submit();
            }

            // Redirect to change password page
            function redirectToChangePassword() {
                window.location.href = '<%= request.getContextPath() %>/ChangePassword.jsp';
            }


            // Show password success message if available
            function showPasswordSuccessMessage() {
                var passwordSuccess = "<%= passwordSuccess %>";
                if (passwordSuccess === "true") {
                    document.getElementById('password-success-message').style.display = 'inline';
                }
            }

            // Execute the function on page load
            window.onload = showPasswordSuccessMessage;
        </script>
        <script>
            // Function to handle avatar file upload
            function handleFileUpload(event) {
                const input = event.target;
                if (input.files.length > 0) {
                    const formData = new FormData();
                    formData.append('avatar', input.files[0]);
                    formData.append('action', 'uploadTempAvatar');

                    fetch('<%= request.getContextPath() %>/userProfile', {
                        method: 'POST',
                        body: formData
                    })
                            .then(response => response.json())
                            .then(data => {
                                if (data.success) {
                                    // Show new avatar instantly
                                    document.getElementById('avatarImage').src = 'userProfile?action=displayTempAvatar&ts=' + new Date().getTime();
                                    document.getElementById('save-avatar-btn').style.display = 'inline'; // Show Save button for avatar
                                } else {
                                    alert('Error uploading avatar');
                                }
                            })
                            .catch(error => console.error('Error:', error));
                }
            }

            // Function to save avatar to the database
            function saveAvatar() {
                const formData = new FormData();
                formData.append('action', 'saveAvatar');

                fetch('<%= request.getContextPath() %>/userProfile', {
                    method: 'POST',
                    body: formData
                })
                        .then(response => response.json())
                        .then(data => {
                            if (data.success) {
                                alert('Avatar updated successfully!');
                                document.getElementById('save-avatar-btn').style.display = 'none'; // Hide Save button after save
                            } else {
                                alert('Error saving avatar');
                            }
                        })
                        .catch(error => console.error('Error:', error));
            }
        </script>

    </head>
    <body>
        <jsp:include page="header.jsp" /> <!-- Include the navigation/header -->

        <div class="profile-container">
            <div class="profile-left">
                <h3>User Profile</h3>
                <div class="avatar-container">
                    <img id="avatarImage" src="userProfile?action=displayAvatar&ts=<%= System.currentTimeMillis() %>" alt="Avatar" class="avatar-image" />
                    <input type="file" id="avatar" name="avatar" style="display: none;" accept="image/*" onchange="handleFileUpload(event)" />
                    <span class="edit-icons" onclick="document.getElementById('avatar').click()">✏️</span>
                </div>
                <button type="button" id="save-avatar-btn" class="save-btn" onclick="saveAvatar()" style="display: none;">Save Avatar</button>
                <p><%= user.getFullname() %></p>
            </div>


            <div class="profile-right">
                <!-- Display success or error messages -->
                <%
                    if ("true".equals(successMessage)) {
                        out.println("<p class='success-message'>Profile updated successfully!</p>");
                    } else if ("true".equals(errorMessage)) {
                        out.println("<p class='error-message'>Failed to update profile. Please try again.</p>");
                    }
                %>
                <form id="userForm" action="<%= request.getContextPath() %>/saveUserProfile" method="post">
                    <!-- Hidden input to store the avatar URL -->
                    <input type="hidden" id="avatarUrl" name="avatarUrl" value="<%= user.getAvatar() %>">

                    <table>
                        <tr>
                            <td>User name:</td>
                            <td><input type="text" id="username" name="username" value="<%= user.getUsername() %>" readonly /></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><input type="email" id="email" name="email" value="<%= user.getEmail() %>" readonly /></td>
                        </tr>
                        <tr>
                            <td>Phone number:</td>
                            <td><input type="tel" id="phone" name="phone" value="<%= user.getPhone() %>" readonly />
                                <span class="edit-icon" onclick="enableEdit('phone')">✏️</span></td>
                        </tr>
                        <tr>
                            <td>Date of birth:</td>
                            <td><input type="date" id="dob" name="dob" value="<%= user.getDob() %>" readonly />
                                <span class="edit-icon" onclick="enableEdit('dob')">✏️</span></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type="password" id="password" name="password" value="********" readonly />
                                <span class="edit-icon" onclick="redirectToChangePassword()">✏️</span>
                                <span class="success-message" id="password-success-message" style="display: none;">Password changed successfully!</span>
                            </td>
                        </tr>
                    </table>
                    <button type="button" id="save-btn" class="save-btn" onclick="validateAndSubmit()" style="display: none;">Save</button>
                </form>

                <!-- Link to Order History -->
                <a href="<%= request.getContextPath() %>/orderHistory" class="order-history-btn">Order History</a>

            </div>
        </div>
    </body>
</html>
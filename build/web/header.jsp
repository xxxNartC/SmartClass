

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">  
        <title>eLEARNING - eLearning HTML Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">  

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="img/favicon.ico" rel="icon">
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
            <a href="index.jsp" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>Pet Homies</h2>
            </a>
            <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto p-4 p-lg-0">
                    <a href="index.jsp" class="nav-item nav-link active">Home</a>
                    <a href='blog' class="nav-item nav-link">Blog</a>


                    <a href='GeminiChatbot' class="nav-item nav-link">Chat bot</a>

                  <%
                     Integer roleId = (Integer) session.getAttribute("role_id");
                     if (roleId != null && roleId == 3) {
                    %>
                    <a href="subjectStudents" class="nav-item nav-link">Student List</a>
                    <%
                                            } else if (roleId != null && roleId == 1) {
                    %>
                    <a class="nav-item nav-link" href="manageRoleRequests">Role Change Requests</a>
                    <a href='manageReports' class='nav-item nav-link'>Reports Comment</a>
                    <a href='ordersManagement' class="nav-item nav-link">Orders Manage</a>
                    <%
                        } else if (roleId != null && roleId == 2) {
                    %>
                    <a href='my_accomplish' class="nav-item nav-link">Accomplish</a>
                    <a class="nav-item nav-link" href="requestRoleChange.jsp">Join with us</a>
                    <a href="mysubjectlist" class="nav-item nav-link">My Subject</a>
                    <%
                    }
                    %>
                    <% if (session.getAttribute("user") != null && session.getAttribute("role_id") != null && (Integer) session.getAttribute("role_id") != 1) { %>
                    <a href="report" class="nav-item nav-link">Report</a>
                    <% } %>


                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Subject</a>
                        <div class="dropdown-menu fade-down m-0">
                            <a href='FavoriteSubject' class="dropdown-item">Favorite Subjects</a>
                            <a href="lecturerList" class="dropdown-item">Lecture List</a>
                            <%
                                    if (roleId != null && roleId == 3) {
                            %>
                            <a href="subjectmanage" class="dropdown-item">Subject Manage</a>
                            <a href="subjectreview" class="dropdown-item">Subject Review</a>
                            <% } %>
                            <a href="subjectlist" class="dropdown-item">Subject List</a>

                        </div>
                    </div>

                    <!-- Biểu tượng giỏ hàng -->
                    <a href="/smartclass/cart2" class="nav-item nav-link cart-icon">
                        <i class="fa fa-shopping-cart"></i>
                        <c:if test="${sessionScope.cart_size != null && sessionScope.cart_size > 0}">
                            <span class="cart-count">${sessionScope.cart_size}</span>
                        </c:if>
                    </a>


                </div>
                <% if (session.getAttribute("user") != null) { %>
                <a href="logout" class="nav-item nav-link">Logout</a>
                <a href="UserProfile.jsp" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">My Profile<i class="fa fa-user ms-3"></i></a>
                    <% } else { %>
                <a href="login.jsp" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Login<i class="fa fa-arrow-right ms-3"></i></a>
                    <% } %>

            </div>
        </nav>
    </body>
</html>

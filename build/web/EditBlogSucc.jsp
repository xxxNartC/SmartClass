<%-- 
    Document   : EditBlogSucc
    Created on : Oct 8, 2024, 5:58:09 PM
    Author     : TRUONG GIANG
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog Update</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
   <jsp:include page="header.jsp" />
        <!-- Blog Update Start -->
        <div class="container mt-5">
            <h1 class="text-center">Blog Update</h1>
            <div class="row mt-4 justify-content-center">
                <!-- Blog Update Form (Centered) -->
                <div class="col-md-8 mx-auto">
                    <!-- Display success message if available -->
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success" role="alert">
                            ${successMessage}
                        </div>
                    </c:if>
                    <div class="text-center">
                         <a href="editBlog" class="btn btn-primary">Continue Editing Blogs</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Blog Update End -->

        <!-- Footer Start -->
        <footer class="bg-light text-center text-lg-start mt-5">
            <div class="text-center p-3">
                © Eleaning
            </div>
        </footer>
        <!-- Footer End -->

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>


<%-- 
    Document   : blogUpdate
    Created on : Oct 1, 2024, 11:54:48 PM
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
        <c:if test="${sessionScope.user.role_id == '1' || sessionScope.user.role_id == '4'}">
            <!-- Navbar Start -->
            <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
                <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                    <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>eLEARNING</h2>
                </a>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto p-4 p-lg-0">
                        <a href="index.html" class="nav-item nav-link">Home</a>
                        <a href="about.html" class="nav-item nav-link">About</a>
                        <a href="courses.html" class="nav-item nav-link">Courses</a>
                        <a href="<%= request.getContextPath() %>/blog?action=list" class="nav-item nav-link active">Blog List</a>
                        <a href="contact.html" class="nav-item nav-link">Contact</a>
                    </div>
                </div>
            </nav>
            <!-- Navbar End -->

            <!-- Blog Update Start -->
            <div class="container mt-5">
                <h1 class="text-center">Blog Update</h1>
                <div class="row mt-4 justify-content-center">
                    <!-- Blog Update Form (Centered) -->
                    <div class="col-md-8 mx-auto">
                        <div class="col-md-12 text-end mb-3">
                            <a href="<%= request.getContextPath() %>/blog?action=add" class="btn btn-primary">Add Blog</a>
                        </div>
                        <!-- Display success message if available -->
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success" role="alert">
                                ${successMessage}
                            </div>
                        </c:if>
                        <form action="<%= request.getContextPath() %>/editBlog?action=update&page=${currentPage}" method="POST">
                            <input type="hidden" name="id" value="${blog.id}">
                            <div class="form-group">
                                <label for="title">Title:</label>
                                <input type="text" class="form-control" id="title" name="title" value="${blog.title}" required>
                            </div>
                            <div class="form-group">
                                <label for="content">Content:</label>
                                <textarea class="form-control" id="content" name="content" rows="5" required>${blog.content}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="description">Description:</label>
                                <textarea class="form-control" id="description" name="description" rows="3" required>${blog.description}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="image">Image URL:</label>
                                <input type="text" class="form-control" id="image" name="image" value="${blog.image}" required>
                            </div>
                            <div class="form-group">
                                <label for="tag">Tag:</label>
                                <input type="text" class="form-control" id="tag" name="tag" value="${blog.tag}" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Update Blog</button>
                        </form>
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
        </c:if>
        <c:if test="${sessionScope.user.role_id != '1' && sessionScope.user.role_id != '4'}">    
            <div class="container">    
                <h1>You are not authorized to access this page.</h1>       
                <a href="index.html"><input class="btn" type="submit" value="Back to Home"/></a>  
            </div>  
        </c:if>
    </body>
</html>

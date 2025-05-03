<%-- 
    Document   : EditBlog
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
        <title>Blog List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
     <jsp:include page="header.jsp" />
    <body>
        <c:if test="${sessionScope.user.role_id == '1' || sessionScope.user.role_id == '4'}">
            

            <!-- Blog List Start -->
            <div class="container mt-5">
                <h1 class="text-center">Blog List</h1>
                <div class="row mt-4 justify-content-center">
                    <!-- Blog List by ID (Centered) -->
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
                        <!-- Iterate through the blog list and display each blog in ascending order of ID -->
                        <c:forEach var="blog" items="${blogList}">
                            <div class="col-md-12 mb-4">
                                <div class="card">
                                    <img src="img/${blog.image}" class="card-img-top" alt="Blog Image">
                                    <div class="card-body">
                                        <h5 class="card-title">Blog ID: ${blog.id} - ${blog.title}</h5>
                                        <p class="card-text">${blog.description}</p>
                                        <a href="<%= request.getContextPath() %>/editBlog?action=edit&id=${blog.id}&page=${currentPage}" class="btn btn-success">Update</a>
                                        <a href="<%= request.getContextPath() %>/editBlog?action=delete&id=${blog.id}&page=${currentPage}" class="btn btn-danger">Delete</a>
                                        <a href="<%= request.getContextPath() %>/blog?action=detail&id=${blog.id}" class="btn btn-primary">Read More</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <!-- Pagination -->
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                                <c:if test="${currentPage > 1}">
                                    <li class="page-item"><a class="page-link" href="<%= request.getContextPath() %>/editBlog?action=listEdit&page=${currentPage - 1}">Previous</a></li>
                                </c:if>
                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <li class="page-item <c:if test="${currentPage == i}">active</c:if>"><a class="page-link" href="<%= request.getContextPath() %>/editBlog?action=listEdit&page=${i}">${i}</a></li>
                                </c:forEach>
                                <c:if test="${currentPage < totalPages}">
                                    <li class="page-item"><a class="page-link" href="<%= request.getContextPath() %>/editBlog?action=listEdit&page=${currentPage + 1}">Next</a></li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <!-- Blog List End -->

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

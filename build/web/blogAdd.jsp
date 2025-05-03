<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Blog</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
 <jsp:include page="header.jsp" />
<body>
    
    <!-- Blog Add Form Start -->
    <div class="container mt-5">
        <h1 class="text-center">Add New Blog</h1>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                ${successMessage}
            </div>
        </c:if>
        <form action="<%= request.getContextPath() %>/blog" method="POST">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="content">Content:</label>
                <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
            </div>
            <div class="form-group">
                <label for="image">File Image (Enter only filename.jpg):</label> <span class="text-danger"> *Please enter only filename.jpg</span>
                <input type="text" class="form-control" id="image" name="image" required>
            </div>
            <div class="form-group">
                <label for="tag">Tags (comma-separated):</label>
                <input type="text" class="form-control" id="tag" name="tag" required>
            </div>
            <input type="hidden" name="action" value="add">
            <button type="submit" class="btn btn-primary">Add Blog</button>
        </form>
    </div>
    <!-- Blog Add Form End -->

    <!-- Footer Start -->
    <footer class="bg-light text-center text-lg-start mt-5">
        <div class="text-center p-3">
            © 2024 Elearning
        </div>
    </footer>
    <!-- Footer End -->

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

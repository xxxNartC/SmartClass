<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
 <jsp:include page="header.jsp" />
<body>

    <!-- Navbar End -->

    <!-- Blog Detail Start -->
    <div class="container mt-5">
        <h1 class="text-center">${blog.title}</h1>
        <div class="row mt-4">
            <div class="col-md-12">
                 <img src="img/${blog.image}" class="card-img-top img-fluid" alt="Blog Image" style="max-height: 500px; object-fit: cover;">

                <p class="mt-4">${blog.content}</p>
                <p class="mt-4">Marketer: ${blog.marketerName}</p> <%-- Display the marketer name --%>
            </div>
        </div>
    </div>
    <!-- Blog Detail End -->

    <!-- Footer Start -->
    <footer class="bg-light text-center text-lg-start mt-5">
        <div class="text-center p-3">
            © Elearning
        </div>
    </footer>
    <!-- Footer End -->

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


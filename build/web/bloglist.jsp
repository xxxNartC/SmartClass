<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">
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

    <!-- Custom CSS -->
    <style>
        /* Custom styles for the Search input */
        #filterInput {
            border: 2px solid #007bff;
            border-radius: 5px;
            padding: 10px;
            transition: all 0.3s ease-in-out;
        }

        #filterInput:focus {
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        /* Custom styles for the Tag filter select */
        #selectedTag {
            border: 2px solid #28a745;
            border-radius: 5px;
            padding: 10px;
            transition: all 0.3s ease-in-out;
        }

        #selectedTag:focus {
            outline: none;
            box-shadow: 0 0 5px rgba(40, 167, 69, 0.5);
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />

    <!-- Blog List Start -->
    <div class="container mt-5">
        <h1 class="text-center">Blog List</h1>
        <div class="row mt-4 justify-content-center">
            <!-- Blog List by ID (Centered) -->
            <div class="col-md-8 mx-auto">
                <c:if test="${sessionScope.user.role_id == '1' || sessionScope.user.role_id == '4'}">
                    <div class="col-md-12 text-end mb-3">
                        <a href="<%= request.getContextPath() %>/blog?action=add" class="btn btn-primary">Add Blog</a>
                        <a href="editBlog" class="nav-item nav-link">Edit Blog</a>
                    </div>
                </c:if>
                <!-- Search Bar -->
                <div class="input-group mb-3">
                    <form action="<%= request.getContextPath() %>/blog" method="get">
                        <input type="hidden" name="action" value="list">
                        <input type="text" class="form-control" placeholder="Search by title or tag" name="searchText" value="${searchText}" id="filterInput">
                        <button class="btn btn-outline-secondary" type="submit">Search</button>
                    </form>
                </div>
                <!-- Tag Filter -->
                <div class="mb-3">
                    <label for="selectedTag" class="form-label">Filter by Tag:</label>
                    <select class="form-select" id="selectedTag" name="selectedTag" onchange="filterByTag()">
                        <option value="">All Tags</option>
                        <c:forEach var="tag" items="${allTags}">
                            <option value="${tag}" <c:if test="${selectedTag == tag}">selected</c:if>>${tag}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- Iterate through the blog list and display each blog -->
                <div class="row" id="blogList">
                    <c:forEach var="blog" items="${blogList}">
                        <div class="col-md-12 mb-4">
                            <div class="card">
                                <img src="img/${blog.image}" class="card-img-top" alt="Blog Image">
                                <div class="card-body">
                                    <h5 class="card-title">Blog ID: ${blog.id} - ${blog.title}</h5>
                                    <p class="card-text">${blog.description}</p>
                                    <p class="card-text">Tag: ${blog.tag}</p> <%-- Display the tag --%>
                                    <p class="card-text">Date: ${blog.formattedCreatedDate}</p> <%-- Display the formatted date --%>
                                    <p class="card-text">Marketer: ${blog.marketerName}</p> <%-- Display the marketer name --%>
                                    <a href="<%= request.getContextPath() %>/blog?action=detail&id=${blog.id}" class="btn btn-primary">Read More</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!-- Pagination -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="<%= request.getContextPath() %>/blog?action=list&page=${currentPage - 1}&searchText=${searchText}&selectedTag=${selectedTag}">Previous</a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item <c:if test="${currentPage == i}">active</c:if>">
                                <a class="page-link" href="<%= request.getContextPath() %>/blog?action=list&page=${i}&searchText=${searchText}&selectedTag=${selectedTag}">${i}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="<%= request.getContextPath() %>/blog?action=list&page=${currentPage + 1}&searchText=${searchText}&selectedTag=${selectedTag}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <!-- Blog List End -->
    <script>
        // Function to filter blog list based on search input
        function filterBlogList() {
            const filter = document.getElementById("filterInput").value.toUpperCase();
            const blogList = document.getElementById("blogList");
            const blogCards = blogList.getElementsByClassName("col-md-12");

            for (let i = 0; i < blogCards.length; i++) {
                const cardTitle = blogCards[i].querySelector(".card-title").textContent.toUpperCase();
                const cardDescription = blogCards[i].querySelector(".card-text").textContent.toUpperCase();

                if (cardTitle.indexOf(filter) > -1 || cardDescription.indexOf(filter) > -1) {
                    blogCards[i].style.display = "";
                } else {
                    blogCards[i].style.display = "none";
                }
            }
        }

        // Function to filter blog list based on selected tag
        function filterByTag() {
            const selectedTag = document.getElementById("selectedTag").value;
            const blogList = document.getElementById("blogList");
            const blogCards = blogList.getElementsByClassName("col-md-12");

            for (let i = 0; i < blogCards.length; i++) {
                const cardTag = blogCards[i].querySelector(".card-text:nth-of-type(2)").textContent.split(": ")[1]; // Lấy tag từ phần mô tả

                if (selectedTag === "" || cardTag === selectedTag) {
                    blogCards[i].style.display = "";
                } else {
                    blogCards[i].style.display = "none";
                }
            }
        }

        // Add event listener to the search input
        document.getElementById("filterInput").addEventListener("keyup", filterBlogList);

        // Call filterByTag when the page loads to apply the selected tag filter
        filterByTag();
    </script>

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


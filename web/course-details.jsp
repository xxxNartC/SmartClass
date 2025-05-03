<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject List</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

        <style>
            .card-container {
                display: grid;
                grid-template-columns: repeat(3, 1fr); /* 3 cột */
                gap: 20px;
                justify-content: center; /* Căn giữa container */
                margin: 20px auto; /* Căn giữa trang với khoảng cách đều hai bên */
                max-width: 1200px; /* Đặt chiều rộng tối đa cho container để không kéo dài quá nhiều */
            }

            /* Thiết kế cho từng card */
            .card {
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s;
                text-align: center; /* Căn giữa nội dung văn bản */
                display: flex; /* Sử dụng flex để căn giữa nội dung */
                flex-direction: column; /* Sắp xếp các phần tử theo chiều dọc */
                justify-content: space-between; /* Căn đều khoảng cách giữa các phần tử */
                height: 100%; /* Đảm bảo thẻ chiếm toàn bộ chiều cao */
            }

            /* Hiệu ứng hover cho card */
            .card:hover {
                transform: translateY(-5px);
            }

            .card img {
                width: 100%;
                height: 370px;
                object-fit: cover;
            }

            /* Phần nội dung trong card */
            .card-content {
                padding: 15px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            /* Tiêu đề của card */
            .card h3 {
                font-size: 18px;
                margin: 0 0 10px;
                color: #333;
            }

            /* Mô tả của card */
            .card p {
                font-size: 14px;
                color: #666;
                margin: 10px 0;
            }

            /* Giá của khóa học */
            .card .price {
                font-size: 16px;
                font-weight: bold;
                color: #ff5722;
                margin-top: 10px;
            }


            .pagination {
                display: flex; /* Sử dụng Flexbox để dễ dàng căn giữa */
                justify-content: center; /* Căn giữa các nút phân trang */
                margin-top: 20px;
            }

            .pagination a {
                display: inline-block;
                padding: 8px 16px;
                margin: 4px;
                background-color: #ddd;
                text-decoration: none;
                color: black;
                border-radius: 4px;
            }

            .pagination a:hover {
                background-color: #bbb;
            }

            h1 {

                text-align: center;
            }
            .search-container {
                display: flex; /* Đặt cả input và nút search trên cùng một hàng */
                justify-content: center; /* Căn giữa các phần tử trong container */
                gap: 10px; /* Khoảng cách giữa ô input và nút search */
                margin-bottom: 20px; /* Khoảng cách dưới của form */
            }

            /* Điều chỉnh chiều rộng của input */
            .search-container input {
                flex-grow: 1; /* Để input chiếm nhiều không gian nhất có thể */
                max-width: 300px; /* Giới hạn chiều rộng tối đa */
            }

            /* Điều chỉnh nút search */
            .search-container button {
                white-space: nowrap; /* Đảm bảo nút không bị xuống dòng */
            }

        </style>

    </head>
    <jsp:include page="header.jsp" />
    <body>
        <c:if test="${sessionScope.user.role_id == '2' || sessionScope.user.role_id == '3'}"> 

            <!--</div>-->

        </nav>
        <h1 style="margin-top: 30px; margin-bottom: 20px">Course Detail</h1>
        <!-- Overview -->
        <div class="courese-overview" id="overview">
            <h3 class="value black" style="color: blue;margin-bottom: 20px ; width: 100%; text-align: center">${course.getCourse_name()}</h3>
        </div>

        <!--content-->
        <section class="banner-area">
            <div class="container">
                <div class="row justify-content-center align-items-center">
                    <div class="col-lg-12 banner-right">
                        <h1 class="text-white">
                            Course Details
                        </h1>
                        <a href="course?subject_id=${subject_id}" style="float: right"><input class="btn btn-info" type="button" value="Back to Course List"/></a>
                    </div>
                </div>
            </div>
        </section>
        <div class="container black">
            <!-- Overview -->
            <div class="courese-overview" id="overview">
                <h4>Overview</h4>
            </div>
            <div class="row">
                <div class="col-xl-6 col-lg-6 col-md-12 inner-content">
                    <div class="course-details">
                        <div class="card-text mb-2 inner-wrap" >
                            <div class="inner-head"><i class="fa-solid fa-book li-button"></i> <span class="label">Subject : ${course.getSubject_name()}</span></div>
                        </div>
                        <div class="card-text mb-2 inner-wrap">
                            <div class="inner-head"><i class="ti-file li-button"></i> <span class="label">Course No : ${course.getCourse_no()}</span></div> 
                        </div>
                        <div class="card-text mb-2 inner-wrap">
                            <div class="inner-head"><i class="ti-files li-button"></i> <span class="label">Chapter : ${numberOfChapters}</span></div> 
                        </div>
                        <div class="card-text mb-2 inner-wrap">
                            <div class="inner-head"><i class="fa-solid fa-school li-button"></i> <span class="label">Online</span></div></div>
                    </div>


                    <c:if test="${hasAssignment == true}">
                        <h4>Assignment</h4>
                        <a href="takeAssignment?course_id=${course.getCourse_id()}&learner_id=${sessionScope.user.account_id}" class="blue-button">Take Assignment</a>
                    </c:if>
                    <h4>Quiz</h4>           
                    <a href="viewquizresult?course_id=${course.getCourse_id()}&learner_id=${sessionScope.user.account_id}" class="blue-button">View Quiz Results</a>        
                </div>
                <div class="col-xl-6 col-lg-6 col-md-12 courese-overview">
                    <h5 class="m-b5">💡 Course Description</h5>
                    <p style="font-size: 18px;margin-top: 10px">${course.description}</p>
                    <h5 class="m-b5" style="margin-bottom: 15px">✔ Learning Outcomes</h5>
                    <ul class="list-checked primary">
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Over 37 lectures and 55.5 hours of content!</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>LIVE PROJECT End to End Software Testing Training Included.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Learn Software Testing and Automation basics from a professional trainer from your own desk.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Information packed practical training starting from basics to advanced testing techniques.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Best suitable for beginners to advanced level users and who learn faster when demonstrated.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Course content designed by considering current software testing technology and the job market.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Practical assignments at the end of every session.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Practical learning experience with live project work and examples.cv</li>
                    </ul>
                    <c:if test="${not empty latestLearnerCourse}">
                        
                            <h5 class="m-b5" style="margin-bottom: 15px">✔ Course Feedback</h5>
                            <div class="card-text mb-2 inner-wrap">
                                <div class="inner-head"><i class="fa-solid fa-star li-button"></i> 
                                    <span class="label">Rating</span>
                                </div>
                                <span class="value">${latestLearnerCourse.getRate()}</span>
                            </div>
                            <div class="card-text mb-2 inner-wrap">
                                <div class="inner-head"><i class="fa-solid fa-comment li-button"></i> 
                                    <span class="label">Feedback</span>
                                </div>
                                <span class="value">${latestLearnerCourse.getFeedback()}</span>
                            </div>
                       
                    </c:if>
                </div>

            </div>
        </div>
        <!--curriculum -->
        <div class="container">
            <div class="m-b30 curriculum" id="curriculum">
                <h4>Chapters</h4>
                <c:forEach items="${chapters}" var="chapter">
                    <div class="card">
                        <div class="card-header" id="headingChapter_no">
                            <h2 class="mb-0">
                                <a href="lessonlist?subject_id=${subject_id}&course_id=${course.getCourse_id()}&chapter_id=${chapter.getChapter_id()}" class="btn btn-link btn-block text-left" id="chapterLink${chapter.getChapter_no()}" 
                                   data-toggle="collapse" data-target="#collapse$Chapter_no" aria-expanded="true" aria-controls="collapse$chapter.getChapter_no()">
                                    <h5 class="chapter">Chapter ${chapter.getChapter_no()} : <span class="value">${chapter.getChapter_name()}</span>
                                        <i class="fa-solid fa-caret-down"></i>
                                    </h5>
                                </a>
                            </h2>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!--End curriculum -->

        <div class=" container inner-wrap curriculum">
            <h4 class="mt-4">Related Course</h4>
            <div class="row inner-content">
                <c:forEach items="${relatedCourses}" var="course">
                    <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12">
                        <div class='inner-box'>
                            <div class="card">
                                <img src="img/coursera.png" class="card-img-top" alt="Image Course" style="height: 200px">
                                <div class="card-body">
                                    <div class="courese-overview">
                                        <h5 class="value" style="color: blue;margin-bottom: 20px ; width: 100%">${course.getCourse_name()}</h5>
                                        <div class="card-text mb-2 inner-wrap"> <div class="inner-head"><i class="fa-solid fa-audio-description"></i> <span class="label">Description</span></div><span class="value">${course.getDescription()}</span></div>
                                        <div class="card-text mb-2 inner-wrap"><div class="inner-head"><i class="fa-solid fa-book li-button"></i> <span class="label">Subject</span> </div><span class="value">${course.getSubject_name()}</span></div>
                                        <div class="card-text mb-2 inner-wrap"><div class="inner-head"><i class="fa-solid fa-user-graduate li-button"></i><span>Lecturer</span> </div><span class="value"></span></div>
                                        <div class="card-text mb-2 inner-wrap"><div class="inner-head"><i class="ti-file li-button"></i> <span class="label">Course No</span> </div><span class="value">${course.getCourse_no()}</span></div>
                                        <a href="course-details?course_id=${course.getCourse_id()}" class="inner-button"> <button class="btn btn-success">Join Now</button></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>


        <!--</div>-->
        <!--End Overview -->

        <!-- Phân trang -->
        <div class="pagination">
            <%--<c:forEach begin="1" end="${page}" var="i">--%>
                <!--<a href="subjectlist?index=${i}&search_name=${search_name}"> ${i} </a>-->
            <%--</c:forEach>--%>
        </div>



    </c:if>
    <c:if test="${sessionScope.user.role_id != '2' && sessionScope.user.role_id != '3'}">    
        <div class="container">    
            <h1>You are not authorized to access this page.</h1>       
            <a href="index.html"><input class="btn" type="submit" value="Back to Home"/></a>  
        </div>  
    </c:if>
</body>
</html>


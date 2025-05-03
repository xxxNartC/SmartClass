<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Lesson</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>
    <jsp:include page="header.jsp" />
    <body>
        <c:if test="${sessionScope.user.role_id == '3'}">
            <div class="container">
                <div class="card" style="margin-top: 30px; padding: 20px;">
                    <div class="card-body">
                        <h1 class="card-title">Add Lesson</h1>
                        <c:if test="${not empty mess}">
                            <small class="text-danger">${mess}</small>
                        </c:if>
                        <c:if test="${not empty error}">
                            <small class="text-danger">${error}</small>
                        </c:if>
                        <form action="addLesson" method="POST">
                            <input type="hidden" name="course_id" value="${course_id}">
                            <input type="hidden" name="subject_id" value="${subject_id}">
                            <input type="hidden" name="chapter_id" value="${chapter_id}">

                            <div class="form-group">
                                <label for="lesson_no">Lesson No:</label>
                                <input type="text" class="form-control" id="lesson_no" name="lesson_no" required>
                                <c:if test="${not empty lesson_no_error}">
                                    <small class="text-danger">${lesson_no_error}</small>
                                </c:if>
                            </div>

                            <div class="form-group">
                                <label for="lesson_name">Lesson Name:</label>
                                <input type="text" class="form-control" id="lesson_name" name="lesson_name" required>
                                <c:if test="${not empty lesson_name_error}">
                                    <small class="text-danger">${lesson_name_error}</small>
                                </c:if>
                            </div>

                            <div class="form-group">              
                                <label for="video">Video Link:</label>            
                                <input type="text" class="form-control" id="video" name="video" required>          
                                <c:if test="${not empty video_error}">                
                                    <small class="text-danger">${video_error}</small>           
                                </c:if>                             
                            </div>

                            <!-- Button Preview Video -->
                            <button type="button" class="btn btn-primary" onclick="previewVideo()">Preview Video</button>

                            <!-- Video Preview -->
                            <div id="video-preview" style="margin-top: 15px;">
                                <!-- Video will be embedded here -->
                            </div>

                            <div class="form-group">
                                <label for="description">Lesson Description:</label>
                                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                                <c:if test="${not empty description_error}">
                                    <small class="text-danger">${description_error}</small>
                                </c:if>
                            </div>

                            <button type="submit" class="btn btn-primary">Add Lesson</button>
                            <a href="manageLesson?course_id=${course_id}&subject_id=${subject_id}&chapter_id=${chapter_id}" class="btn btn-primary">Back to Lesson Manage</a>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.user.role_id != '3'}">    
            <div class="container">    
                <h1>You are not authorized to access this page.</h1>       
                <a href="index.html"><input class="btn" type="submit" value="Back to Home"/></a>  
            </div>  
        </c:if>

        <script>
            function previewVideo() {
                var videoLink = document.getElementById('video').value;
                var videoPreviewDiv = document.getElementById('video-preview');

                // Embed the video if a valid URL is provided
                if (videoLink) {
                    videoPreviewDiv.innerHTML = '<iframe width="560" height="315" src="' + videoLink + '" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>';
                } else {
                    videoPreviewDiv.innerHTML = '<p class="text-danger">Please provide a valid video link.</p>';
                }
            }
        </script>
    </body>

</html>


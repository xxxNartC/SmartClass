<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="header.jsp" />  
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bootstrap Form Example</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="ckeditor/ckeditor.js" type="text/javascript"></script>
    </head>
    <body>

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-body">
                            <!-- Form -->
                            <form id="markAsm" action="viewAssignmentSubmittedDetail" method="post">

                                <input type="hidden" name="submitted_id" value="${submitted_id}"/>
                                <input type="hidden" name="courseid" value="${courseid}"/>
                                <h2>ASSIGNMENT</h2>
                                <!--De bai-->
                                <!-- Content Display -->
                                <div class="form-group">
                                    <label for="content">Content:</label>
                                    <textarea readonly class="form-control" id="content" name="content" rows="3" readonly>
                                        ${asm.content}
                                    </textarea>
                                </div>
                                <!-- Download Button -->
                                <div class="form-group">
                                    <a href="/smartclass/downloadAsmDoc?docName=${asm.document}" class="btn btn-primary" >Download Document</a>
                                </div>
                                <hr/>
                                <h2>Student's Answer</h2>
                                <!--Submitted file word-->
                                <c:if test="${detail.document != ''}">
                                    <div class="form-group">
                                        <a href="/smartclass/downloadAsmDoc?isSubmitted=true&docName=${detail.document}" class="btn btn-primary" >
                                            Download Student's Answer File</a>
                                    </div>
                                </c:if>

                                <!-- Answer  -->
                                <div style="display: ${detail.answer != '' ? '' : 'none'}" 
                                     class="form-group">
                                    <label for="answer">Student Answer:</label>
                                    <textarea readonly class="form-control" id="answer" name="answer">
                                        ${detail.answer}
                                    </textarea>
                                </div>
                                <hr/>
                                <h2>Your response:</h2>
                                <!-- Lecturer's Comment -->
                                <div class="form-group">
                                    <label for="comment">Your Comment:</label>
                                    <textarea class="form-control" name="comment">${detail.comment}</textarea>
                                </div>
                                <!--Lecturer cham diem-->
                                <div class="form-group">
                                    <label for="mark">Mark</label>
                                    <input id="mark" class="form-control" name="mark" type="text"
                                           value="${detail.mark}" placeholder="0 -> 10"/>
                                </div>

                                <hr/>
                                <!-- Submit Button -->
                                <button onclick="return confirm('Do you want to submit?')"
                                        type="submit" class="btn btn-primary">Submit</button>
                            </form>
                            <!-- End Form -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap JS and dependencies (optional) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <!-- Your custom script -->
        <script>
            CKEDITOR.replace('answer');

            document.getElementById('markAsm').addEventListener('submit', function (event) {
                var markinput = parseFloat(document.getElementById('mark').value);
                console.log(markinput);
                if (isNaN(markinput) || markinput < 0 || markinput > 10) {
                    event.preventDefault();
                    alert('Mark must be from 0 to 10!');
                } else {
                    alert('Marked successfully!');
                }
            });

            // Trim the content textarea before display
            document.addEventListener('DOMContentLoaded', function() {
                var content = document.getElementById('content');
                content.value = content.value.trim();
            });
        </script>

    </body>
</html>

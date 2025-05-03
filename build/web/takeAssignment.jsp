

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="header.jsp" />
    <head>
       
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <h1 style="text-align: center;">Assignment</h1>
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
                            <form id="submitAsmForm" action="" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="courseid" value="${courseid}"/>
                                <input type="hidden" name="learnerid" value="${learnerid}"/>
                                <input type="hidden" name="assignmentid" value="${asm.assignment_id}"/>
                                
                                <!-- Content Display -->
                                <div class="form-group">
                                    <label for="content">Content:</label>
                                    <textarea class="form-control" id="content" name="content" rows="3" readonly>
                                        ${asm.content}
                                    </textarea>
                                </div>
                                <!-- Download Button -->
                                <c:if test="${asm.document != '' && asm.document != null}">
                                    <div class="form-group">
                                        <a href="/smartclass/downloadAsmDoc?docName=${asm.document}" class="btn btn-primary" id="downloadBtn" download>Download Document</a>
                                    </div>
                                </c:if>

                                <!-- Answer Input -->
                                <div class="form-group">
                                    <label for="answer">Your Answer:</label>
                                    <c:if test="${detail != null}">
                                        <textarea readonly class="form-control" id="answer" name="answer">
                                           ${detail.answer}
                                        </textarea>
                                    </c:if>
                                    <c:if test="${detail == null}">
                                        <textarea class="form-control" id="answer" name="answer"></textarea>
                                    </c:if>

                                </div>
                                <!-- File Upload Input -->
                                <c:if test="${detail == null}">
                                    <div class="form-group">
                                        <label for="document">Upload Document:</label>
                                        <input type="file" class="form-control-file" id="doc" name="document" accept=".doc,.docx">
                                    </div>
                                    <button onclick="return confirm('Do you want to submit. You cannot edit this assignment again !')"
                                            type="submit" class="btn btn-primary">Submit</button>
                                </c:if>

                                <!-- Submit Button -->

                            </form>
                            <!-- End Form -->
                        </div>
                    </div>
                </div>
                <c:if test="${detail != null}">
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <h1>Your Result</h1>
                                <c:if test="${detail.status == 'pending'}">
                                    <h3 style="color: orange;">Pending...</h3>
                                </c:if>
                                <c:if test="${detail.status == 'reject' || detail.status == 'passed'}">
                                    <h3 style="color: ${detail.status == 'reject' ? 'red' : 'green'};">
                                        ${detail.status == 'reject' ? 'REJECT' : 'PASSED'}
                                    </h3>
                                    <div class="form-group">
                                        <label for="document">Your mark</label>
                                        <input class="form-control" readonly="true" type="text" value="${detail.mark}"/>
                                        <label for="document">Note</label>
                                        <input class="form-control" readonly="true" type="text" value="${detail.comment}"/>
                                    </div>
                                </c:if>
                                
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies (optional) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <!-- Your custom script -->
        <script>
            CKEDITOR.replace('answer');

            document.getElementById('submitAsmForm').addEventListener('submit', function (event) {
                var ans = CKEDITOR.instances.answer.getData();
                var doc = document.getElementById('doc').files;
                if (ans.trim() === '' && doc.length === 0) {
                    event.preventDefault();
                    alert('You must answer or submit your answer document file!');
                } else {
                    alert('Submitted Successfully!');
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




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="header.jsp" />
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Assignment</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        
<c:if test="${sessionScope.user.role_id == '3'}">
        <div class="container">
        <div class="container mt-5">
            <h2>${detail != null ? 'Update' : 'Add'} Assignment</h2>
            <form id="addAsmForm" action="addAssignment" method="post" enctype="multipart/form-data">
                <input type="hidden" name="courseid" value="${courseid}"/>
                <input type="hidden" name="subjectid" value="${subjectid}"/>
                <input type="hidden" name="action" value="${detail != null ? 'update' : 'add'}"/>
                <!-- Content Input -->
                <div class="form-group">
                    <label for="content">Content:</label>
                    <textarea class="form-control" id="content" name="content" rows="3" placeholder="Enter text">
                        ${detail != null ? detail.content.trim() : ''}
                    </textarea>
                </div>
                <!-- Document Input -->
                <c:if test="${detail.document != '' && detail.document != null}">
                    <div class="form-group">
                      
                        <a href="/smartclass/downloadAsmDoc?docName=${detail.document}" class="btn btn-primary" id="downloadBtn">Download Current Document</a>
                    
                    </div>
                    
                </c:if>

                <div class="form-group">
                    <label for="document">${detail != null ? 'Change Document' : 'Add Document'}:</label>
                    <input type="file" class="form-control-file" id="document" name="document" accept=".doc,.docx">
                </div>
                <button type="submit" class="btn btn-primary">${detail != null ? 'Update' : 'Add'}</button>
            </form>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script type="text/javascript">
            document.getElementById('addAsmForm').addEventListener('submit', function (event) {
                var content = document.getElementById('content').value.trim(); // Trim the content to remove extra spaces
                document.getElementById('content').value = content; // Set the trimmed value back to the textarea
                var doc = document.getElementById('document').files;
                if (content === '' && doc.length === 0) {
                    event.preventDefault();
                    alert('You must input CONTENT or UPLOAD DOCUMENT !');
                }
            });

        </script>
</c:if>
    <c:if test="${sessionScope.user.role_id != '3'}">    
        <div class="container">    
            <h1>You are not authorized to access this page.</h1>       
            <a href="index.jsp"><input class="btn" type="submit" value="Back to Home"/></a>  
        </div>  
    </c:if>
    </body>
</html>



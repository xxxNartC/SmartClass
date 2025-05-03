<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Response Management</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="css/stylesresponsemanagement.css">
        <style>
            .message {
                color: green;
                font-size: 25px;
                margin-top: 5px;
            }
            .error {
                color: red;
                font-size: 12px;
                margin-top: 5px;
            }

        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="header">
            <button class="header-button"><a href="mysubject">Back</a></button>
        </div>
        <h1>${subject.getSubject_name()}</h1>
        <div class="container">
            <h3 class="message">${message}</h3>
            <div id="unrepliedComments" class="comments-section">
                <h2>Comments</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Learner</th>
                            <th>Comment</th>
                            <th>Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${datacommentLessonUnreplied}" var="comment">
                            <tr>
                                <td>${comment.getFullname()}</td>
                                <td>${comment.getComment()}</td>
                                <td>${comment.getCommentDate()}</td>
                                <td><button class="reply" onclick="replyComment(${comment.getCommentId()}, ${comment.getLessonId()}, ${subject_id})">Reply</button>
                                    <a href="reportComment?comment_id=${comment.getCommentId()}&subject_id=${subject_id}"><button>Report Comment</button></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${noOfPages > 1}">
                    <div class="pagination">
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <a href="responemanagement?subject_id=${subject_id}&pageUnRep=${i}" class="${pageUnRep == i ? 'active' : ''}">${i}</a>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
            <div id="repliedComments" class="comments-section hidden">
                <h2>Comments</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Learner</th>
                            <th>Comment</th>
                            <th>Date</th>
                            <th>Reply</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${datacommentLessonReplied}" var="comment">
                            <tr>
                                <td>${comment.getFullname()}</td>
                                <td>${comment.getComment()}</td>
                                <td>${comment.getCommentDate()}</td>
                                <td>${comment.getReply()}</td>
                                <td><button class="update" onclick="update(${comment.getCommentId()}, ${comment.getParentCommentId()},${subject_id})">Update Reply</button>
                                    <a href="deletecommentlesson?comment_id=${comment.getCommentId()}&parent_comment_id=${comment.getParentCommentId()}&subject_id=${subject_id}"><button class="delete">Delete</button></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
            </div>


            <form id="form" action="addcommentlesson" method="Post">
                <div id="replyBox" class="reply-box hidden">
                    <input type="hidden" name="comment_id" id="comment_id" value=""/>
                    <input type="hidden" name="lesson_id" id="lesson_id" value=""/>
                    <input type="hidden" name="subject_id" id="subject_id" value=""/>
                    <input type="hidden" name="parent_comment_id" id="parent_comment_id" value=""/>
                    <textarea placeholder="Enter your reply..." name="comment" id="comment"></textarea>
                    <div name="err" id="err" class="error"></div>
                    <button type="submit" class="send-reply" type="button" id="submit">Send</button>
                    <button class="cancel-reply" type="button" id="cancel">Cancel</button>
                </div>
            </form>
        </div>
        <script>

            function replyComment(comment_id, lesson_id, subject_id) {
                var replyBox = document.getElementById('replyBox');
                replyBox.classList.remove('hidden');
                let comment_id_input = document.querySelector("#comment_id");
                comment_id_input.value = comment_id;
                let lesson_id_input = document.querySelector("#lesson_id");
                lesson_id_input.value = lesson_id;
                let subject_id_input = document.querySelector("#subject_id");
                subject_id_input.value = subject_id;
                let comment = document.querySelector("#comment");
                comment.focus();
                console.log(comment_id_input);
                console.log(lesson_id_input);
                console.log(subject_id_input);
                // Disable other reply buttons
            }
            function update(comment_id, parent_comment_id, subject_id) {
                var replyBox = document.getElementById('replyBox');
                replyBox.classList.remove('hidden');
                let comment_id_input = document.querySelector("#comment_id");
                comment_id_input.value = comment_id;
                let parent_comment_id_input = document.querySelector("#parent_comment_id");
                parent_comment_id_input.value = parent_comment_id;
                let subject_id_input = document.querySelector("#subject_id");
                subject_id_input.value = subject_id;
                let form = document.querySelector("#form");
                form.action = "updatecommentlesson";
                let comment = document.querySelector("#comment");
                comment.focus();
                console.log(comment_id_input);
                console.log(parent_comment_id_input);
                console.log(form.action);
                // Disable other reply buttons
            }
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const deletes = document.querySelectorAll(".delete");
                console.log('Found deletes:', deletes.length);
                deletes.forEach(deleteLink => {
                    deleteLink.addEventListener("click", (event) => {
                        console.log('Delete clicked:', deleteLink);
                        if (!confirm("Are you sure you want to delete this reply?")) {
                            event.preventDefault();
                        }
                    });
                });
            });
        </script>

    </body>
</html>



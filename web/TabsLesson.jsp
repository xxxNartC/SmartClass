<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tab Navigation with Comment Form</title>
         <link rel="stylesheet" href="css/linearicons.css" />
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css" />
         <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

         
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 20px;
            }

            /* Container for the tabs */
            .tab-container {
                display: flex;
                justify-content: flex-start;
                border-bottom: 1px solid #ccc;
                margin-bottom: 20px;
            }

            /* Style for each tab */
            .tab {
                padding: 10px 20px;
                cursor: pointer;
                border: none;
                background-color: transparent;
                font-size: 16px;
                color: #0073b1;
                font-weight: bold;
                border-bottom: 3px solid transparent;
                transition: all 0.3s ease;
            }

            /* When the tab is active */
            .tab.active {
                border-bottom: 3px solid #0073b1;
                color: #333;
            }

            /* Content for each tab */
            .tab-content {
                display: none;
            }

            /* Show the active tab's content */
            .tab-content.active {
                display: block;
            }

            /* Additional content style */
            .tab-content {
                padding: 20px;
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 8px;
            }

            /* Style for the comment form */
            .comment-form {
                margin-top: 20px;
            }

            .comment-form textarea {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
                margin-bottom: 10px;
            }

            .comment-form button {
                padding: 10px 20px;
                background-color: #0073b1;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .comment-form button:hover {
                background-color: #005f8d;
            }

            /* Comment display */
            .comment-list {
                margin-top: 20px;
            }

            .comment-item {
                padding: 10px;
                background-color: #f9f9f9;
                border-radius: 5px;
                margin-bottom: 10px;
            }
            
            
             .message {
            color: white;
            font-size: 18px;
            margin-top: 5px;
        }
        .error {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
        body {
            font-family: Arial, sans-serif;
        }

        /* Common styles for action buttons */
        .list-inline-item {
            display: inline-block;
            margin-right: 20px;
        }

        .update i {
            color: #007bff; /* Blue for edit */
        }

        .update:hover i {
            color: #0056b3;
        }

        .delete i {
            color: #dc3545; /* Red for delete */
        }

        .delete:hover i {
            color: #c82333;
        }

        .report i {
            color: #ffc107; /* Yellow for report */
        }

        .report:hover i {
            color: #e0a800;
        }

        .reply i {
            color: #28a745; /* Green for reply */
        }

        .reply:hover i {
            color: #218838;
        }
            
            /* Styles for comment containers */
        .media-comment {
            background-color: #f1f1f1;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .media-comment .media-body {
            background-color: #f1f1f1;
            border-radius: 8px;
            padding: 15px;
        }

        .media-comment.own-comment .media-body {
            background-color: #dff0d8;
        }

        .media-comment.reply .media-body {
            background-color: #e8f5e9; /* Slightly different background for replies */
            margin-left: 30px; /* Indent reply comments */
            border-left: 3px solid #28a745; /* Green border to highlight reply */
            padding-left: 12px;
        }

        /* Styles for comment header */
        .comment-header {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
        }

        .comment-author {
            font-weight: bold;
            color: #333;
        }

        .comment-date {
            color: #888;
            font-size: 0.9em;
        }

        /* Styles for comment body */
        .comment-body {
            margin-bottom: 15px;
            color: #555;
        }
        .btnn{
            background-color: #007bff;
            color: white;
            font-weight: 600;
            border: none; /* Remove border */
            padding: 10px 20px; /* Padding for the button */
            font-size: 16px; /* Font size */
            border-radius: 10px; /* Rounded corners */
            cursor: pointer; /* Pointer cursor on hover */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow */
            transition: box-shadow 0.3s ease; /* Smooth transition for shadow */
            margin-top: 10px
        }

        .btnn:hover {
            box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15); /* Slightly larger shadow on hover */
        }

        .form-group {
            margin: 10px 0; /* Margin for spacing around the form group */
        }
        .chapter,.lesson{
            color : Black;
        }
        .rate-course .btn-close{
            position: absolute;
            border-radius: 50px;
            top: -34px;
            right: -25px;
        }
        textarea{
            outline: none
        }
        </style>
    </head>
    <body>

        <!-- Container for tabs -->
        <div class="tab-container">
            <button class="tab active" onclick="showTabContent(event, 'comment')">Comment</button>
            <button class="tab" onclick="showTabContent(event, 'discuss')">Discuss</button>
        </div>

        <!-- Tab content sections -->
        <div id="comment-area" class="tab-content active">
            <h2>Comment</h2>
            <p>Leave your comments below:</p>


            <!-- List of comments -->
            <div class="comment-list" id="comment-list">
                        <!--Comment-->
        <c:if test="${ not empty lesson_id}">
            <section>
                <div class="container d-flex ">
                    <div class="col-xl-7">
                        <c:forEach items="${datacommentlesson}" var="commentlesson">
                            <div class="media g-mb-30 media-comment">
                                <div class="media-body u-shadow-v18 g-bg-secondary g-pa-30">
                                    <div class="g-mb-15">
                                        <h5 class="h5 g-color-gray-dark-v1 mb-0">${commentlesson.getFullname()}</h5>
                                        <span class="g-color-gray-dark-v4 g-font-size-12">${commentlesson.getCommentDate()}</span>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <p>${commentlesson.getComment()}</p>
                                        <c:choose>
                                            <c:when test="${commentlesson.getAccountId()== sessionScope.user.getAccount_id()}">
                                                <div>
                                                    <a class="list-inline-item g-mr-20 update" name="${commentlesson.getComment()}&${commentlesson.getCommentId()}">
                                                        <i class="fa-solid fa-pen"></i>
                                                    </a>
                                                    <a class="list-inline-item g-mr-20 delete" href="deletecommentlesson?comment_id=${commentlesson.getCommentId()}&chapter_id=${chapter_id}&lesson_id=${lesson_id}">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </a>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div>
                                                    <a class="list-inline-item g-mr-20 report"
                                                       href="reportComment?comment_id=${commentlesson.getCommentId()}&chapter_id=${chapter_id}&lesson_id=${lesson_id}">
                                                        <i class="fa-solid fa-flag"></i>
                                                    </a>
                                                    <a class="list-inline-item g-mr-20 reply"
                                                       onclick="replyComment(${commentlesson.getCommentId()}, ${chapter_id}, ${lesson_id})">
                                                        <i class="fa-solid fa-reply"></i>
                                                    </a>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </div>
                            </div>
                            <c:forEach items="${datacommentlessonIsParent}" var="commentparent">
                                <c:if test="${commentlesson.getCommentId().equals(commentparent.getParentCommentId())}">
                                    <div class="media g-mb-30 media-comment reply">
                                        <div class="media-body u-shadow-v18 g-bg-secondary g-pa-30">
                                            <div class="g-mb-15">
                                                <h5 class="h5 g-color-gray-dark-v1 mb-0">${commentparent.getFullname()}</h5>
                                                <span class="g-color-gray-dark-v4 g-font-size-12">${commentparent.getCommentDate()}</span>
                                            </div>
                                            <div class="d-flex justify-content-between">
                                                <p>${commentparent.getComment()}</p>
                                                <c:choose>
                                                    <c:when test="${commentparent.getAccountId() == sessionScope.user.getAccount_id()}">
                                                        <div>
                                                            <a class="list-inline-item g-mr-20 update" name="${commentparent.getComment()}&${commentparent.getCommentId()}">
                                                                <i class="fa-solid fa-pen"></i>
                                                            </a>
                                                            <a class="list-inline-item g-mr-20 delete" href="deletecommentlesson?comment_id=${commentparent.getCommentId()}&subject_id=${subject_id}&course_id=${course_id}&chapter_id=${chapter_id}&lesson_id=${lesson_id}">
                                                                <i class="fa-solid fa-trash"></i>
                                                            </a>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div>
                                                            <a class="list-inline-item g-mr-20 report" href="reportComment?comment_id=${commentparent.getCommentId()}&chapter_id=${chapter_id}&lesson_id=${lesson_id}">
                                                                <i class="fa-solid fa-flag"></i>
                                                            </a>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </c:forEach>

                    </div>
                    <div class="col-xl-4">
                        <form id="alginform" action="addcommentlesson" method="Post" >
                            <div class="form-group">
                                <h4 style="color: white">Leave a comment</h4>
                                <label for="comment">Message</label>
                                <textarea name="comment" id="comment" msg cols="30" rows="5" class="form-control" required=""></textarea>
                                <div class="error" id="errcomment"></div>
                            </div>
                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" name="name" id="fullname" class="form-control" value="${sessionScope.user.getFullname()}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="text" name="email" id="email" class="form-control" value="${sessionScope.user.getEmail()}" readonly>
                            </div>
                            <div class="form-group">
                                <input type="text" name="lesson_id" id="lesson_id" class="form-control" value="${lesson_id}" style="display: none">
                                <input type="text" name="chapter_id" id="chapter_id" class="form-control" value="${chapter_id}" style="display: none">
                                <input type="text" name="comment_id" id="comment_id" class="form-control" style="display: none">
                            </div>
                            <div class="form-group">
                                <button type="submit" id="submit" class="btnn">Post Comment</button>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
        </c:if>
            </div>
        </div>

        <script>
            // Function to handle tab switching
            function showTabContent(event, tabId) {
                // Hide all tab contents
                var contents = document.getElementsByClassName('tab-content');
                for (var i = 0; i < contents.length; i++) {
                    contents[i].classList.remove('active');
                }

                // Remove active class from all tabs
                var tabs = document.getElementsByClassName('tab');
                for (var i = 0; i < tabs.length; i++) {
                    tabs[i].classList.remove('active');
                }

                // Show the selected tab content
                document.getElementById(tabId).classList.add('active');

                // Add active class to the clicked tab
                event.currentTarget.classList.add('active');
            }

            // Function to submit a comment and display it
            function submitComment() {
                var commentText = document.getElementById("comment-text").value;

                if (commentText.trim() === "") {
                    alert("Please enter a comment before submitting.");
                    return;
                }

                var commentList = document.getElementById("comment-list");
                var newComment = document.createElement("div");
                newComment.className = "comment-item";
                newComment.innerText = commentText;

                commentList.appendChild(newComment);

                // Clear the comment box after submitting
                document.getElementById("comment-text").value = "";
            }
        </script>
        
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const updates = document.querySelectorAll(".update");
                console.log('Found updates:', updates.length);
                updates.forEach(update => {
                    update.addEventListener("click", () => {
                        console.log('Update clicked:', update);
                        const comment = document.querySelector("#comment");
                        const alginform = document.querySelector("#alginform");
                        const comment_id = document.querySelector("#comment_id");
                        alginform.action = "updatecommentlesson";
                        if (update && update.name) {
                            var arr = update.name.split("&");
                            comment.value = arr[0];
                            comment_id.value = arr[1];
                            console.log(comment_id.value);
                            comment.focus();
                        }
                    });
                });

                // Submit comment
                const submit = document.querySelector("#submit");
                submit.addEventListener("click", (event) => {
                    let errcomment = document.querySelector("#errcomment");
                    const comment = document.querySelector("#comment").value.trim();
                    if (comment.length < 1 || comment.length > 500) {
                        errcomment.innerHTML = 'Comment must be between 1 and 500 characters.';
                        event.preventDefault();
                    }
                });

                //Delete
                const deletes = document.querySelectorAll(".delete");
                console.log('Found deletes:', deletes.length);
                deletes.forEach(deleteLink => {
                    deleteLink.addEventListener("click", (event) => {
                        console.log('Delete clicked:', deleteLink);
                        if (!confirm("Are you sure you want to delete this comment?")) {
                            event.preventDefault(); // Ng?n ch?n hành ??ng xóa n?u ng??i dùng ch?n "Cancel"
                        }
                    });
                });
            });
        </script>
        <script>
            function replyComment(comment_id, chapter_id, lesson_id) {
                console.log("Reply clicked with IDs:", comment_id, chapter_id, lesson_id);
                var comment = document.querySelector("#comment");
                comment.focus();
                let comment_id_input = document.querySelector("#comment_id");
                comment_id_input.value = comment_id;
                let chapter_id_input = document.querySelector("#chapter_id");
                chapter_id_input.value = chapter_id;
                let lesson_id_input = document.querySelector("#lesson_id");
                lesson_id_input.value = lesson_id;
            }
        </script>

    </body>
</html>



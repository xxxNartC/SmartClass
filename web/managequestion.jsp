
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <script src="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css"></script>
        <script src="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css"></script>
        <link rel="stylesheet" href="css/linearicons.css" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <link rel="stylesheet" href="css/bootstrap.css" />
        <link rel="stylesheet" href="css/magnific-popup.css" />
        <link rel="stylesheet" href="css/owl.carousel.css" />
        <link rel="stylesheet" href="css/nice-select.css">
        <link rel="stylesheet" href="css/hexagons.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css" />
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/style.css">
        <link href="css/subjectlist.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/linearicons.css" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <link rel="stylesheet" href="css/bootstrap.css" />
        <link rel="stylesheet" href="css/magnific-popup.css" />
        <link rel="stylesheet" href="css/owl.carousel.css" />
        <link rel="stylesheet" href="css/nice-select.css">
        <link rel="stylesheet" href="css/hexagons.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css" />
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:900|Roboto:400,400i,500,700" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/style1.css"/>
        <link rel="stylesheet" href="css/linearicons.css" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <link rel="stylesheet" href="css/bootstrap.css" />
        <link rel="stylesheet" href="css/magnific-popup.css" />
        <link rel="stylesheet" href="css/owl.carousel.css" />
        <link rel="stylesheet" href="css/nice-select.css">
        <link rel="stylesheet" href="css/hexagons.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css" />
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/style.css">
        <link href="css/subjectlist.css" rel="stylesheet" type="text/css"/>


        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Handlee&family=Nunito&display=swap" rel="stylesheet">

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Flaticon Font -->
        <link href="template course_detail/lib/flaticon/font/flaticon.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="template course_detail/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="css/managesubject.css" rel="stylesheet">

        <link href="template course_detail/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" rel="stylesheet">


        <!-- Contact Javascript File -->


        <!-- Template Javascript -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"><!-- comment -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



        <link href="css/managequestion.css" rel="stylesheet" type="text/css"/>
        <style>
            form {
                width: 500px; /* Adjust width as needed */
                margin: -20px auto;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            label {
                display: block;
                margin-bottom: 5px;
            }
            input[type="text"], select {
                width: 70%;
                padding: 10px;
                margin-bottom: 15px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 3px;
            }
            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
        </style>
    </head>
    <body>

        <input type="hidden" id="sid" value="${sid}">

        <div class="table-title">
            <h1 class="text-center mb-4">Manage Question</h1> <!-- Căn giữa tiêu đề và thêm khoảng cách phía dưới -->
            <div class="d-flex justify-content-end flex-column align-items-end">
                <div class="mb-2">
                    <a href="subjectmanage">
                        <button class="btn btn-primary" style="background-color: #ADD8E6; border-color: #ADD8E6; color:black; ">Back to Manage Subject</button>
                    </a>
                </div>
                <div class="mb-3">
                    <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#addone" style="color:black; background-color: #ADD8E6; border-color: #ADD8E6;">
                        Add Question
                    </button>
                </div>
            </div>
        </div>
    </div>



    <div style="">
        <div style="" class="row">
            <div class="col-md-4">
                <h1 style="color: Black; font-weight: bold;">FILTER CHAPTER</h1><br>
                <div>
                    <c:forEach items="${listCourse}" var="course">
                        <h3>Course ${course.course_no}: ${course.course_name}</h3>
                        <c:forEach items="${listChapter}" var="chapter">
                            <c:if test="${chapter.course_id == course.course_id}">
                                <input onclick="addChapterData()" type="checkbox" name="chapter" value="${chapter.chapter_id}">${chapter.chapter_name} <br/>
                            </c:if>
                        </c:forEach>

                    </c:forEach>
                </div>
            </div>

            <!--Start Table-->
            <div class="col-md-8">
    <table style="width: 100%" id="questiontable" class="cell-border">
        <thead>
            <tr style="background-color: #ADD8E6;">
                <th>Chapter</th>
                <th>Content</th>
                <th>Option 1</th>
                <th>Option 2</th>
                <th>Option 3</th>
                <th>Option 4</th>
                <th>Answer</th>
                <th>Level</th>
                <th>Edit</th>
            </tr>
        </thead>
        <tbody>
            <!-- DataTable will populate this -->
        </tbody>
    </table>
</div>
        </div>
    </div>
    <!--End Table-->
</div>
</div>
</div>        
</div>

<!--Begin Modal Add 1 Question-->
<div class="modal fade" id="addone" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div style="margin-right: 65rem;" class="modal-dialog">
        <div style="width: 70rem;" class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">FORM ADD QUESTION</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">

                </button>
            </div>
            <div class="modal-body row">
                <div class="col-md-5">

                    <c:forEach items="${listCourse}" var="course" >
                        <h5>Course ${course.course_no}: ${course.course_name}</h5>

                        <c:forEach items="${listChapter}" var="chapter" begin="0" end="0">
                            <c:if test="${chapter.course_id == course.course_id}">
                                <input checked type="radio" name="chapter-oq" value="${chapter.chapter_id}">${chapter.chapter_name} <br/>
                            </c:if>
                        </c:forEach>


                        <c:forEach items="${listChapter}" var="chapter" begin="1">
                            <c:if test="${chapter.course_id == course.course_id}">
                                <input type="radio" name="chapter-oq" value="${chapter.chapter_id}">${chapter.chapter_name} <br/>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </div>
                <div class="col-md-7">            
                    <form id="addOneQuestion">
                        <label for="content">Question Content:</label>
                        <input type="text" id="content" name="content" required><br>
                        <fieldset>
                            <legend>Options:</legend>
                            <div>
                                <label for="option1">Option 1:</label>
                                <input oninput="checkValidOption()" type="text" id="option1" name="options[]" >
                            </div>
                            <div>
                                <label for="option2">Option 2:</label>
                                <input oninput="checkValidOption()" type="text" id="option2" name="options[]" >
                            </div>
                            <div> 
                                <label for="option3">Option 3:</label>
                                <input oninput="checkValidOption()" type="text" id="option3" name="options[]">
                            </div>
                            <div>
                                <label for="option4">Option 4:</label>
                                <input oninput="checkValidOption()" type="text" id="option4" name="options[]">
                            </div>
                        </fieldset>
                        <label for="answer">Correct Answer:</label>
                        <select id="answer" name="answer">
                            <option id="sl-op0" value="0">Select Option</option>
                            <option name="op" id="sl-op1" style="display: none;" value="1">Option 1</option>
                            <option name="op" id="sl-op2" style="display: none;" value="2">Option 2</option>
                            <option name="op" id="sl-op3" style="display: none;" value="3">Option 3</option>
                            <option name="op" id="sl-op4" style="display: none;" value="4">Option 4</option>
                        </select><br>

                        <label for="level">Difficulty Level:</label>
                        <select id="level" name="level">
                            <option value="1">Easy</option>
                            <option value="2">Medium</option>
                            <option value="3">Hard</option>
                        </select><br>                    

                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button id="btnCancelAdd1Question" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button onclick="checkValidToAdd()" type="button" class="btn btn-primary">Add Question</button>
            </div>
        </div>
    </div>
</div>

<!--End Modal Add 1 Question-->


<!--Form to edit question-->

<div id="listFormToEditQuestion">

</div>

<!--End Form to edit question-->


<link href="css/dt.css" rel="stylesheet" type="text/css"/>
<script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<link href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css" type="text/javascript"/>
<script type="text/javascript">

                    var chapter = [];
                    var dataTabledata = [];
                    $(document).ready(function () {
                        loadQuestion();
                    });
                    function loadQuestion() {
                        var dataSendToController = {
                            checkedChapters: chapter.toString(),
                            sid: $('#sid').val()

                        };
                        $.ajax({
                            async: false,
                            url: "/smartclass/questionlist",
                            type: 'POST',
                            dataType: 'json',
                            data: dataSendToController,
                            success: function (data) {
                                console.log(data);

                                dataTabledata = data;
                                table.clear().draw();
                                table.rows.add(dataTabledata).draw();
                                // Reload form edit question
                                loadFormEditQuestion();

                            },
                            error: function (err) {
                                console.log(err);
                            }
                        });
                    }


                    var table = $('#questiontable').DataTable({
                        data: dataTabledata,
                        columns: [
                            {data:
                                        'quiz_id'
                            },
                            {data:
                                        'content'
                            },
                            {data:
                                        'option1'
                            },
                            {data:
                                        'option2'
                            },
                            {data:
                                        'option3'
                            },
                            {data:
                                        'option4'
                            },
                            {data:
                                        'answer'
                            },
                            {data:
                                        'level',
                                render: function (data) {
                                    if (data === '1' || data === 1) {
                                        return '<p>Easy</p>';
                                    }
                                    if (data === '2' || data === 2) {
                                        return '<p>Medium</p>';
                                    }
                                    if (data === '3' || data === 3) {
                                        return '<p>Hard</p>';
                                    }
                                }

                            },
                            {data: null,
                                render: function (data) {
                                    return ''
                                            + '<button class=" btn-success" data-toggle="modal" data-target="#editquestion' + data.question_id + '" >Edit</button>'

                                            + '<a href="deletequestion?qid=' + data.question_id + '&sid=' + document.getElementById('sid').value + ' ">'
                                            + '<button onclick="return confirm(\' Do you want to Delete this Question \')" class=" btn-danger">Delete </button>'
                                            + '</a>';
                                }
                            }
                        ]
                    });
                    //fucion de check add chapter
                    function addChapterData() {
                        var checkedChapter = document.getElementsByName('chapter');
                        var temp = [];
                        for (var i = 0; i < checkedChapter.length; i++) {
                            if (checkedChapter[i].checked && !temp.includes(checkedChapter[i].value)) {
                                temp.push(checkedChapter[i].value);
                            } else {
                                temp.splice(i, 1);
                            }
                        }
                        chapter = temp;
                        loadQuestion();
                        console.log(chapter.toString());
                    }

                    // funciton tu dong check chapter dau tien khi add
                    function addOneQuestion() {
                        var cs = document.getElementsByName('chapter-oq');
                        for (var i = 0; i < cs.length; i++) {
                            if (cs[i].checked) {
                                cs = cs[i].value;
                                break;
                            }

                        }
                        var dataSendToController = {
                            chapterid: cs,
                            content: document.getElementById('content').value.trim(),
                            option1: document.getElementById('option1').value.trim(),
                            option2: document.getElementById('option2').value.trim(),
                            option3: document.getElementById('option3').value.trim(),
                            option4: document.getElementById('option4').value.trim(),
                            ans: document.getElementById('answer').value,
                            level: document.getElementById('level').value,
                            sid: document.getElementById('sid').value
                        };
                        $.ajax({
                            async: false,
                            url: "/smartclass/addquestion",
                            type: 'POST',
                            dataType: 'json',
                            data: dataSendToController,
                            success: function (data) {
                                console.log(data);
                                alert(data);
                                document.getElementById('btnCancelAdd1Question').click();
                                loadQuestion();
                            },
                            error: function (err) {
                                console.log(err);
                            }
                        });
                    }



                    // check valid option

                    function checkValidOption() {

                        // xu ly option 1
                        if (document.getElementById('option1').value.trim() !== '') {
                            document.getElementById('sl-op1').style.display = 'block';
                        } else {
                            document.getElementById('sl-op1').style.display = 'none';
                        }
                        // xu ly option 2
                        if (document.getElementById('option2').value.trim() !== '') {
                            document.getElementById('sl-op2').style.display = 'block';
                        } else {
                            document.getElementById('sl-op2').style.display = 'none';
                        }
                        // xu ly option 3
                        if (document.getElementById('option3').value.trim() !== '') {
                            document.getElementById('sl-op3').style.display = 'block';
                        } else {
                            document.getElementById('sl-op3').style.display = 'none';
                        }
                        // xu ly option 4
                        if (document.getElementById('option4').value.trim() !== '') {
                            document.getElementById('sl-op4').style.display = 'block';
                        } else {
                            document.getElementById('sl-op4').style.display = 'none';
                        }

                        // xu ly muc Select Option
                        if (document.getElementById('option1').value.trim() === '' && document.getElementById('option2').value.trim() === ''
                                && document.getElementById('option3').value.trim() === '' && document.getElementById('option4').value.trim() === '') {
                            document.getElementById('sl-op0').style.display = 'block';
                        } else {
                            document.getElementById('sl-op0').style.display = 'none';
                        }


                        // auto select
                        var options = document.getElementById('answer').options;
                        for (var i = 0; i < options.length; i++) {
                            if (options[i].style.display === 'block') {
                                console.log(options[i].style.display);
                                document.getElementById('answer').selectedIndex = i;
                            }
                        }
                    }

                    // check valid after add 1 question
                    function checkValidToAdd() {
                        var count = 0;
                        if (document.getElementById('option1').value.trim() !== '') {
                            count++;
                        }
                        // xu ly option 2
                        if (document.getElementById('option2').value.trim() !== '') {
                            count++;
                        }
                        // xu ly option 3
                        if (document.getElementById('option3').value.trim() !== '') {
                            count++;
                        }
                        // xu ly option 4
                        if (document.getElementById('option4').value.trim() !== '') {
                            count++;
                        }

                        if (count < 2 || document.getElementById('content').value.trim() === '') {
                            alert('Form have at less 2 option and content');
                        } else {
                            addOneQuestion();
                        }
                    }


                    // load form to edit question
                    function loadFormEditQuestion() {
                        var modal = "";
                        var edit = document.getElementById('listFormToEditQuestion');
                        if (dataTabledata.length > 0) {
                            for (var i = 0; i < dataTabledata.length; i++) {
                                modal += `<div class="modal fade" id="editquestion` + dataTabledata[i].question_id + `" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div style="margin-right: 65rem;" class="modal-dialog">
                                                    <div style="width: 70rem;" class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">FORM EDIT QUESTION</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"> </button>
                                                        </div>
                                                        
                                                            <div class="col-md-7">            
                                                                <form style="width: 600px; margin: 11px auto; padding: 20px;border: 4px solid #ccc;border-radius: 15px; margin-right: -10rem">
                                                                    <label for="content">Question Content:</label>
                                                                    <input type="text" id="content-` + dataTabledata[i].question_id + `" name="content" value="` + dataTabledata[i].content + `" required><br>
                                                                    <fieldset>
                                                                        <legend>Options:</legend>
                                                                        <div>
                                                                            <label for="option1">Option 1:</label>
                                                                            <input oninput="checkValidOptionEdit(` + dataTabledata[i].question_id + `)" id="option1-` + dataTabledata[i].question_id + `" type="text" value="` + dataTabledata[i].option1 + `"  name="options[]" >
                                                                        </div>
                                                                        <div>
                                                                            <label for="option2">Option 2:</label>
                                                                            <input oninput="checkValidOptionEdit(` + dataTabledata[i].question_id + `)" id="option2-` + dataTabledata[i].question_id + `" type="text" value="` + dataTabledata[i].option2 + `" name="options[]" >
                                                                        </div>
                                                                        <div> 
                                                                            <label for="option3">Option 3:</label>
                                                                            <input oninput="checkValidOptionEdit(` + dataTabledata[i].question_id + `)" id="option3-` + dataTabledata[i].question_id + `" type="text" value="` + dataTabledata[i].option3 + `" name="options[]">
                                                                        </div>
                                                                        <div>
                                                                            <label for="option4">Option 4:</label>
                                                                            <input oninput="checkValidOptionEdit(` + dataTabledata[i].question_id + `)" id="option4-` + dataTabledata[i].question_id + `" type="text" value="` + dataTabledata[i].option4 + `" name="options[]">
                                                                        </div>
                                                                    </fieldset>
                                                                    <label for="answer">Correct Answer:</label>
                                                                    <select id="answer-` + dataTabledata[i].question_id + `" name="answer">
                                                                        <option id="sl-op0-` + dataTabledata[i].question_id + `" value="0">Select Option</option>
                                                                        <option id="sl-op1-` + dataTabledata[i].question_id + `" ` + (dataTabledata[i].answer === '1' ? 'selected' : '') + ` name="op"  style="display: none;" value="1">Option 1</option>
                                                                        <option id="sl-op2-` + dataTabledata[i].question_id + `" ` + (dataTabledata[i].answer === '2' ? 'selected' : '') + ` name="op"  style="display: none;" value="2">Option 2</option>
                                                                        <option id="sl-op3-` + dataTabledata[i].question_id + `" ` + (dataTabledata[i].answer === '3' ? 'selected' : '') + ` name="op"  style="display: none;" value="3">Option 3</option>
                                                                        <option id="sl-op4-` + dataTabledata[i].question_id + `" ` + (dataTabledata[i].answer === '4' ? 'selected' : '') + ` name="op"  style="display: none;" value="4">Option 4</option>
                                                                    </select><br>

                                                                    <label for="level">Difficulty Level:</label>
                                                                    <select id="level-` + dataTabledata[i].question_id + `">
                                                                        <option ` + (dataTabledata[i].level === '1' ? 'selected' : '') + ` value="1">Easy</option>
                                                                        <option ` + (dataTabledata[i].level === '2' ? 'selected' : '') + ` value="2">Medium</option>
                                                                        <option ` + (dataTabledata[i].level === '3' ? 'selected' : '') + ` value="3">Hard</option>
                                                                    </select><br>                    

                                                                </form>
                                                           
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button id="btnCancelAdd1Question-` + dataTabledata[i].question_id + `" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                            <button onclick="checkValidToEdit(` + dataTabledata[i].question_id + `)" type="button" class="btn btn-primary">Edit Question</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>`;
                            }
                            edit.innerHTML = modal;
                        }
                    }


                    // check valid information to edit (question id)
                    function checkValidOptionEdit(id) {
                        // xu ly option 1
                        if (document.getElementById('option1-' + id).value.trim() !== '') {
                            document.getElementById('sl-op1-' + id).style.display = 'block';
                        } else {
                            document.getElementById('sl-op1-' + id).style.display = 'none';
                        }
                        // xu ly option 2
                        if (document.getElementById('option2-' + id).value.trim() !== '') {
                            document.getElementById('sl-op2-' + id).style.display = 'block';
                        } else {
                            document.getElementById('sl-op2-' + id).style.display = 'none';
                        }
                        // xu ly option 3
                        if (document.getElementById('option3-' + id).value.trim() !== '') {
                            document.getElementById('sl-op3-' + id).style.display = 'block';
                        } else {
                            document.getElementById('sl-op3-' + id).style.display = 'none';
                        }
                        // xu ly option 4
                        if (document.getElementById('option4-' + id).value.trim() !== '') {
                            document.getElementById('sl-op4-' + id).style.display = 'block';
                        } else {
                            document.getElementById('sl-op4-' + id).style.display = 'none';
                        }

                        // xu ly muc Select Option
                        if (document.getElementById('option1-' + id).value.trim() === '' && document.getElementById('option2-' + id).value.trim() === ''
                                && document.getElementById('option3-' + id).value.trim() === '' && document.getElementById('option4-' + id).value.trim() === '') {
                            document.getElementById('sl-op0-' + id).style.display = 'block';
                        } else {
                            document.getElementById('sl-op0-' + id).style.display = 'none';
                        }


                        // auto select
                        var options = document.getElementById('answer-' + id).options;
                        for (var i = 0; i < options.length; i++) {
                            if (options[i].style.display === 'block') {
                                console.log(options[i].style.display);
                                document.getElementById('answer-' + id).selectedIndex = i;
                            }
                        }
                    }

                    function checkValidToEdit(id) {
                        var count = 0;
                        if (document.getElementById('option1-' + id).value.trim() !== '') {
                            count++;
                        }
                        // xu ly option 2
                        if (document.getElementById('option2-' + id).value.trim() !== '') {
                            count++;
                        }
                        // xu ly option 3
                        if (document.getElementById('option3-' + id).value.trim() !== '') {
                            count++;
                        }
                        // xu ly option 4
                        if (document.getElementById('option4-' + id).value.trim() !== '') {
                            count++;
                        }

                        if (count < 2 || document.getElementById('content-' + id).value.trim() === '') {
                            alert('Form have at less 2 option and content');
                        } else {
                            editQuestion(id);
                        }
                    }

                    function editQuestion(id) {
                        var dataSendToController = {
                            content: document.getElementById('content-' + id).value.trim(),
                            option1: document.getElementById('option1-' + id).value.trim(),
                            option2: document.getElementById('option2-' + id).value.trim(),
                            option3: document.getElementById('option3-' + id).value.trim(),
                            option4: document.getElementById('option4-' + id).value.trim(),
                            ans: document.getElementById('answer-' + id).value,
                            level: document.getElementById('level-' + id).value,
                            qid: id
                        };
                        $.ajax({
                            async: false,
                            url: "/smartclass/editquestion",
                            type: 'POST',
                            dataType: 'json',
                            data: dataSendToController,
                            success: function (data) {
                                console.log(data);
                                alert(data);
                                document.getElementById('btnCancelAdd1Question-' + id).click();
                                loadQuestion();
                            },
                            error: function (err) {
                                console.log(err);
                            }
                        });
                    }
</script>
</body>
</html>



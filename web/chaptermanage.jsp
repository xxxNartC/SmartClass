<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" />

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chapter List</title>
        <style>
            table {
                width: 80%; /* Set a fixed width for the table */
                margin: 0 auto; /* Center the table */
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid black;
            }
            th, td {
                padding: 10px;
                text-align: left;
                word-wrap: break-word; /* Ensure long text wraps to the next line */
            }
            th {
                background-color: #ADD8E6; /* Light blue background for headers */
            }
            td {
                max-width: 300px; /* Limit the width of each cell */
            }
            .add-chapter-btn {
                display: inline-block;
                margin-top: -18px; /* Điều chỉnh giá trị này để vị trí đúng */
                margin-right: 1%;
                float: right;
                background-color: #ADD8E6; /* Màu nền xanh nhạt */
                color: black; /* Chữ màu xanh */
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 5px;
                font-size: 16px;
            }

            .add-home-btn {
                display: inline-block;
                margin-top: -18px; /* Điều chỉnh giá trị này để vị trí đúng */
                margin-right: 1%;
                float: left;
                background-color: #ADD8E6; /* Màu nền xanh nhạt */
                color: black; /* Chữ màu xanh */
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 5px;
                font-size: 16px;
            }
        </style>
        <link href="img/favicon.ico" rel="icon">
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"
                integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>

        <!-- Mobile Specific Meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:900|Roboto:400,400i,500,700" rel="stylesheet" />     
        <link type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" rel="stylesheet">
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



        <!-- Customized Bootstrap Stylesheet -->
        <link href="template course_detail/css/style.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.7/css/dataTables.dataTables.min.css">
        <link href="img/favicon.ico" rel="icon">

    <body>
        <c:if test="${sessionScope.user.role_id == '3'}">
        </div>

    </nav>
    <h1 style="text-align: center;">Chapter List Management</h1>
    <!-- Add Subject button -->
    <a href="addchapter?course_id=${requestScope.course.course_id}" class="add-chapter-btn">Add Chapter</a>
    <!--Table nhung du lieu-->
    <table id="tablechapter" class="display" style="width:100%">
        <thead>
            <tr>

                <th>Chapter No</th>
                <th>Chapter Name</th>
                <th>Course ID</th>
                <td>Actions</td>
            </tr>
        </thead>
        <tbody>

        </tbody>
    </table>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="template course_detail/lib/easing/easing.min.js"></script>
    <script src="template course_detail/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="template course_detail/lib/isotope/isotope.pkgd.min.js"></script>
    <script src="template course_detail/lib/lightbox/js/lightbox.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="template course_detail/mail/jqBootstrapValidation.min.js"></script>
    <script src="template course_detail/mail/contact.js"></script>
    <!-- Template Javascript -->
    <script src="template course_detail/js/main.js"></script>
    <script src="https://cdn.datatables.net/2.0.7/js/dataTables.min.js"></script>
    <!--thu vien datatabe-->
    <script src="https://cdn.datatables.net/2.1.6/css/dataTables.dataTables.min.css"></script> 
    <script src="https://cdn.datatables.net/2.1.6/js/dataTables.min.js"></script>
    <!--CRUD on DataTable & Handle Exception-->
    <script type="text/javascript">
        // instance variable
        var table;
        //khoi dong document thi chay ham loaddata
        $(document).ready(function () {
            loadData();
        });

        function loadData() {
            $.ajax({
                //            lay du lieu tu url 
                url: "/smartclass/chaptermanage?course_id=${requestScope.course.course_id}",
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    console.log(data); // Kiểm tra dữ liệu
                    if (!table) { // Nếu table chưa được khởi tạo
                        table = $('#tablechapter').DataTable({
                            data: data, // gan du lieu cho data table
                            columns: [
                                {data: 'chapter_no'},
                                {data: 'chapter_name'},
                                {data: 'course_id'},
                                {data: null,
                                    render: function (data) {
                                        return '<a href="editchapter?chapter_id=' + data.chapter_id + '&course_id=${requestScope.course.course_id}">'
                                                + '<button class="btn btn-success btn-sm">Edit</button></br>'
                                                + '</a>'
                                                + '<a href="deletechapter?chapter_id=' + data.chapter_id + '&course_id=${requestScope.course.course_id}">'
                                                + '<button onclick="return confirm(\' Do you want to delete this CHAPTER \')" class="btn btn-danger btn-sm">Delete</button>'
                                                + '</a>'
                                                + '<a href="lessonmanage?chapter_id=' + data.chapter_id + '&course_id=${requestScope.course.course_id}">'
                                                + '<button class="btn btn-primary btn-sm">Lesson</button>'
                                                + '</a>';
                                    }
                                }
                            ],
                            language: {
                                emptyTable: "No data available in table"
                            }
                        });
                    } else {
                        table.clear().rows.add(data).draw(); // Cập nhật dữ liệu nếu table đã được khởi tạo
                    }
                },
                error: function (err) {
                    console.log(err);
                }
            });
        }

    </script>
</c:if>
<c:if test="${sessionScope.user.role_id != '3'}">    
    <div class="container">    
        <h1>You are not authorized to access this page.</h1>       
        <a href="index.html"><input class="btn" type="submit" value="Back to Home"/></a>  
    </div>  
</c:if>
</body>
</html>


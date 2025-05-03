<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<%@ page import="com.itextpdf.text.Document" %>
<%@ page import="com.itextpdf.text.Paragraph" %>
<%@ page import="com.itextpdf.text.pdf.PdfWriter" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>
<%@ page import="com.itextpdf.text.Font" %>
<%@ page import="com.itextpdf.text.FontFactory" %>
<%@ page import="com.itextpdf.text.Element" %>
<%@ page import="com.itextpdf.text.Phrase" %>
<%@ page import="com.itextpdf.text.pdf.PdfPCell" %>
<%@ page import="com.itextpdf.text.pdf.PdfPTable" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Course Certificates</title>
        <style>
            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid black;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <h2 style="text-align: center;">Course Certificate Details</h2>

        <%
            List<String> coursesWithDetails = (List<String>) request.getAttribute("coursesWithDetails");
            if (coursesWithDetails == null || coursesWithDetails.isEmpty()) {
        %>
        <p style="text-align: center;">No certificates found for this learner.</p>
        <%
            } else {
        %>
        <table>
            <tr>
                <th>Course Name</th>
                <th>Username</th>
                <th>Issue Date</th>
                <th>Description</th> 
            </tr>
            <%
                for (String courseDetail : coursesWithDetails) {
                    String[] details = courseDetail.split(", ");
            %>
            <tr>
                <td><%= details[0] %></td> 
                <td><%= details[1] %></td> 
                <td><%= details[2] %></td> 
                <td><%= details[3] %></td> 
            </tr>
            <%
                }
            %>
        </table>
        <%
            }
        %>

        <form method="post" action="generatePDF">
            <button type="submit">Generate PDF</button>
        </form>

    </body>
</html>

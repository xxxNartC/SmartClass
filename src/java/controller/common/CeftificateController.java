package controller.common;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import dao.CourseCertificateDAO;
import dao.SubjectCertificateDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Servlet to generate and return a PDF certificate for the learner.
 */
@WebServlet(name = "CeftificateController", urlPatterns = {"/ceftificate"})
public class CeftificateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("account_id") == null) {
            // Phiên làm việc đã hết hạn hoặc account_id không tồn tại, chuyển hướng người dùng đến trang đăng nhập
            response.sendRedirect("login.jsp");  // Hoặc trang xử lý khi hết phiên
            return;
        }
        int learnerId = (int) session.getAttribute("account_id");

        String courseName = request.getParameter("courseName");
        String subjectName = request.getParameter("subjectName");

        CourseCertificateDAO courseCertificateDAO = new CourseCertificateDAO();
        List<String> coursesWithDetails = courseCertificateDAO.getCoursesWithUsernameAndIssueDate(learnerId, courseName);

        SubjectCertificateDAO subjectCertificateDAO = new SubjectCertificateDAO();
        List<String> subjectsWithDetails = subjectCertificateDAO.getSubjectCertificateDAOByLearnerId(learnerId, subjectName);

//        if (coursesWithDetails == null || coursesWithDetails.isEmpty() && subjectsWithDetails == null || subjectsWithDetails.isEmpty()) {
//            // Send 404 Not Found error if no certificates are found
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No certificates found for this learner.");
//            return;
//        }

        // Generate PDF
        generatePdfCertificate(response, coursesWithDetails, subjectsWithDetails);
    }

    private void generatePdfCertificate(HttpServletResponse response, List<String> coursesWithDetails, List<String> subjectsWithDetails) throws IOException {
        // Set response headers to indicate a PDF download
response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=certificate.pdf");

        // Prepare to write the PDF to the output stream
        OutputStream outStream = response.getOutputStream();
        PdfWriter writer = new PdfWriter(outStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Add margins for content spacing
        document.setMargins(70, 50, 10, 50);  // Set appropriate margins to leave room for the frame

        // Draw the outer frame/border
        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
        Color borderColor = new DeviceRgb(0, 0, 0); // Black border color
        canvas.setLineWidth(2f)
                .setStrokeColor(borderColor)
                .rectangle(40, 40, PageSize.A4.getWidth() - 80, PageSize.A4.getHeight() - 80) // Adjust rectangle position
                .stroke();

        // Generate certificates for courses
        for (String courseDetail : coursesWithDetails) {
            String[] details = courseDetail.split(", ");
            String courseName = details[0];
            String username = details[1];
            String issueDate = details[2];
            String description = details[3];

            // Add the certificate content to the PDF
            document.add(new Paragraph("COURSE CERTIFICATE")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(24));

            document.add(new Paragraph(issueDate)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(12));

            document.add(new Paragraph(username)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(28));

            document.add(new Paragraph("Congratulations on completing the course!")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(14));

            document.add(new Paragraph(courseName)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(18));

            document.add(new Paragraph(description)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(12));

            document.add(new Paragraph("SmartLearning")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20));

            document.add(new Paragraph("\n\n"));  // Add some spacing for readability
        }

        // Generate certificates for subjects
        for (String subjectDetail : subjectsWithDetails) {
            String[] details = subjectDetail.split(", ");
            String username = details[0];
            String subjectName = details[1];
            String description = details[2];
            String categoryName = details[3];
// Add the certificate content to the PDF
            document.add(new Paragraph("SUBJECT CERTIFICATE")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(24));

            document.add(new Paragraph(username)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(28));

            document.add(new Paragraph("Congratulations on completing the subject!")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(14));

            document.add(new Paragraph(subjectName)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(18));

            document.add(new Paragraph(description)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(12));

            document.add(new Paragraph(categoryName)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(12));

            document.add(new Paragraph("SmartLearning")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20));

            document.add(new Paragraph("\n\n"));  // Add some spacing for readability
        }

        // Close the document
        document.close();
    }
}
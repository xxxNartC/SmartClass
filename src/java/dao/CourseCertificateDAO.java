package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CourseCertificate;

public class CourseCertificateDAO extends DBContext {

    public CourseCertificate getCourseCertificateByLearnerCourseId(int learnerCourseId) throws SQLException {
        String sql = "SELECT * FROM course_certificate WHERE learner_course_id=?";
        CourseCertificate courseCertificate = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, learnerCourseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                courseCertificate = new CourseCertificate(
                        resultSet.getInt("course_certificate_id"),
                        resultSet.getInt("learner_course_id"),
                        resultSet.getDate("issue_date"));
                System.out.println("Course certificate retrieved: " + courseCertificate.getCourseCertificateId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseCertificate;
    }
    
    public void insertCourseCertificate(int learnerCourseId) {
        String sql = "INSERT INTO course_certificate(learner_course_id, issue_date) VALUES (?, GETDATE())";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, learnerCourseId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseCertificateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public List<String> getCoursesWithUsernameAndIssueDate(int learnerId, String courseName) {
        List<String> coursesWithDetails = new ArrayList<>();
        try {
            String sql = "SELECT c.course_name, c.description, a.username, cc.issue_date\n"
                    + "FROM learner_course lc\n"
                    + "JOIN account a ON lc.learner_id = a.account_id\n"
                    + "JOIN course c ON lc.course_id = c.course_id\n"
                    + "JOIN course_certificate cc ON lc.id = cc.learner_course_id\n"
                    + "WHERE lc.learner_id = ? AND c.course_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, learnerId);
            statement.setString(2, courseName);  // Lưu ý không dùng dấu phần trăm nếu bạn tìm kiếm chính xác
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String courseNameResult = rs.getString("course_name");
                String username = rs.getString("username");
                String issueDate = rs.getDate("issue_date").toString();  // Có thể chuyển đổi thành chuỗi nếu cần
                String description = rs.getString("description");
                coursesWithDetails.add(courseNameResult + ", " + username + ", " + issueDate + ", " + description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursesWithDetails;
    }

    public static void main(String[] args) {
        CourseCertificateDAO courseCertificateDAO = new CourseCertificateDAO();
        try {
            List<String> coursesWithDetails = courseCertificateDAO.getCoursesWithUsernameAndIssueDate(2, "Introduction to Big Data"); // Thay thế bằng learner ID và course name thực tế
            for (String courseDetail : coursesWithDetails) {
                System.out.println(courseDetail);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

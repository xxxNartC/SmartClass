package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Subject Certificates.
 *
 * @author TRUONG GIANG
 */
public class SubjectCertificateDAO extends DBContext {

    /**
     * Retrieves a list of subject certificates for a given learner ID and
     * subject name.
     *
     * @param learnerId The learner's ID.
     * @param subjectName The name of the subject.
     * @return A list of subject certificates, each represented as a string
     * containing username, subject name, description, and category name,
     * separated by commas.
     */
    public List<String> getSubjectCertificateDAOByLearnerId(int learnerId, String subjectName) {
        List<String> subjectsWithDetails = new ArrayList<>();
        try {
            String sql = "SELECT a.username, s.subject_name, s.description, c.category_name\n"
                    + "FROM learner_subject ls\n"
                    + "JOIN account a ON ls.learner_id = a.account_id\n"
                    + "JOIN subject s ON ls.subject_id = s.subject_id\n"
                    + "JOIN category c ON s.category_id = c.category_id\n"
                    + "WHERE ls.learner_id = ? AND s.subject_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, learnerId); // Đặt giá trị cho learnerId
            statement.setString(2, subjectName); // Đặt giá trị cho subjectName
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String subjectNameResult = rs.getString("subject_name");
                String description = rs.getString("description");
                String categoryName = rs.getString("category_name");
                subjectsWithDetails.add(username + ", " + subjectNameResult + ", " + description + ", " + categoryName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectCertificateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjectsWithDetails;
    }

    /**
     * Main method for testing purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SubjectCertificateDAO subjectCertificateDAO = new SubjectCertificateDAO();
        try {
            List<String> subjectsWithDetails = subjectCertificateDAO.getSubjectCertificateDAOByLearnerId(2, "Academic Skills for University Success");
            for (String subjectDetail : subjectsWithDetails) {
                System.out.println(subjectDetail);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

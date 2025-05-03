/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author TRUONG GIANG
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.LearnerCourse;

public class LearnerCourseDAO extends DBContext {

    public boolean insertRate(int rating, String learner_id, String course_id, Date date) {
        boolean check = false;
        String sql = "INSERT INTO learner_course (learner_id, course_id, rate, createdDate) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(learner_id));
            stm.setInt(2, Integer.parseInt(course_id));
            stm.setInt(3, rating);
            stm.setDate(4, new java.sql.Date(date.getTime()));
            int number = stm.executeUpdate();
            check = number > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return check;
    }

    public boolean insertRateAndFeedBack(int rating, String learner_id, String course_id, String feedback, Date date) {
        boolean check = false;
        String sql = "INSERT INTO learner_course (learner_id, course_id, rate, feedback, createdDate) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(learner_id));
            stm.setInt(2, Integer.parseInt(course_id));
            stm.setInt(3, rating);
            stm.setString(4, feedback);
            stm.setDate(5, new java.sql.Date(date.getTime()));
            int number = stm.executeUpdate();
            check = number > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return check;
    }

    // Get the latest learner course for a specific course  
    public LearnerCourse getLatestLearnerCourseByCourseId(int courseId) throws SQLException {
        String sql = "SELECT TOP 1 * FROM learner_course WHERE course_id = ? ORDER BY createdDate DESC";
        LearnerCourse learnerCourse = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                learnerCourse = new LearnerCourse(resultSet.getInt("id"), resultSet.getInt("learner_id"), resultSet.getInt("course_id"), resultSet.getInt("status_id"), resultSet.getDouble("rate"), resultSet.getString("feedback"), resultSet.getDate("createdDate"));
                System.out.println("Latest learner course retrieved for course ID: " + courseId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return learnerCourse;
    }

    public int getLearnerCourseIdByCourseIdAndLearnerId(String course_id, String learner_id) throws SQLException {
        String sql = "SELECT id FROM learner_course WHERE course_id = ? AND learner_id = ?";
        int learnerCourseId = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, Integer.parseInt(course_id));
            preparedStatement.setInt(2, Integer.parseInt(learner_id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                learnerCourseId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return learnerCourseId;
    }



    public void insertLearnerCourse(LearnerCourse learnerCourse) throws SQLException {
        String sql = "INSERT INTO learner_course (learner_id, course_id, status_id, rate, feedback, createdDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, learnerCourse.getLearnerId());
            preparedStatement.setInt(2, learnerCourse.getCourseId());
            preparedStatement.setInt(3, learnerCourse.getStatusId());
            preparedStatement.setDouble(4, learnerCourse.getRate());
            preparedStatement.setString(5, learnerCourse.getFeedback());
            preparedStatement.setDate(6, new java.sql.Date(learnerCourse.getCreatedDate().getTime())); // Sử dụng java.sql.Date cho kiểu date

            preparedStatement.executeUpdate();
            System.out.println("Insert learner course successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLearnerCourse(LearnerCourse learnerCourse) throws SQLException {
        String sql = "UPDATE learner_course SET status_id=?, rate=?, feedback=?, createdDate=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, learnerCourse.getStatusId());
            preparedStatement.setDouble(2, learnerCourse.getRate());
            preparedStatement.setString(3, learnerCourse.getFeedback());
            preparedStatement.setDate(4, new java.sql.Date(learnerCourse.getCreatedDate().getTime()));
            preparedStatement.setInt(5, learnerCourse.getId());

            preparedStatement.executeUpdate();
            System.out.println("Update learner course successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLearnerCourseStatus(int learnerCourseId, int statusId) throws SQLException {
        String sql = "UPDATE learner_course SET status_id=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, statusId);
            preparedStatement.setInt(2, learnerCourseId);

            preparedStatement.executeUpdate();
            System.out.println("Update learner course status successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLearnerCourse(int id) throws SQLException {
        String sql = "DELETE FROM learner_course WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Delete learner course successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LearnerCourse getLearnerCourseById(int id) throws SQLException {
        String sql = "SELECT * FROM learner_course WHERE id=?";
        LearnerCourse learnerCourse = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                learnerCourse = new LearnerCourse(
                        resultSet.getInt("id"),
                        resultSet.getInt("learner_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getInt("status_id"),
                        resultSet.getDouble("rate"),
                        resultSet.getString("feedback"),
                        resultSet.getDate("createdDate"));
                System.out.println("Learner course retrieved: " + learnerCourse.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return learnerCourse;
    }

    public List<LearnerCourse> getLearnerCoursesByLearnerId(int learnerId) throws SQLException {
        List<LearnerCourse> learnerCourses = new ArrayList<>();
        String sql = "SELECT * FROM learner_course WHERE learner_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, learnerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LearnerCourse learnerCourse = new LearnerCourse(
                        resultSet.getInt("id"),
                        resultSet.getInt("learner_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getInt("status_id"),
                        resultSet.getDouble("rate"),
                        resultSet.getString("feedback"),
                        resultSet.getDate("createdDate"));
                learnerCourses.add(learnerCourse);
            }
            System.out.println("Retrieved " + learnerCourses.size() + " learner courses for learner ID: " + learnerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return learnerCourses;
    }
    
    public void updateRate(int learnerCourseId, int rating, Date date) {
        String sql = "UPDATE learner_course SET rate = ?, createdDate = ? WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, rating);
            stm.setDate(2, new java.sql.Date(date.getTime()));
            stm.setInt(3, learnerCourseId);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRateAndFeedBack(int learnerCourseId, int rating, String feedback, Date date) {
        String sql = "UPDATE learner_course SET rate = ?, feedback = ?, createdDate = ? WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, rating);
            stm.setString(2, feedback);
            stm.setDate(3, new java.sql.Date(date.getTime()));
            stm.setInt(4, learnerCourseId);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Main method to test the DAO
    public static void main(String[] args) {
        LearnerCourseDAO learnerCourseDAO = new LearnerCourseDAO();

        // Tạo một LearnerCourse mới để kiểm tra hàm insert
        LearnerCourse newLearnerCourse = new LearnerCourse(1, 1, 1, 1, 4.5, "Good course!", new java.util.Date()); // Sử dụng java.util.Date cho ngày hiện tại

        try {
            // Test Insert
            learnerCourseDAO.insertLearnerCourse(newLearnerCourse);

            // Test Get LearnerCourse by ID
            LearnerCourse learnerCourse = learnerCourseDAO.getLearnerCourseById(1);
            if (learnerCourse != null) {
                System.out.println("Learner course retrieved: " + learnerCourse.getId());
            }

            // Test Update LearnerCourse
            if (learnerCourse != null) {
                learnerCourse.setRate(5.0); // Cập nhật rate
                learnerCourseDAO.updateLearnerCourse(learnerCourse);
            }

            // Test Delete LearnerCourse
            learnerCourseDAO.deleteLearnerCourse(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

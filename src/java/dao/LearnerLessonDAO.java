/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author TRUONG GIANG
 */
public class LearnerLessonDAO extends DBContext {
    
     public void updateLearnerLessonStatus(int learnerId, String lessonId) {
        String sql = "UPDATE learner_lesson SET status_id = 2 WHERE learner_id = ? AND lesson_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, learnerId);
            statement.setString(2, lessonId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating learner lesson status: " + e.getMessage());
        }
    }
 public boolean updateStatusLesson(String learner_id, String lesson_id) {
        boolean check = false;
        String sql = "UPDATE learner_lesson SET status_id = 2 WHERE learner_id = ? AND lesson_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            // Validate input parameters before using them
            if (learner_id == null || learner_id.isEmpty() || lesson_id == null || lesson_id.isEmpty()) {
                throw new IllegalArgumentException("Input parameters cannot be null or empty.");
            }
            stm.setInt(1, Integer.parseInt(learner_id));
            stm.setInt(2, Integer.parseInt(lesson_id));
            int number = stm.executeUpdate();
            check = number > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle the NumberFormatException gracefully
            // Log the error for debugging
            System.err.println("Error: " + e.getMessage());
            // Re-throw the exception to be handled by the servlet
            throw e;
        }

        return check;
    }

}


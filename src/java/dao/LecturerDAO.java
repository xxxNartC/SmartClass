package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Account;
import model.Subject;

public class LecturerDAO extends DBContext {

    public List<Account> getLecturersWithCourses() {
        List<Account> lecturers = new ArrayList<>();
        try {
            String sql = "SELECT a.fullname AS LecturerName, "
                    + "s.subject_name AS SubjectName, "
                    + "STRING_AGG(c.course_name, CHAR(10)) AS CourseNames "
                    + "FROM account a "
                    + "JOIN subject s ON a.account_id = s.lecturer_id "
                    + "LEFT JOIN course c ON s.subject_id = c.subject_id " // Changed to LEFT JOIN
                    + "GROUP BY a.fullname, s.subject_name "
                    + "ORDER BY a.fullname, s.subject_name"; // Added ORDER BY

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Account lecturer = null;
            while (rs.next()) {
                String lecturerName = rs.getString("LecturerName");
                String subjectName = rs.getString("SubjectName");
                String courses = rs.getString("CourseNames");

                // Find existing lecturer or create a new one
                if (lecturer == null || !lecturer.getFullname().equals(lecturerName)) {
                    lecturer = new Account();
                    lecturer.setFullname(lecturerName);
                    lecturer.setSubjects(new ArrayList<>());
                    lecturers.add(lecturer);
                }

                // Add subject and courses to lecturer
                Subject subject = new Subject();
                subject.setSubject_name(subjectName);
                // Split the courses by new line and handle null case
                if (courses != null) {
                    subject.setCoursesList(Arrays.asList(courses.split("\n")));
                } else {
                    subject.setCoursesList(new ArrayList<>()); // Empty list if no courses
                }
                lecturer.getSubjects().add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lecturers;
    }
}

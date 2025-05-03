package dao;

import dal.DBContext;
import model.GroupedSubject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectStudentDAO extends DBContext {

    public List<GroupedSubject> getGroupedSubjectStudentList(int lecturerId) {
        List<GroupedSubject> groupedSubjects = new ArrayList<>();
        Map<String, List<String>> subjectToStudents = new HashMap<>();

        String sql = "SELECT s.subject_name, a.fullname " +
                     "FROM subject s " +
                     "JOIN order_detail od ON s.subject_id = od.subject_id " +
                     "JOIN orders o ON od.order_id = o.order_id " +
                     "JOIN account a ON o.account_id = a.account_id " +
                     "WHERE s.lecturer_id = ? " +
                     "AND o.payment_status = 'Success'";

        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lecturerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String subjectName = rs.getString("subject_name");
                String studentName = rs.getString("fullname");

                subjectToStudents
                    .computeIfAbsent(subjectName, k -> new ArrayList<>())
                    .add(studentName);
            }

            // Convert map to list of GroupedSubject objects
            for (Map.Entry<String, List<String>> entry : subjectToStudents.entrySet()) {
                String subjectName = entry.getKey();
                String studentNames = String.join(", ", entry.getValue());
                groupedSubjects.add(new GroupedSubject(subjectName, studentNames));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupedSubjects;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Assignment;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.AssignmentSubmitted;
import model.Course;
import model.Subject;

/**
 *
 * @author ThaiGay
 */
public class AssignmentDAO extends DBContext {

    // Thêm assignment
    public void addAssignment(String content, String document, String course_id) {
        String sql = "insert into assignment(content, document, course_id) values(?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, content);
            ps.setString(2, document);
            ps.setString(3, course_id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Update Assignment
    public void updateAssignment(String content, String document, String assignment_id) {
        String sql = "update assignment set\n"
                + "content = ?,\n"
                + "document = ?\n"
                + "where assignment_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, content);
            ps.setString(2, document);
            ps.setString(3, assignment_id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // get asm by id
    public Assignment getAssignmentById(String assignmentid) {
        String sql = "select * from assignment where assignment_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, assignmentid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Assignment(rs.getString(1),
                        rs.getString(2).trim(),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    // get asm by Course id
    public Assignment getAssignmentByCourseId(String courseid) {
        String sql = "select * from assignment where course_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, courseid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Assignment(rs.getString(1),
                        rs.getString(2).trim(),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    // submit assignment
    public void submitAssignment(String learner_id, String assignment_id, String answer, String document,
            String status, String submitted_date) {
        String sql = """
                     insert into assignmentsubmitted(learner_id, assignment_id, answer, document, status, submitted_date)
                     values(?,?,?,?,?,?);
                     """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, learner_id);
            ps.setString(2, assignment_id);
            ps.setString(3, answer);
            ps.setString(4, document);
            ps.setString(5, status);
            ps.setString(6, submitted_date);

            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Get all subject by lecturer id
    public List<Subject> getSubjectByLecturerId(String lecturerid) {
        List<Subject> list = new ArrayList<>();
        String sql = "select subject_id, subject_name, image from subject where lecturer_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, lecturerid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_id(rs.getString(1));
                s.setSubject_name(rs.getString(2));
                s.setImage(rs.getString(3));
                list.add(s);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    // get all course by subject id
    public List<Course> getCourseBySubjectId(String subjectid) {
        List<Course> list = new ArrayList<>();
        String sql = "select c.course_id, c.course_name, c.course_no from course c join subject s on c.subject_id = s.subject_id where s.subject_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subjectid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCourse_id(rs.getString(1));
                c.setCourse_name(rs.getString(2));
                c.setCourse_no(rs.getString(3));
                list.add(c);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    // get all assignment submitted by course id
    public List<AssignmentSubmitted> getAssignmentSubmittedByCourseId(String courseid) {
        List<AssignmentSubmitted> list = new ArrayList<>();
        String sql = "select asm.* from assignmentsubmitted asm "
                + "join assignment a on asm.assignment_id = a.assignment_id where a.course_id = ? order by mark asc;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, courseid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AssignmentSubmitted asm = new AssignmentSubmitted();
                asm.setSubmitted_id(rs.getString(1));
                asm.setLearner_id(rs.getString(2));
                asm.setAssignment_id(rs.getString(3));
                asm.setAnswer(rs.getString(4));
                asm.setDocument(rs.getString(5));
                asm.setMark(rs.getString(6));
                asm.setComment(rs.getString(7));
                asm.setStatus(rs.getString(8));
                asm.setSubmitted_date(rs.getString(8));
                asm.setResponse_date(rs.getString(9));
                list.add(asm);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    // total records
    public int getTotalSubmittedAssignmentByCourseId(String course_id, String status) {
        String sql = "select a.course_id from assignmentsubmitted asm join assignment a on asm.assignment_id = a.assignment_id \n"
                + "where a.course_id = ?";
        if (!status.equalsIgnoreCase("all")) {
            sql += " and status like '" + status + "'";
        }
        int count = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, course_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (SQLException e) {
        }
        return count;
    }

    // get assignment submitted detail
    public AssignmentSubmitted getAssignmentSubmittedDetail(String submitted_id) {
        String sql = "select * from assignmentsubmitted where submitted_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, submitted_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AssignmentSubmitted asm = new AssignmentSubmitted();
                asm.setSubmitted_id(rs.getString(1));
                asm.setLearner_id(rs.getString(2));
                asm.setAssignment_id(rs.getString(3));
                asm.setAnswer(rs.getString(4));
                asm.setDocument(rs.getString(5));
                asm.setMark(rs.getString(6));
                asm.setComment(rs.getString(7));
                asm.setStatus(rs.getString(8));
                asm.setSubmitted_date(rs.getString(8));
                asm.setResponse_date(rs.getString(9));
                return asm;
            }
        } catch (SQLException e) {
        }

        return null;
    }

    // Lecturer cham diem => update lai asm submitted
    public void markedAssignmentSubmitted(String submitted_id, String mark, String comment, String status, String response_date) {
        String sql = "update assignmentsubmitted set\n"
                + "mark = ?,\n"
                + "comment = ?,\n"
                + "status = ?,\n"
                + "response_date = ?\n"
                + "where submitted_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, mark);
            ps.setString(2, comment);
            ps.setString(3, status);
            ps.setString(4, response_date);
            ps.setString(5, submitted_id);

            ps.executeUpdate();
            System.out.println(sql);
        } catch (SQLException e) {
        }
    }

    // tim bai lam chi tiet cho learner
    public AssignmentSubmitted findSubmittedForLearner(String courseid, String learnerid) {
        String sql = "select asm.* from assignmentsubmitted asm join assignment a on asm.assignment_id = a.assignment_id\n"
                + "where a.course_id = ? and asm.learner_id = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, courseid);
            ps.setString(2, learnerid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AssignmentSubmitted asm = new AssignmentSubmitted();
                asm.setSubmitted_id(rs.getString(1));
                asm.setLearner_id(rs.getString(2));
                asm.setAssignment_id(rs.getString(3));
                asm.setAnswer(rs.getString(4));
                asm.setDocument(rs.getString(5));
                asm.setMark(rs.getString(6));
                asm.setComment(rs.getString(7));
                asm.setStatus(rs.getString(8));
                asm.setSubmitted_date(rs.getString(8));
                asm.setResponse_date(rs.getString(9));
                return asm;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<AssignmentSubmitted> getAssignmentSubmittedByCourseId(String courseid, int page, int records_per_page, String status) {
        List<AssignmentSubmitted> list = new ArrayList<>();
        String sql = "select asm.* from assignmentsubmitted asm "
                + "join assignment a on asm.assignment_id = a.assignment_id where a.course_id = ? ";
        if (!status.equalsIgnoreCase("all")) {
            sql += " and status like '" + status + "'";
        }
        sql += "\n order by mark asc offset ? rows fetch next ? rows only;";
        int start = (page - 1) * records_per_page;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, courseid);
            ps.setInt(3, records_per_page);
            ps.setInt(2, start);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AssignmentSubmitted asm = new AssignmentSubmitted();
                asm.setSubmitted_id(rs.getString(1));
                asm.setLearner_id(rs.getString(2));
                asm.setAssignment_id(rs.getString(3));
                asm.setAnswer(rs.getString(4));
                asm.setDocument(rs.getString(5));
                asm.setMark(rs.getString(6));
                asm.setComment(rs.getString(7));
                asm.setStatus(rs.getString(8));
                asm.setSubmitted_date(rs.getString(8));
                asm.setResponse_date(rs.getString(9));
                list.add(asm);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public int getSubjectIdByCourseId(String cid) {
        String sql = "select subject_id from course where course_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public List<AssignmentSubmitted> listStudentSubmittedBySubjectId(String learnerId, String subjectId) {

        List<AssignmentSubmitted> list = new ArrayList<>();
        String sql = "select asm_submitted.submitted_id, asm_submitted.[status], c.course_name from assignmentsubmitted asm_submitted\n"
                + "join assignment asm on asm_submitted.assignment_id = asm.assignment_id\n"
                + "join course c on asm.course_id = c.course_id\n"
                + "where asm_submitted.learner_id = ? and c.subject_id = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, learnerId);
            ps.setString(2, subjectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AssignmentSubmitted asm = new AssignmentSubmitted();
                asm.setSubmitted_id(rs.getString(1));
                asm.setStatus(rs.getString(2));
                asm.setCourse_name(rs.getString(3));
                list.add(asm);
            }
        } catch (SQLException e) {
        }

        return list;
    }

    public static void main(String[] args) {
        AssignmentDAO ad = new AssignmentDAO();
        System.out.println(ad.getTotalSubmittedAssignmentByCourseId("5", "pending"));
    }

}

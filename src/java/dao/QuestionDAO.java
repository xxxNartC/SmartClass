/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Chapter;
import model.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author slhoa
 */
public class QuestionDAO extends DBContext {

    private static final Logger logger = LogManager.getLogger(QuestionDAO.class);

    // subject id = 2 => course: 5,6,7,8
    private String getCourseIdBySubjectId(String subjectId) {
        String result = "";
        String sql = "select * from course where subject_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subjectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                result += "," + rs.getString(1); //1,2,3,4
            }
        } catch (SQLException e) {
            logger.error(e);
        }

        return result.substring(1);
    }

    // lay cac chapter thuoc mon hoc
    public List<Chapter> getChapterOfSubject(String subjectId) {
        List<Chapter> list = new ArrayList<>();
        String sql = "select * from chapter where course_id in (" + getCourseIdBySubjectId(subjectId) + ")";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter();
                c.setChapter_id(rs.getString(1));
                c.setChapter_no(rs.getString(2));
                c.setChapter_name(rs.getString(3));

                c.setCourse_id(rs.getString(4));
                list.add(c);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        System.out.println(sql);
        return list;
    }

    // Lay question bang chapter id (optional) neu tick thi lay theo id con ko tick thi lay all question thuoc ve mon hoc day
    public List<Question> getQuestionByChapterId(String subject_id, String chapters) {
        List<Question> list = new ArrayList();

        String sql = """
                     select * from question where subject_id = ?
                     """;

        try {
            if (!chapters.isBlank()) {
                sql += "and quiz_id in (" + chapters + ")";
            }
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, subject_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setQuestion_id(rs.getString(1));
                q.setContent(rs.getString(2));
                q.setOption1(rs.getString(3));
                q.setOption2(rs.getString(4));
                q.setOption3(rs.getString(5));
                q.setOption4(rs.getString(6));
                q.setAnswer(rs.getString(7));
                q.setSubject_id(rs.getString(8));
                q.setQuiz_id(rs.getString(9));
                q.setLevel(rs.getString(10));
                list.add(q);

            }
        } catch (SQLException e) {
            logger.error(e);
        }

        return list;

    }

    public void addQuestion(String content, String op1, String op2, String op3, String op4, String ans, String sid, String chapterid, String level) {
        String sql = "insert into question(content, option1, option2, option3, option4, answer, subject_id,quiz_id, level) values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, content);
            stm.setString(2, op1);
            stm.setString(3, op2);
            stm.setString(4, op3);
            stm.setString(5, op4);
            stm.setString(6, ans);
            stm.setString(7, sid);
            stm.setString(8, chapterid);
            stm.setString(9, level);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }

    }
    public void deleteQuestion(String qid){
        String sql = """
                     delete from question 
                     where question_id = ?
                     """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, qid);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
    
    public void editQuestion(String content, String op1, String op2, String op3, String op4, String ans , String level, String qid) {
        String sql = """
                     update question
                     set content = ?,
                     option1 = ?,
                     option2 = ?,
                     option3 = ?,
                     option4 = ?,
                     answer = ?,
                     level = ?
                     where question_id = ?
                     """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, content);
            stm.setString(2, op1);
            stm.setString(3, op2);
            stm.setString(4, op3);
            stm.setString(5, op4);
            stm.setString(6, ans);
            stm.setString(7, level);
            stm.setString(8, qid);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public static void main(String[] args) {
        QuestionDAO q = new QuestionDAO();
        List<Question> l = q.getQuestionByChapterId("1", "1");
        System.out.println(l.size());
    }

}

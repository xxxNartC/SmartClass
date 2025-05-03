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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Question;
import model.Quiz;
import model.QuizResult;

/**
 *
 *
 */
public class QuizDAO extends dal.DBContext {

    // option 10
    private static final int MIN_NUMBER_OF_QUESTION = 10;
    private static final int[] MIN_NUMBER_OF_EACH_LEVEL = {4, 4, 2};

    // ti le ramdom question cho quiz 
    private static final int[] PERCENT = {40, 40, 20};

    // option 15
    private static final int MIN_NUMBER_OF_QUESTION_15 = 15;
    private static final int[] MIN_NUMBER_OF_EACH_LEVEL_15 = {6, 6, 3};

    // option 20
    private static final int MIN_NUMBER_OF_QUESTION_20 = 20;
    private static final int[] MIN_NUMBER_OF_EACH_LEVEL_20 = {8, 8, 4};

    public boolean checkNumberOfQuestion(String chapterid) {
        String sql = "select count(*) as total_question_of_chapter from question where quiz_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapterid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) < MIN_NUMBER_OF_QUESTION) {
                    return false;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }

    public boolean checkNumberOfQuestionEachLevel(String chapterid) {
        int count = 0;
        int array[] = new int[3];
        String sql = "select level, count(level) as no_question_level from question where quiz_id = ? group by level;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapterid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                array[count] = rs.getInt(2);
                count++;
            }
            if (count == 3) {
                if (array[0] >= MIN_NUMBER_OF_EACH_LEVEL[0] && array[1] >= MIN_NUMBER_OF_EACH_LEVEL[1] && array[2] >= MIN_NUMBER_OF_EACH_LEVEL[2]) {
                    return true;
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean checkOption15Questions(String chapterid) {
        int count = 0;
        int array[] = new int[3];
        String sql = "select level, count(level) as no_question_level from question where quiz_id = ? group by level;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapterid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                array[count] = rs.getInt(2);
                count++;
            }
            if (count == 3) {
                int totalNumberOfQuestionsChapter = array[0] + array[1] + array[2];
                if (totalNumberOfQuestionsChapter >= MIN_NUMBER_OF_QUESTION_15 && array[0] >= MIN_NUMBER_OF_EACH_LEVEL_15[0] && array[1] >= MIN_NUMBER_OF_EACH_LEVEL_15[1] && array[2] >= MIN_NUMBER_OF_EACH_LEVEL_15[2]) {
                    return true;
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean checkOption20Questions(String chapterid) {
        int count = 0;
        int array[] = new int[3];
        String sql = "select level, count(level) as no_question_level from question where quiz_id = ? group by level;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapterid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                array[count] = rs.getInt(2);
                count++;
            }
            if (count == 3) {
                int totalNumberOfQuestionsChapter = array[0] + array[1] + array[2];
                if (totalNumberOfQuestionsChapter >= MIN_NUMBER_OF_QUESTION_20
                        && array[0] >= MIN_NUMBER_OF_EACH_LEVEL_20[0]
                        && array[1] >= MIN_NUMBER_OF_EACH_LEVEL_20[1]
                        && array[2] >= MIN_NUMBER_OF_EACH_LEVEL_20[2]) {
                    return true;
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public void addQuiz(String name, String chapterid, String no_question) {
        String sql = """
                     insert into quiz(name,chapter_id,no_question)
                     values(?,?,?)
                     """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, chapterid);
            stm.setString(3, no_question);
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // update Quiz
    public void updateQuiz(String name, String chapterid, String no_question) {
        String sql = "update quiz set name = ?, no_question = ? where chapter_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, no_question);
            stm.setString(3, chapterid);
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // check chapter da co quiz chua?
    public boolean checkQuizExitsByChapterID(String chapterId) {
        String sql = "select * from quiz where chapter_id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapterId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public Quiz getQuizByChapterID(String chapterId) {
        String sql = "select * from quiz where chapter_id =?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapterId);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                Quiz q = new Quiz();
                q.setQuiz_id(rs.getString(1));
                q.setChapter_id(rs.getString(3));
                q.setName(rs.getString(2));
                q.setNo_question(rs.getString(4));
                return q;
            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public void deleteQuiz(String quizid) {
        String sql = "delete from quiz where chapter_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, quizid);
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // get random Question for quiz
    public List<Question> getRandomQuestion(String chapter_id, int no_question) {
        List<Question> list = new ArrayList<>();

        try {
            for (int i = 1; i <= 3; i++) {
                String sql = "SELECT TOP " + (PERCENT[i - 1] * no_question / 100) + " question_id, content, option1, option2, option3, option4, answer, subject_id, quiz_id, level "
                        + "FROM question "
                        + "WHERE quiz_id = ? AND level = ? "
                        + "ORDER BY NEWID()";

                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, chapter_id); // ID chương
                stm.setInt(2, i); // Cấp độ
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
                    q.setLevel(rs.getString("level"));
                    list.add(q);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return list;
    }

    public List<Quiz> getAllQuizByCourseID(String course_id) {
        List<Quiz> list = new ArrayList<>();
        String sql = """
                     select q.* from quiz q 
                     join chapter ch on q.chapter_id = ch.chapter_id 
                     join course c on ch.course_id = c.course_id
                     where c.course_id = ?
                     """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, course_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setQuiz_id(rs.getString(1));
                q.setName(rs.getString(2));
                q.setChapter_id(rs.getString(3));
                q.setNo_question(rs.getString(4));
                list.add(q);

            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public boolean CheckLearnerTakeQuiz(String quiz_id, String learner_id) {
        String sql = """
                     select id from quiz_result where quiz_id = ? and learner_id = ?
                     """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, quiz_id);
            stm.setString(2, learner_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    public QuizResult getQuizResult(String quiz_id, String learner_id) {

        String sql = """
                     select * from quiz_result where quiz_id = ? and learner_id = ?
                     """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, quiz_id);
            stm.setString(2, learner_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                QuizResult qr = new QuizResult();
                qr.setId(rs.getString(1));
                qr.setLearner_id(rs.getString(2));
                qr.setQuiz_id(rs.getString(3));
                qr.setMark(rs.getString(4));
                qr.setStatus(rs.getString(5));
                return qr;
            }

        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

    public void insertQuizResult(String learner_id, String quiz_id, String mark, String status) {
        String sql = """
                    insert into quiz_result(learner_id, quiz_id, mark, status)
                    values(?,?,?,?)
                    """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, learner_id);
            stm.setString(2, quiz_id);
            stm.setString(3, mark);
            stm.setString(4, status);
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateQuizResult(String mark, String status, String id) {
        String sql = """
                     update quiz_result set
                     mark = ?,
                     status = ?
                     where id = ?
                     """;

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, mark);
            stm.setString(2, status);
            stm.setString(3, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean isQuizMarkGreaterThanFive(String chapter_id) {
        String sql = "SELECT COUNT(*) FROM quiz q "
                + "JOIN quiz_result qr ON q.quiz_id = qr.quiz_id "
                + "WHERE qr.mark >= 5 and q.chapter_id=? ";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, chapter_id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    // Get all questions by quiz ID 
    public List<Question> getQuestionsByQuizId(String quizId) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM question WHERE quiz_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, quizId);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Question question = new Question();
                    question.setQuestion_id(rs.getString("question_id"));
                    question.setContent(rs.getString("content"));
                    question.setOption1(rs.getString("option1"));
                    question.setOption2(rs.getString("option2"));
                    question.setOption3(rs.getString("option3"));
                    question.setOption4(rs.getString("option4"));
                    question.setAnswer(rs.getString("answer"));
                    question.setSubject_id(rs.getString("subject_id"));
                    question.setQuiz_id(rs.getString("quiz_id"));
                    question.setLevel(rs.getString("level"));
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return questions;
    }    // Get learner's answer for a specific question in a quiz  

    public String getLearnerAnswer(String quizId, String learnerId, String questionId) {
        String learnerAnswer = null;
        String sql = "SELECT learner_ans FROM learner_answer WHERE quiz_id = ? AND learner_id = ? AND question_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, quizId);
            stm.setString(2, learnerId);
            stm.setString(3, questionId);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    learnerAnswer = rs.getString("learner_ans");
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return learnerAnswer;
    }

    public List<QuizResult> getQuizResultsByLearnerIdAndCourseId(String learner_id, String course_id) {
        List<QuizResult> quizResults = new ArrayList<>();
        String sql = "SELECT qr.*, q.name AS quiz_name, q.no_question, c.chapter_id, c.chapter_name " + "FROM quiz_result qr " + "JOIN quiz q ON qr.quiz_id = q.quiz_id " + "JOIN chapter c ON q.chapter_id = c.chapter_id " + "WHERE qr.learner_id = ? AND c.course_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, learner_id);
            stm.setString(2, course_id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    QuizResult quizResult = new QuizResult();
                    quizResult.setId(rs.getString("id"));
                    quizResult.setLearner_id(rs.getString("learner_id"));
                    quizResult.setQuiz_id(rs.getString("quiz_id"));
                    quizResult.setMark(rs.getString("mark"));
                    quizResult.setStatus(rs.getString("status"));
                    quizResult.setQuiz_name(rs.getString("quiz_name"));
                    quizResult.setNo_question(rs.getString("no_question"));
                    quizResult.setChapter_id(rs.getString("chapter_id"));
                    quizResult.setChapter_name(rs.getString("chapter_name"));
                    quizResults.add(quizResult);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return quizResults;
    }

    public int getCompletedChaptersCount(String learner_id, String course_id) {
        int completedChaptersCount = 0;
        String sql = "SELECT COUNT(DISTINCT c.chapter_id) " + "FROM quiz_result qr " + "JOIN quiz q ON qr.quiz_id = q.quiz_id " + "JOIN chapter c ON q.chapter_id = c.chapter_id " + "WHERE qr.learner_id = ? AND c.course_id = ? AND qr.status = 1";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, learner_id);
            stm.setString(2, course_id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    completedChaptersCount = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return completedChaptersCount;
    }

    public int getTotalChaptersCount(String course_id) {
        int totalChaptersCount = 0;
        String sql = "SELECT COUNT(*) FROM chapter WHERE course_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, course_id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    totalChaptersCount = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return totalChaptersCount;
    }

    public List<QuizResult> getAllQuizResultsByCourseId(String course_id) {
        List<QuizResult> quizResults = new ArrayList<>();
        String sql = "SELECT qr.*, q.name AS quiz_name, q.no_question, c.chapter_id, c.chapter_name, a.username, a.fullname, a.email " + "FROM quiz_result qr " + "JOIN quiz q ON qr.quiz_id = q.quiz_id " + "JOIN chapter c ON q.chapter_id = c.chapter_id " + "JOIN account a ON qr.learner_id = a.account_id " + "WHERE c.course_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, course_id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    QuizResult quizResult = new QuizResult();
                    quizResult.setId(rs.getString("id"));
                    quizResult.setLearner_id(rs.getString("learner_id"));
                    quizResult.setQuiz_id(rs.getString("quiz_id"));
                    quizResult.setMark(rs.getString("mark"));
                    quizResult.setStatus(rs.getString("status"));
                    quizResult.setQuiz_name(rs.getString("quiz_name"));
                    quizResult.setNo_question(rs.getString("no_question"));
                    quizResult.setChapter_id(rs.getString("chapter_id"));
                    quizResult.setChapter_name(rs.getString("chapter_name"));
                    quizResult.setUsername(rs.getString("username"));
                    quizResult.setFullname(rs.getString("fullname"));
                    quizResult.setEmail(rs.getString("email"));
                    quizResults.add(quizResult);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return quizResults;
    }
    

    public static void main(String[] args) {
        QuizDAO qd = new QuizDAO();
        qd.insertQuizResult("2", "1", "1", "1");
    }
}


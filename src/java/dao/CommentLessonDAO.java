/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.CommentLesson;

/**
 *
 * @author TRUONG GIANG
 */
public class CommentLessonDAO extends DBContext {

//    private Connection connection;
//
//    public CommentLessonDAO(Connection connection) {
//        this.connection = connection;
//    }
    public List<CommentLesson> getCommentByLessonId(int lessonId) throws SQLException {
        List<CommentLesson> commentLessons = new ArrayList<>();
        String sql = "SELECT cl.comment_id, cl.account_id, cl.lesson_id, cl.comment, cl.comment_date, cl.status, cl.parent_comment_id "
                + "FROM comment_lesson cl "
                + "WHERE cl.lesson_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, lessonId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int commentId = resultSet.getInt("comment_id");
                    int accountId = resultSet.getInt("account_id");
                    int lessonId1 = resultSet.getInt("lesson_id");
                    String comment = resultSet.getString("comment");
                    Date commentDate = resultSet.getDate("comment_date");
                    int status = resultSet.getInt("status");
                    Integer parentCommentId = resultSet.getInt("parent_comment_id");
                    CommentLesson commentLesson = new CommentLesson(commentId, accountId, lessonId1, comment, commentDate, status, parentCommentId);
                    commentLessons.add(commentLesson);
                }
            }
        }
        return commentLessons;
    }

    public void updateComment(int accountId, int lessonId, String comment, Date commentDate) throws SQLException {
        String sql = "UPDATE comment_lesson "
                + "SET comment = ?, comment_date = ? "
                + "WHERE account_id = ? AND lesson_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, comment);
            statement.setDate(2, new java.sql.Date(commentDate.getTime()));
            statement.setInt(3, accountId);
            statement.setInt(4, lessonId);
            statement.executeUpdate();
        }
    }

    public void insertComment(CommentLesson commentLesson) throws SQLException {
        String sql = "INSERT INTO comment_lesson (account_id, lesson_id, comment, comment_date, status, parent_comment_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, commentLesson.getAccountId());
            statement.setInt(2, commentLesson.getLessonId());
            statement.setString(3, commentLesson.getComment());
            statement.setDate(4, new java.sql.Date(commentLesson.getCommentDate().getTime()));
            statement.setInt(5, commentLesson.getStatus());
            statement.setInt(6, commentLesson.getParentCommentId());
            statement.executeUpdate();
        }
    }

    public ArrayList<CommentLesson> selectAllCommentLesson(String lesson_id) {
        ArrayList<CommentLesson> data = new ArrayList<>();
        String sql = "SELECT cl.comment_id, cl.account_id, cl.lesson_id, cl.comment, cl.comment_date, a.fullname \n"
                + "                FROM comment_lesson cl \n"
                + "                JOIN account a ON a.account_id = cl.account_id \n"
                + "                WHERE cl.lesson_id = ? AND cl.status != 2 And cl.parent_comment_id IS NULL \n"
                + "                ORDER BY cl.comment_date DESC\n";

//        1 là ch reply and report 
//                2 là bij report 
//                        3 đã 
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(lesson_id));

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int comment_id = rs.getInt(1);
                    int account_id = rs.getInt(2);
                    String comment = rs.getString(4);
                    Date comment_date = rs.getDate(5);
                    String fullname = rs.getString(6);
                    CommentLesson commentLesson = new CommentLesson(comment_id, account_id, Integer.parseInt(lesson_id), comment, comment_date, fullname);
                    data.add(commentLesson);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<CommentLesson> selectAllCommentLessonISParent(String lesson_id) {
        ArrayList<CommentLesson> data = new ArrayList<>();
        String sql = "SELECT cl.comment_id, cl.account_id, cl.lesson_id, cl.comment, cl.comment_date, a.fullname, cl.parent_comment_id \n"
                + "                FROM comment_lesson cl \n"
                + "                JOIN account a ON a.account_id = cl.account_id \n"
                + "                WHERE cl.lesson_id = ? AND cl.status != 2 And cl.parent_comment_id IS NOT NULL \n";

//        1 là ch reply and report 
//                2 là bij report 
//                        3 đã 
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(lesson_id));

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int comment_id = rs.getInt(1);
                    int account_id = rs.getInt(2);
                    String comment = rs.getString(4);
                    Date comment_date = rs.getDate(5);
                    String fullname = rs.getString(6);
                    Integer parent_comment_id = rs.getInt(7);
                    CommentLesson commentLesson = new CommentLesson(comment_id, account_id, Integer.parseInt(lesson_id), comment, comment_date, fullname, parent_comment_id);
                    data.add(commentLesson);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean insertCommentLesson(CommentLesson commentLesson) {
        boolean check = false;
        String sql = "INSERT INTO comment_lesson (account_id, lesson_id, comment, comment_date, status,parent_comment_id) "
                + "VALUES (?, ?, ?, ?, ?,?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, commentLesson.getAccountId());
            stm.setInt(2, commentLesson.getLessonId());
            stm.setString(3, commentLesson.getComment());
            stm.setDate(4, new java.sql.Date(commentLesson.getCommentDate().getTime()));
            stm.setString(5, "1");
            if (commentLesson.getParentCommentId() == null) {
                stm.setNull(6, java.sql.Types.INTEGER);
            } else {
                stm.setInt(6, commentLesson.getParentCommentId());
            }
            int number = stm.executeUpdate();
            check = number > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean updateStatusComment(int status, String comment_id) {
        boolean check = false;
        String sql = "UPDATE comment_lesson SET status = ? WHERE comment_id = ?";

        try {
            int commentIdInt = Integer.parseInt(comment_id);

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, status);
                stm.setInt(2, commentIdInt);

                int rowsAffected = stm.executeUpdate();
                check = rowsAffected > 0;

                System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (NumberFormatException e) {
            System.err.println("comment_id không phải là số hợp lệ: " + comment_id);
        } catch (SQLException e) {
            System.err.println("Lỗi khi thực hiện cập nhật: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

    public boolean hasReplies(int comment_id) {
        String query = "SELECT COUNT(*) FROM comment_lesson WHERE parent_comment_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, comment_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeCommentByCommentID(String comment_id) {
        String sql = "DELETE FROM comment_lesson WHERE comment_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(comment_id));
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateCommentLesson(CommentLesson commentLesson) {
        boolean check = false;
        String sql = "UPDATE comment_lesson SET comment = ? WHERE comment_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, commentLesson.getComment());
            stm.setInt(2, commentLesson.getCommentId());
            int number = stm.executeUpdate();
            check = number > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public CommentLesson selectCommentByComment_id(int comment_id) {
        CommentLesson commentLesson = null;
        String sql = "SELECT cl.comment_id, cl.account_id, cl.lesson_id, cl.comment, cl.comment_date, a.fullname "
                + "FROM comment_lesson cl "
                + "JOIN account a ON a.account_id = cl.account_id "
                + "WHERE cl.comment_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, comment_id);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    int account_id = rs.getInt(2);
                    int lesson_id = rs.getInt(3);
                    String comment = rs.getString(4);
                    Date comment_date = rs.getDate(5);
                    String fullname = rs.getString(6);
                    commentLesson = new CommentLesson(comment_id, account_id, lesson_id, comment, comment_date, fullname);
                } else {
                    System.out.println("No comment found with ID: " + comment_id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid comment ID format: " + comment_id);
        }
        return commentLesson;
    }

    public void reportCommentLesson(String comment_id) {
        String sql = "UPDATE comment_lesson SET status = 2 WHERE comment_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, Integer.parseInt(comment_id));
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<CommentLesson> selectAllCommentLessonBySubject_id(String subject_id, int page, int limit) {
        ArrayList<CommentLesson> data = new ArrayList<>();
        String sql = "SELECT cl.comment_id, cl.account_id, cl.lesson_id, cl.comment, cl.comment_date, a.fullname "
                + "FROM comment_lesson cl "
                + "JOIN account a ON a.account_id = cl.account_id "
                + "JOIN lesson l ON l.lesson_id = cl.lesson_id "
                + "JOIN chapter ch ON ch.chapter_id = l.chapter_id "
                + "JOIN course c ON c.course_id = ch.course_id "
                + "JOIN subject s ON s.subject_id = c.subject_id "
                + "WHERE s.subject_id = ? AND cl.status = 1 And cl.parent_comment_id IS NULL "
                + "ORDER BY cl.comment_date DESC "
                + "OFFSET (?) ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(subject_id));
            stm.setInt(2, page);
            stm.setInt(3, limit);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int comment_id = rs.getInt(1);
                    int account_id = rs.getInt(2);
                    int lesson_id = rs.getInt(3);
                    String comment = rs.getString(4);
                    Date comment_date = rs.getDate(5);
                    String fullname = rs.getString(6);
                    CommentLesson commentLesson = new CommentLesson(comment_id, account_id, lesson_id, comment, comment_date, fullname);
                    data.add(commentLesson);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public ArrayList<CommentLesson> selectAllCommentsWithReplies(String subjectId, int lecturer_id, int offset, int limit) {
        ArrayList<CommentLesson> data = new ArrayList<>();
        String sql = "SELECT cl.comment_id, cl.account_id, cl.lesson_id, cl.comment, cl.comment_date, a.fullname, cl.parent_comment_id \n"
                + "                FROM comment_lesson cl \n"
                + "                JOIN account a ON a.account_id = cl.account_id \n"
                + "                JOIN lesson l ON l.lesson_id = cl.lesson_id \n"
                + "                JOIN chapter ch ON ch.chapter_id = l.chapter_id \n"
                + "                JOIN course c ON c.course_id = ch.course_id \n"
                + "                JOIN subject s ON s.subject_id = c.subject_id \n"
                + "                WHERE s.subject_id = ? AND cl.status = 1 And cl.parent_comment_id IS NOT NULL and cl.account_id = ? \n"
                + "                ORDER BY cl.comment_date DESC OFFSET (?) ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, subjectId);
            stm.setInt(2, lecturer_id);
            stm.setInt(3, offset);
            stm.setInt(4, limit);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int comment_id = rs.getInt(1);
                    int lesson_id = rs.getInt(3);
                    String reply = rs.getString(4);
                    Date comment_date = rs.getDate(5);
                    String fullname = rs.getString(6);
                    int parent_comment_id = rs.getInt(7);
                    CommentLesson commentLesson = new CommentLesson(comment_id, lecturer_id, lesson_id, "", comment_date, fullname, parent_comment_id);
                    commentLesson.setReply(reply);
                    data.add(commentLesson);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
     public int getNoOfCommentsRepliedBySubject(String subjectId,int lecturer_id) {
        int count = 0;
        String sql = "SELECT Count(cl.comment_id) \n"
                + "                FROM comment_lesson cl \n"
                + "                JOIN account a ON a.account_id = cl.account_id \n"
                + "                JOIN lesson l ON l.lesson_id = cl.lesson_id \n"
                + "                JOIN chapter ch ON ch.chapter_id = l.chapter_id \n"
                + "                JOIN course c ON c.course_id = ch.course_id \n"
                + "                JOIN subject s ON s.subject_id = c.subject_id \n"
                + "                WHERE s.subject_id = ? AND cl.status = 1 And cl.parent_comment_id IS NOT NULL and cl.account_id = ? \n";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, subjectId);
            stm.setInt(2, lecturer_id);

            try (ResultSet rs = stm.executeQuery()) {
                 if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public int getNoOfCommentsBySubject(String subject_id) {
        int count = 0;
        String sql = "SELECT Count(cl.comment_id) FROM comment_lesson cl\n"
                + "			Join lesson l on l.lesson_id = cl.lesson_id\n"
                + "                Join Chapter ch on ch.chapter_id = l.chapter_id\n"
                + "                join Course c on c.course_id = ch.course_id\n"
                + "                join subject s on s.subject_id = c.subject_id \n"
                + "                WHERE s.subject_id = ? and cl.status = 1 And cl.parent_comment_id IS NULL";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(subject_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public String selectRepliesByParentId(int comment_id) {
        String replies = "";
        String sql = "SELECT  cl.comment "
                + "FROM comment_lesson cl "
                + "JOIN account a ON a.account_id = cl.account_id "
                + "WHERE cl.comment_id = ? ORDER BY cl.comment_date ASC";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, comment_id);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    replies = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return replies;
    }
}

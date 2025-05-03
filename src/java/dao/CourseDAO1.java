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
import java.util.List;
import model.Course;

/**
 *
 * @author Admin
 */
public class CourseDAO1 extends DBContext {

    public CourseDAO1() {
        connectDB();
    }
    Connection cnn;// Kết nối DB
    PreparedStatement stm; // Thực hiện các câu lệnh sql
    ResultSet rs;

    private void connectDB() {
        cnn = connection;
        if (cnn != null) {
            System.out.println("Connect success");
        } else {
            System.out.println("Not success");
        }
    }

    public ArrayList<Course> selectAllCourseBySubject_id(String subject_id) {
        ArrayList<Course> data = new ArrayList();
        try {
            String sql = "Select c.course_id,c.course_name,c.image,c.subject_id,c.course_no,c.description\n"
                    + "                    From Course c "
                    + "                    Join Subject s on c.subject_id = s.subject_id\n"
                    + "                    where c.subject_id = ?";
            stm = cnn.prepareStatement(sql);
            stm.setString(1, subject_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String course_id = rs.getString(1);
                String course_name = rs.getString(2);
                String image = rs.getString(3);
                String course_no = rs.getInt(5) + "";
                String description = rs.getString(6);
                String rate = calculateRateCourse(course_id)+"";
                Course course = new Course(course_id, course_name, image, subject_id, course_no, description, rate);
                data.add(course);
            }
        } catch (Exception e) {
        }
        return data;
    }

    public Course selectCourseByCourse_id(String course_id) {
        Course course = new Course();
        try {
            String sql = "Select c.course_id,c.course_name,c.image,c.subject_id,c.course_no,c.description\n"
                    + "From Course c \n"
                    + "where c.course_id = ?";
            stm = cnn.prepareStatement(sql);
            stm.setString(1, course_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String course_name = rs.getString(2);
                String image = rs.getString(3);
                String subject_id = rs.getInt(4) + "";
                String course_no = rs.getInt(5) + "";
                String description = rs.getString(6);
                course = new Course(course_id, course_name, image, subject_id, course_no, description);
            }
        } catch (Exception e) {
        }
        return course;
    }

    public ArrayList<Course> searchCourseByName(String search_course) {
        String name = "%" + search_course + "%";
        ArrayList<Course> data = new ArrayList();
        try {
            String sql = "Select c.course_id,c.course_name,c.image,c.subject_id,c.course_no,c.description\n"
                    + "                    From Course c \n"
                    + "                    where c.course_name like ?";
            stm = cnn.prepareStatement(sql);
            stm.setString(1, name);
            rs = stm.executeQuery();
            while (rs.next()) {
                String course_id = rs.getString(1);
                String course_name = rs.getString(2);
                String image = rs.getString(3);
                String subject_id = rs.getString(4);
                String course_no = rs.getInt(5) + "";
                String description = rs.getString(6);
                Course course = new Course(course_id, course_name, image, subject_id, course_no, description);
                data.add(course);
            }
        } catch (Exception e) {
        }
        return data;

    }

    public int SelectMaxCourseNoBySubject_id(String subject_id) {
        int max = 0;
        try {
            String sql = "Select Max(c.course_no)\n"
                    + "from course c\n"
                    + "where c.subject_id = ?";
            stm = cnn.prepareStatement(sql);
            stm.setString(1, subject_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                max = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return max;

    }

    public void DeleteCourseByCourse_id(String course_id) {
        try {
            String sql = "DELETE FROM course\n"
                    + "WHERE course_id = ?;";
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(course_id));
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertCourse(String course_name, String course_no, String description, String subject_id) {
        try {
            String sql = "INSERT INTO course (course_name, subject_id, course_no, description) "
                    + "VALUES (?, ?, ?, ?)";
            stm = cnn.prepareStatement(sql);
            stm.setString(1, course_name);
            stm.setInt(2, Integer.parseInt(subject_id));
            stm.setInt(3, Integer.parseInt(course_no));
            stm.setString(4, description);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void update(String course_name, String description, String course_id) {
        try {
            String sql = "Update course  \n"
                    + "                Set course_name = ?, description =  ?"
                    + "                 where course_id = ?";

            stm = cnn.prepareStatement(sql);
            stm.setString(1, course_name);
            stm.setString(2, description);
            stm.setInt(3, Integer.parseInt(course_id));
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public boolean CheckNameExits(String course_name, String subject_id) {
        boolean check = false;
        try {
            String sql = "Select c.course_name\n"
                    + "  from course c \n"
                    + " where c.subject_id = ? and c.course_name = ?";

            stm = cnn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(subject_id));
            stm.setString(2, course_name);
            rs = stm.executeQuery();
            while (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
        }
        return check;
    }

    public boolean CheckNameExitsUpdate(String course_name, String subject_id, String course_id) {
        boolean check = false;
        try {
            String sql = "Select c.course_name\n"
                    + "                                       from course c \n"
                    + "               where c.subject_id = ? and c.course_name = ? and c.course_id != ? ";

            stm = cnn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(subject_id));
            stm.setString(2, course_name);
            stm.setInt(3, Integer.parseInt(course_id));
            rs = stm.executeQuery();
            while (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
        }
        return check;
    }

    public int CountAllCourseBySubject(String subject_id) {
        int count = 0;
        try {
            String sql = "Select count(c.course_id)\n"
                    + "                    from subject s\n"
                    + "                    join Course c on c.subject_id = s.subject_id\n"
                    + "                    where s.subject_id =?";
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(subject_id));
            rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    public ArrayList<Course> selectAllCourseBySubject_idAndCourse_id(String subject_id, String course_id) {
        ArrayList<Course> data = new ArrayList();
        try {
            String sql = "Select c.course_id,c.course_name,c.image,c.subject_id,c.course_no,c.description\n"
                    + "                    From Course c \n"
                    + "                    Join Subject s on c.subject_id = s.subject_id\n"
                    + "                    where c.subject_id = ? and not c.course_id = ?";
            stm = cnn.prepareStatement(sql);
            stm.setString(1, subject_id);
            stm.setString(2, course_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String course_id_new = rs.getInt(1) + "";
                String course_name = rs.getString(2);
                String image = rs.getString(3);
                String course_no = rs.getInt(5) + "";
                String description = rs.getString(6);
                Course course = new Course(course_id_new, course_name, image, subject_id, course_no, description);
                data.add(course);
            }
        } catch (Exception e) {
        }
        return data;
    }

    private int calculateRateCourse(String course_id) {
        int rate = 0;

        String sql = "SELECT   SUM(lc.rate) / COUNT(lc.rate) as ratesubject\n"
                + "FROM learner_course lc \n"
                + "WHERE lc.rate != 0 and lc.course_id = ?\n"
                + "GROUP BY lc.course_id;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(course_id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rate = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return rate;
    }
}

package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Course;



public class CourseDAO extends DBContext {
    
    
    public List<String> getCompletedCoursesByLearnerId(int learnerId) {
        List<String> completedCourses = new ArrayList<>();
        String sql = "SELECT c.course_name "
                + "FROM learner_course lc "
                + "JOIN course c ON lc.course_id = c.course_id "
                + "WHERE lc.status_id = 2 "
                + // 2 represents 'completed' status
                "AND lc.learner_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, learnerId);  // Set the learner_id parameter
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                completedCourses.add(rs.getString("course_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Log the exception for debugging
        }

        return completedCourses;
    }

    public List<Course> getCoursesBySubjectId(String subjectId, String nameSearch) {
        List<Course> courses = new ArrayList<>();
        try {
            String query = "SELECT * FROM Course WHERE subject_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, subjectId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                // Lấy các thông tin từ ResultSet và gán vào đối tượng Course
                course.setCourse_id(rs.getString("course_id"));
                course.setCourse_name(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                course.setSubject_id(rs.getString("subject_id"));
                // Thêm course vào danh sách
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> getAllCourse(String name) {
        List<Course> courses = new ArrayList<>();
        String query = "select C.*, S.subject_name from Course C\n"
                + "left join Subject S on C.subject_id = S.subject_id\n"
                + "where C.course_name like ?"; // Câu truy vấn SQL
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourse_id(rs.getString("course_id")); // Đảm bảo sử dụng tên cột chính xác
                course.setCourse_name(rs.getString("course_name"));
                course.setImage(rs.getString("image")); // Thêm nếu cần
                course.setCourse_no(rs.getString("course_no"));
                course.setDescription(rs.getString("description")); // Thêm nếu cần
                course.setSubject_name(rs.getString("subject_name"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("errror" + e.getMessage());
        }
        return courses;
    }
    
    public boolean hasAssignmentForCourse(String courseid){
        String sql = " select * from assignment where course_id = "  +courseid;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return true;
        } catch (SQLException e) {
        }
        return false;
    }
    public Course getCourseById(int id) {
        Course c = null;
        String strSql = "select C.*, S.subject_name from Course C\n"
                + "left join Subject S on C.subject_id = S.subject_id\n"
                + "WHERE course_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(strSql)) {
            stm.setInt(1, id);
            ResultSet resultSet = stm.executeQuery();

            if (resultSet.next()) {
                c = new Course(
                        resultSet.getString("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getString("image"),
                        resultSet.getString("subject_id"),
                        resultSet.getString("course_no"),
                        resultSet.getString("description"),
                        resultSet.getString("subject_name")
                );
                c.setStatus(resultSet.getBoolean("status"));
            }
        } catch (SQLException e) {
            System.out.println("getCourseById: " + e.getMessage());
        }

        return c;
    }

    public List<Course> selectTop4CourseBySubject_id(String subject_id) {
        List<Course> data = new ArrayList<>();
        String sql = "select top 4 C.*, S.subject_name from Course C\n"
                + "left join Subject S on C.subject_id = S.subject_id\n"
                + "WHERE C.subject_id = ?"; // chạy thử câu lệnh này trên db chưa ? quên mịa rồi để chạy lại thử

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, subject_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourse_id(rs.getString("course_id"));
                course.setCourse_name(rs.getString("course_name"));
                course.setImage(rs.getString("image"));
                course.setSubject_id(rs.getString("subject_id"));
                course.setCourse_no(rs.getString("course_no"));
                course.setDescription(rs.getString("description"));
                course.setSubject_name(rs.getString("subject_name"));
                data.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public Course selectCourseByCourse_id(String course_id) {
        Course course = new Course();
        try {
            String sql = "Select c.course_id,c.course_name,c.image,c.subject_id,c.course_no,c.description\n"
                    + "From Course c \n"
                    + "where c.course_id = ?";
            PreparedStatement stm;
            stm = connection.prepareStatement(sql);
            stm.setString(1, course_id);
            ResultSet rs = stm.executeQuery();
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

    public List<Course> getCourseBySubjectId(String subject_id) {
        List<Course> data = new ArrayList<>();
        String sql = "select C.*, S.subject_name from Course C\n"
                + "left join Subject S on C.subject_id = S.subject_id\n"
                + "WHERE C.subject_id = ?"; // chạy thử câu lệnh này trên db chưa ? quên mịa rồi để chạy lại thử

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, subject_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourse_id(rs.getString("course_id"));
                course.setCourse_name(rs.getString("course_name"));
                course.setImage(rs.getString("image"));
                course.setSubject_id(rs.getString("subject_id"));
                course.setCourse_no(rs.getString("course_no"));
                course.setDescription(rs.getString("description"));
                course.setSubject_name(rs.getString("subject_name"));
                course.setStatus(rs.getBoolean("status"));
                data.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void createCourse(Course c) {
        String sql = "INSERT INTO course(course_name, image, subject_id, course_no, description, status)\n"
                + "values(?,?,?,?,?,1);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getCourse_name());
            st.setString(2, c.getImage());
            st.setString(3, c.getSubject_id());
            st.setString(4, c.getCourse_no());
            st.setString(5, c.getDescription());
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateCourse(Course c) {
        String sql = "update course set course_name = ?, image = ?, course_no = ?, description = ?, status = ? "
                + "where course_id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getCourse_name());
            st.setString(2, c.getImage());
            st.setString(3, c.getCourse_no());
            st.setString(4, c.getDescription());
            st.setBoolean(5, c.isStatus());
            st.setString(6, c.getCourse_id());
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean deleteCourse(String id) {
        String sql = "UPDATE course SET status = 0 WHERE course_id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            int rowsAffected = st.executeUpdate(); // Thực hiện truy vấn và lấy số dòng bị ảnh hưởng
            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (Exception e) {
            System.out.println(e);
            return false; // Trả về false nếu có lỗi
        }
    }

   public boolean isCourseNoExist(String courseNo) {
    String query = "SELECT COUNT(*) FROM Course WHERE courseNo = ?";
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, courseNo);
        ResultSet rs = ps.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            return true; // courseNo đã tồn tại
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

}

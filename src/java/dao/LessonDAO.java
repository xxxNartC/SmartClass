package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Lesson;

/**
 *
 * @author ACER
 */
public class LessonDAO extends DBContext {

    public List<Lesson> getLessonsByLessonName(String lessonName) {
        List<Lesson> lessons = new ArrayList<>();
        String sql = """
                 SELECT lesson_id, lesson_no, lesson_name, description, video, document ,chapter_id
                 FROM Lesson
                 WHERE lesson_name = ?
                 """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, lessonName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String lessonNo = rs.getString("lesson_no");
                String lessonNameResult = rs.getString("lesson_name");
                String description = rs.getString("description");
                String video = rs.getString("video");
                String document = rs.getString("document");

                Lesson lesson = new Lesson();
                lesson.setLesson_id(rs.getString("lesson_id"));
                lesson.setChapter_id(rs.getString("chapter_id"));
                lesson.setLesson_no(lessonNo);
                lesson.setLesson_name(lessonNameResult);
                lesson.setDescription(description);
                lesson.setVideo(video);
                lesson.setDocument(document);

                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

  public void insertLesson(String chapter_id, String lesson_no, String lesson_name, String video, String document, String description) {
        try {
            String sql = "INSERT INTO Lesson(lesson_no, lesson_name, video, document, description, chapter_id) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lesson_no);
            stm.setString(2, lesson_name);
            stm.setString(3, video);
            stm.setString(4, document);
            stm.setString(5, description);
            stm.setString(6, chapter_id);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public boolean updateLesson(String lesson_id, String lesson_no, String lesson_name, String video, String document, String description, String chapter_id) {
        try {
            // Thực hiện truy vấn SQL để cập nhật chương
            String sql = "UPDATE Lesson SET lesson_no = ?, lesson_name = ?, video = ?, document = ?, description = ?, chapter_id = ? WHERE lesson_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, lesson_no);
            ps.setString(2, lesson_name);
            ps.setString(3, video);
            ps.setString(4, document);
            ps.setString(5, description);
            ps.setString(6, chapter_id);
            ps.setString(7, lesson_id);

            // Kiểm tra xem cập nhật có thành công hay không
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một hàng được cập nhật
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
    }

    public void deleteLesson(String lesson_id) {
        try {
            String sql = "DELETE FROM Lesson WHERE lesson_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lesson_id);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }
    public List<Lesson> getLessonsById(String id) {
        List<Lesson> lessons = new ArrayList<>();
        String sql = """
                 SELECT lesson_id, lesson_no, lesson_name, description, video, document ,chapter_id
                 FROM Lesson
                 WHERE lesson_id = ?
                 """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String lessonNo = rs.getString("lesson_no");
                String lessonNameResult = rs.getString("lesson_name");
                String description = rs.getString("description");
                String video = rs.getString("video");
                String document = rs.getString("document");

                Lesson lesson = new Lesson();
                lesson.setLesson_id(rs.getString("lesson_id"));
                lesson.setChapter_id(rs.getString("chapter_id"));
                lesson.setLesson_no(lessonNo);
                lesson.setLesson_name(lessonNameResult);
                lesson.setDescription(description);
                lesson.setVideo(video);
                lesson.setDocument(document);

                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public ArrayList<Lesson> selectAllLessonByChapter_id(String chapter_id) {
        ArrayList<Lesson> data = new ArrayList();
        try {
            String sql = """
                         Select lesson_id,lesson_no,lesson_name,video,document,description,chapter_id
                           from Lesson
                           where chapter_id = ?""";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapter_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String lesson_id = rs.getInt(1) + "";
                String lesson_no = rs.getInt(2) + "";
                String lesson_name = rs.getString(3);
                String video = rs.getString(4);
                String document = rs.getString(5);
                String description = rs.getString(6);
                Lesson lesson = new Lesson(lesson_id, lesson_no, lesson_name, video, document, chapter_id, description);
                data.add(lesson);
            }
        } catch (Exception e) {
        }
        return data;
    }





    public Lesson getLessonById(String lesson_id) {
        Lesson lesson = new Lesson();
        try {
            String sql = "SELECT * FROM Lesson WHERE lesson_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lesson_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                lesson.setLesson_id(rs.getString("lesson_id"));
                lesson.setLesson_no(rs.getString("lesson_no"));
                lesson.setLesson_name(rs.getString("lesson_name"));
                lesson.setVideo(rs.getString("video"));
                lesson.setDocument(rs.getString("document"));
                lesson.setDescription(rs.getString("description"));
                lesson.setChapter_id(rs.getString("chapter_id"));
            }
        } catch (Exception e) {
        }
        return lesson;
    }

    public boolean checkLessonNo(String chapter_id, String lesson_no) {
        try (
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Lesson WHERE chapter_id = ? AND lesson_no = ?")) {
            ps.setString(1, chapter_id);
            ps.setString(2, lesson_no);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkLessonName(String chapter_id, String lesson_name) {
        try (
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Lesson WHERE chapter_id = ? AND lesson_name = ?")) {
            ps.setString(1, chapter_id);
            ps.setString(2, lesson_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public String getLessonIdByLessonNo(String chapter_id, String lesson_no) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT lesson_id FROM Lesson WHERE chapter_id = ? AND lesson_no = ?")) {
            ps.setString(1, chapter_id);
            ps.setString(2, lesson_no);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("lesson_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String getLessonIdByLessonName(String chapter_id, String lesson_name) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT lesson_id FROM Lesson WHERE chapter_id = ? AND lesson_name = ?")) {
            ps.setString(1, chapter_id);
            ps.setString(2, lesson_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("lesson_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Lesson> searchLessonByName(String chapter_id, String lesson_name) {
        ArrayList<Lesson> data = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Lesson WHERE chapter_id = ? AND lesson_name LIKE ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapter_id);
            stm.setString(2, "%" + lesson_name + "%"); // Use wildcard for partial matching     
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String lesson_id = rs.getInt("lesson_id") + "";
                String lesson_no = rs.getInt("lesson_no") + "";
                String lesson_name_result = rs.getString("lesson_name");
                String video = rs.getString("video");
                String document = rs.getString("document");
                String description = rs.getString("description");
                Lesson lesson = new Lesson(lesson_id, lesson_no, lesson_name_result, video, document, chapter_id, description);
                data.add(lesson);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }

    // Check if lesson_no already exists for a given chapter_id, excluding the current lesson_id  
    public boolean checkLessonNoExists(String chapter_id, String lesson_no, String currentLessonId) {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM Lesson WHERE chapter_id = ? AND lesson_no = ? AND lesson_id != ?")) {
            ps.setString(1, chapter_id);
            ps.setString(2, lesson_no);
            ps.setString(3, currentLessonId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }    // Check if lesson_name already exists for a given chapter_id, excluding the current lesson_id  

    public boolean checkLessonNameExists(String chapter_id, String lesson_name, String currentLessonId) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Lesson WHERE chapter_id = ? AND lesson_name = ? AND lesson_id != ?")) {
            ps.setString(1, chapter_id);
            ps.setString(2, lesson_name);
            ps.setString(3, currentLessonId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

     public int getNumPage() {
        String sql = """
                    select count(*) from Lesson
                    """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 4;
                if (total % 4 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    public List<Lesson> getLessonPage(int index) {
        List<Lesson> list = new ArrayList<>();
        String sql = """
                       select * from Lesson l
                       order by l.lesson_id
                       offset ? rows fetch next 4 row only
                       """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 4);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Lesson(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getNumPageByName(String chapter_id, String name) {
        String sql = "select count(*) from Lesson\n"
                + "where Lesson.chapter_id = ? and Lesson.lesson_name like '%" + name + "%' ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapter_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 4;
                if (total % 4 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public List<Lesson> filterLessonPaging(String chapter_id, String searchTxt, int index) {
        List<Lesson> list = new ArrayList<>();

        String sql = "SELECT l.lesson_id, l.lesson_no, l.lesson_name, l.video, l.document, l.chapter_id, l.description\n"
                + "FROM Lesson l\n"
                + "WHERE l.chapter_id = ? AND l.lesson_name LIKE '%" + searchTxt + "%'\n"
                + "ORDER BY l.lesson_id\n"
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, chapter_id);
            stm.setInt(2, (index - 1) * 4);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLesson_id(rs.getString(1));
                l.setLesson_no(rs.getString(2));
                l.setLesson_name(rs.getString(3));
                l.setVideo(rs.getString(4));
                l.setDocument(rs.getString(5));
                l.setChapter_id(rs.getString(6));
                l.setDescription(rs.getString(7));

                list.add(l);

            }
        } catch (SQLException e) {
        }

        return list;
    }
    public List<Lesson> getLessonByChapterID(String chapter_id) {
        List<Lesson> list = new ArrayList<>();
        String sql = """
                     select * from Lesson
                     where chapter_id = ?;
                     """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapter_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Lesson(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public int getNumPageByChapterID(String chapter_id) {
        String sql = "select count(*) from Lesson\n"
                + "where Lesson.chapter_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapter_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 4;
                if (total % 4 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (SQLException e) {
        }
        return 0;
    }
    
    public List<Lesson> getLessonPageByChapterID(String chapter_id, int index) {
        List<Lesson> list = new ArrayList<>();
        String sql = """
                       select * from Lesson l
                       where l.chapter_id = ?
                       order by l.lesson_id
                       offset ? rows fetch next 4 row only
                       """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, chapter_id);
            ps.setInt(2, (index - 1) * 4);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Lesson(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }


}

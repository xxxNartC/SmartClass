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
import model.Chapter;
import model.Course;
import model.Subject;

/**
 *
 * @author ACER
 */
public class ChapterDAO extends DBContext {

    public int getNumPageByName(String name) {
        String sql = "select count(*) from Chapter\n"
                + "where Chapter.chapter_name like '%" + name + "%' ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 6;
                if (total % 6 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public List<Chapter> filterChapterPaging(String searchTxt, int index) {
        List<Chapter> list = new ArrayList<>();

        String sql = "SELECT c.chapter_id, c.chapter_no, c.chapter_name, c.course_id\n"
                + "FROM Chapter c\n"
                + "WHERE c.chapter_name LIKE '%" + searchTxt + "%'\n"
                + "ORDER BY c.chapter_id\n"
                + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, (index - 1) * 6);

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
        }

        return list;
    }

    public ArrayList<Chapter> selectAllChapterByCourse_id(String course_id) {
        ArrayList<Chapter> data = new ArrayList();
        try {
            String sql = "Select chapter_id,chapter_no,chapter_name,course_id, description\n"
                    + "  from Chapter\n"
                    + "  where course_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, course_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String chapter_id = rs.getInt(1) + "";
                String chapter_no = rs.getInt(2) + "";
                String chapter_name = rs.getString(3);
                String description = rs.getString(5);
                Chapter chapter = new Chapter(chapter_id, chapter_no, chapter_name, course_id, description);
                data.add(chapter);
            }
        } catch (Exception e) {
        }
        return data;
    }

    public void insertChapter(String course_id, String chapter_no, String chapter_name, String description) {
        try {
            String sql = "INSERT INTO Chapter(chapter_no, chapter_name, course_id, description) VALUES(?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapter_no);
            stm.setString(2, chapter_name);
            stm.setString(3, course_id);
            stm.setString(4, description);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }
    

    public boolean updateChapter(String chapter_id, String chapter_no, String chapter_name, String course_id, String description) {
        try {
            // Thực hiện truy vấn SQL để cập nhật chương
            String sql = "UPDATE chapter SET chapter_no = ?, chapter_name = ?, description = ? WHERE chapter_id = ? AND course_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, chapter_no);
            ps.setString(2, chapter_name);
            ps.setString(3, description);
            ps.setString(4, chapter_id);
            ps.setString(5, course_id);

            // Kiểm tra xem cập nhật có thành công hay không
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một hàng được cập nhật
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
    }

    public void deleteChapter(String chapter_id) {
        try {
            String sql = "DELETE FROM Chapter WHERE chapter_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapter_id);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Chapter getChapterById(String chapter_id) {
        Chapter chapter = new Chapter();
        try {
            String sql = "SELECT * FROM Chapter WHERE chapter_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapter_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                chapter.setChapter_id(rs.getString("chapter_id"));
                chapter.setChapter_no(rs.getString("chapter_no"));
                chapter.setChapter_name(rs.getString("chapter_name"));
                chapter.setCourse_id(rs.getString("course_id"));
                chapter.setDescription(rs.getString("description"));
            }
        } catch (Exception e) {
        }
        return chapter;
    }

    public boolean checkChapterNo(String course_id, String chapter_no) {
        try (
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Chapter WHERE course_id = ? AND chapter_no = ?")) {
            ps.setString(1, course_id);
            ps.setString(2, chapter_no);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkChapterNo(String course_id, String chapter_no, String chapter_id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Chapter WHERE course_id = ? AND chapter_no = ? AND chapter_id != ?")) {
            ps.setString(1, course_id);
            ps.setString(2, chapter_no);
            ps.setString(3, chapter_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkChapterName(String course_id, String chapter_name) {
        try (
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Chapter WHERE course_id = ? AND chapter_name = ?")) {
            ps.setString(1, course_id);
            ps.setString(2, chapter_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean checkChapterName(String course_id, String chapter_name, String chapter_id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Chapter WHERE course_id = ? AND chapter_name = ? AND chapter_id != ?")) {
            ps.setString(1, course_id);
            ps.setString(2, chapter_name);
            ps.setString(3, chapter_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public String getChapterIdByChapterNo(String course_id, String chapter_no) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT chapter_id FROM Chapter WHERE course_id = ? AND chapter_no = ?")) {
            ps.setString(1, course_id);
            ps.setString(2, chapter_no);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("chapter_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String getChapterIdByChapterName(String course_id, String chapter_name) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT chapter_id FROM Chapter WHERE course_id = ? AND chapter_name = ?")) {
            ps.setString(1, course_id);
            ps.setString(2, chapter_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("chapter_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Chapter> searchChapterByName(String course_id, String chapter_name) {
        ArrayList<Chapter> data = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Chapter WHERE course_id = ? AND chapter_name LIKE ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, course_id);
            stm.setString(2, "%" + chapter_name + "%"); // Use wildcard for partial matching     
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String chapter_id = rs.getInt("chapter_id") + "";
                String chapter_no = rs.getInt("chapter_no") + "";
                String chapter_name_result = rs.getString("chapter_name");
                String description = rs.getString("description");
                Chapter chapter = new Chapter(chapter_id, chapter_no, chapter_name_result, course_id, description);
                data.add(chapter);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }

    public int getNumPage() {
        String sql = """
                    select count(*) from chapter
                    """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 10;
                if (total % 10 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Chapter> getChapterPage(int index) {
        List<Chapter> list = new ArrayList<>();
        String sql = """
                       select * from chapter c
                       order by c.chapter_id
                       offset ? rows fetch next 10 row only
                       """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Chapter(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getNumPageById(String course_id, String id) {
        String sql = "select count(*) from chapter\n"
                + "where chapter.course_id like '%" + id + "%' ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 10;
                if (total % 10 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getNumPageByCourseId(String course_id) {
        String sql = "SELECT COUNT(*) FROM Chapter WHERE course_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, course_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 10;
                if (total % 10 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ArrayList<Chapter> selectAllChapterByCourse_id(String course_id, int page) {
        ArrayList<Chapter> data = new ArrayList();
        try {
            String sql = "SELECT chapter_id,chapter_no,chapter_name,course_id, description\n" + "  from Chapter\n" + "  where course_id = ?\n" + "  ORDER BY chapter_id\n" + "  OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, course_id);
            stm.setInt(2, (page - 1) * 10);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String chapter_id = rs.getInt(1) + "";
                String chapter_no = rs.getInt(2) + "";
                String chapter_name = rs.getString(3);
                String description = rs.getString(5);
                Chapter chapter = new Chapter(chapter_id, chapter_no, chapter_name, course_id, description);
                data.add(chapter);
            }
        } catch (Exception e) {
        }
        return data;
    }

    public ArrayList<Chapter> selectAllChapterByCourse_id(String course_id, int page, String sort) {
        ArrayList<Chapter> data = new ArrayList();
        try {
            String sql = "SELECT chapter_id,chapter_no,chapter_name,course_id, description\n" + "  from Chapter\n" + "  where course_id = ?\n" + "  ORDER BY chapter_no " + sort + "\n" + "  OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, course_id);
            stm.setInt(2, (page - 1) * 10);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String chapter_id = rs.getInt(1) + "";
                String chapter_no = rs.getInt(2) + "";
                String chapter_name = rs.getString(3);
                String description = rs.getString(5);
                Chapter chapter = new Chapter(chapter_id, chapter_no, chapter_name, course_id, description);
                data.add(chapter);
            }
        } catch (Exception e) {
        }
        return data;
    }

}


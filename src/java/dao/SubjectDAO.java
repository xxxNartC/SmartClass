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
import model.Account;
import model.Category;
import model.Subject;

/**
 *
 * @author Admin
 */
public class SubjectDAO extends DBContext {

    public List<String> getCompletedSubjectsByLearnerId(int learnerId) {
        List<String> completedSubjects = new ArrayList<>();
        String sql = "SELECT s.subject_name "
                + "FROM learner_subject ls "
                + "JOIN subject s ON ls.subject_id = s.subject_id "
                + "JOIN status st ON ls.status_id = st.status_id "
                + "WHERE ls.status_id = 2 "
                + // 1 represents 'completed' status
                "AND ls.learner_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, learnerId);  // Set the learner_id parameter
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                completedSubjects.add(rs.getString("subject_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Log the exception for debugging
        }

        return completedSubjects;
    }

    public Subject selectNameAnDesSubject(String subject_id) {
        Subject subject = new Subject();

        String sql = "select subject_name,description \n"
                + "from Subject\n"
                + "where subject_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subject_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String subject_name = rs.getString(1);
                String subject_description = rs.getString(2);
                subject.setSubject_name(subject_name);
                subject.setDescription(subject_description);
            }
        } catch (SQLException e) {
        }
        return subject;
    }

    public List<Subject> getAllSubject() {
        List<Subject> listSubjects = new ArrayList<>();
        String sql = "select * from Subject ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSubjects.add(new Subject(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(10),
                        rs.getString(11)));
            }
        } catch (SQLException e) {
            System.out.println();
        }

        return listSubjects;
    }

    public List<Subject> getSubjectWithLecture() {
        List<Subject> list = new ArrayList<>();

        String sql = """
                     select s.subject_id, s.subject_name,s.description, s.image , s.created_date,s.price, s.discount,s.sold,s.favorites_count,a.fullname 
                     from subject s join account a on a.account_id = s.lecturer_id 
                     where s.status = 1
                     ;""";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Account acc = new Account();

            while (rs.next()) {

                String subject_id = rs.getInt(1) + "";
                String subject_name = rs.getString(2);
                String description = rs.getString(3);
                String image = rs.getString(4);
                String created_date = String.valueOf(rs.getDate(5));
                int price = rs.getInt(6);
                String discount = rs.getInt(7) + "";
                String sold = rs.getInt(8) + "";
                String favorites_count = rs.getString(9);
                String fullname = rs.getString(10);
                int rate = calculate(subject_id);
                Subject subject = new Subject(subject_id, subject_name, description, image, price, discount, sold, created_date, fullname, favorites_count, String.valueOf(rate), 1);
                list.add(subject);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Subject> selectSubjectManage() {
        List<Subject> list = new ArrayList<>();
        String sql = " SELECT s.subject_id,s.subject_name,s.description,s.image,s.price,s.created_date,c.category_name , a.account_id,c.category_id\n"
                + "FROM Subject s\n"
                + "INNER JOIN Category c ON c.category_id = s.category_id\n"
                + "INNER JOIN Account a ON a.account_id = s.lecturer_id\n"
                + "INNER JOIN Role r ON r.role_id = a.role_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Account acc = new Account();
            Category c = new Category();
            while (rs.next()) {
                acc.setAccount_id(rs.getInt(8));
                c.setCategory_id(rs.getString(9));
                c.setCategory_name(rs.getString(7));
                // 
                Subject s = new Subject();
                s.setSubject_id(rs.getString(1));
                s.setSubject_name(rs.getString(2));
                s.setImage(rs.getString(4));
                s.setDescription(rs.getString(3));
                s.setPrice(rs.getInt(5));
                s.setCreated_date(rs.getString(6));

                // set cho thuoc tinh con
                s.setLecturer(acc);
                s.setCate(c);
                list.add(s);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void addSubject(String name, String description, String image, String created_date, String price, String discount, int lectuer_id, String category_id) {
        String sql = "INSERT INTO Subject(subject_name, description, image, created_date, updated_date, price, discount, sold, category_id, lecturer_id, status)\n"
                + "values(?,?,?,?, null, ?,?,null,?,?,'1');";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            st.setString(3, image);
            st.setString(4, created_date);
            st.setString(5, price);
            st.setString(6, discount);
            st.setInt(8, lectuer_id);
            st.setString(7, category_id);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public List<Subject> searchSubjectByName(String search_name) {

        List<Subject> list = new ArrayList();
        try {
            String sql = "SELECT s.subject_id, s.subject_name, s.description, s.image, s.price, s.created_date, c.category_name, a.account_id, c.category_id \n"
                    + "				FROM Subject s \n"
                    + "                JOIN Category c ON c.category_id = s.category_id \n"
                    + "                 JOIN Account a ON a.account_id = s.lecturer_id \n"
                    + "				WHERE s.subject_name LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + search_name + "%");

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccount_id(rs.getInt(8));

                Category c = new Category();
                c.setCategory_id(rs.getString(9));
                c.setCategory_name(rs.getString(7));
                // 
                Subject s = new Subject();
                s.setSubject_id(rs.getString(1));
                s.setSubject_name(rs.getString(2));
                s.setImage(rs.getString(4));
                s.setDescription(rs.getString(3));
                s.setPrice(rs.getInt(5));
                s.setCreated_date(rs.getString(6));

                s.setLecturer(acc);
                s.setCate(c);

                list.add(s);
            }
        } catch (Exception e) {
        }
        return list;

    }

    public List<Subject> selectTop3SubjectCreate() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT s.`subject_id`, s.`subject_name`, s.`description`, s.`image`, s.`price`, s.`created_date`, a.`fullname`\n"
                + "               FROM `Subject` s\n"
                + "                 JOIN `Account` a ON a.`account_id` = s.`lecturer_id`\n"
                + "                JOIN `Role` r ON r.`role_id` = a.`role_id`\n"
                + "                where s.status = 1"
                + "                ORDER BY s.`created_date` ASC\n"
                + "                LIMIT 3";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Account acc = new Account();

            while (rs.next()) {
                acc.setFullname(rs.getString(7));

                // 
                Subject s = new Subject();
                s.setSubject_id(rs.getString(1));
                s.setSubject_name(rs.getString(2));
                s.setImage(rs.getString(4));
                s.setDescription(rs.getString(3));
                s.setPrice(rs.getInt(5));
                s.setCreated_date(rs.getString(6));

                // set cho thuoc tinh con
                s.setLecturer(acc);

                list.add(s);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void updateSubjectStatus(String sid, String action) {
        String sql = """
                     update Subject set
                     status = ?
                     where subject_id = ?
                     """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(2, sid);
            if (action.equalsIgnoreCase("block")) {
                stm.setString(1, "0");
            } else {
                stm.setString(1, "1");
            }
            stm.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateSubject(String name, String desc, String img, String price, String up_date, String discount, String cid, String sid) {
        String sql = """
                     update Subject set
                     subject_name = ?,
                     description = ?,
                     image = ?,
                     updated_date = ?,
                     price = ?,
                     discount = ?,
                     category_id = ?
                     where subject_id = ?
                     """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, desc);
            stm.setString(3, img);
            stm.setString(5, price);
            stm.setString(4, up_date);
            stm.setString(6, discount);
            stm.setString(7, cid);
            stm.setString(8, sid);
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Subject getSubjectByID(String subject_id) {
        String sql = """
                     select * from Subject
                     where subject_id = ?;
                     """;
        Subject s = new Subject();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subject_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                s.setSubject_id(rs.getString(1));
                s.setSubject_name(rs.getString(2));
                s.setImage(rs.getString(4));
                s.setDescription(rs.getString(3));
                s.setCreated_date(rs.getString(5));
                s.setUpdated_date(rs.getString(6));
                s.setPrice(rs.getInt(7));
                s.setDiscount(rs.getString(8));
                Category c = new Category();
                c.setCategory_id(rs.getString(10));
                s.setCate(c);
                return s;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Subject> filterSubjectForLearner(String cate, String searchTxt, String sort) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT s.subject_id, s.subject_name, s.description, s.image, s.price,s.discount, s.created_date, c.category_name, s.favorites_count, a.fullname, c.category_id \n"
                + " FROM Subject s JOIN Category c ON c.category_id = s.category_id \n"
                + " JOIN Account a ON a.account_id = s.lecturer_id \n"
                + " where s.status = 1 and s.subject_name like '%" + searchTxt + "%'";

        if (!cate.isBlank()) {
            sql += " and c.category_id in (" + cate + ") ";
        }
        if (!sort.isBlank()) {
            if (sort.equalsIgnoreCase("asc")) {
                sql += " order by s.price asc";
            } else {
                sql += " order by s.price desc";
            }
        }

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Account acc = new Account();
                acc.setFullname(rs.getString(9));
                Category category = new Category();
                category.setCategory_id(rs.getString(8));
                category.setCategory_name(rs.getString(7));

                Subject s = new Subject();
                s.setSubject_id(rs.getString(1));
                s.setSubject_name(rs.getString(2));
                s.setDescription(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setPrice(rs.getInt(5));
                s.setDiscount(rs.getString(6));
                s.setCate(category);
                s.setLecturer(acc);
                s.setFavorites_count(rs.getString("favorites_count"));
                String subject_id = rs.getString(1);
                s.setRate_subject(calculate(subject_id) + "");

                list.add(s);

            }
        } catch (SQLException e) {
        }

        return list;
    }

    public List<Subject> filterSubjectForLecturer(String cate, String searchTxt, String sort) {
        List<Subject> list = new ArrayList<>();

        String sql = "SELECT s.subject_id, s.subject_name, s.description, s.image, s.price,s.discount, s.created_date, c.category_name, s.favorites_count, a.fullname, c.category_id \n"
                + " FROM Subject s JOIN Category c ON c.category_id = s.category_id \n"
                + " JOIN Account a ON a.account_id = s.lecturer_id \n"
                + " where s.subject_name like '%" + searchTxt + "%'";

        if (!cate.isBlank()) {
            sql += " and c.category_id in (" + cate + ") ";
        }
        if (!sort.isBlank()) {
            if (sort.equalsIgnoreCase("asc")) {
                sql += " order by s.price asc";
            } else {
                sql += " order by s.price desc";
            }
        }

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Account acc = new Account();
                acc.setFullname(rs.getString(9));
                Category category = new Category();
                category.setCategory_id(rs.getString(8));
                category.setCategory_name(rs.getString(7));

                Subject s = new Subject();
                s.setSubject_id(rs.getString(1));
                s.setSubject_name(rs.getString(2));
                s.setDescription(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setPrice(rs.getInt(5));
                s.setDiscount(rs.getString(6));
                s.setCate(category);
                s.setLecturer(acc);
                s.setFavorites_count(rs.getString("favorites_count"));
                String subject_id = rs.getString(1);
                s.setRate_subject(calculate(subject_id) + "");

                list.add(s);

            }
        } catch (SQLException e) {
        }

        return list;
    }

    public int CountAllCourseBySubject(String subject_id) {
        int count = 0;
        try {
            String sql = "Select count(c.course_id)\n"
                    + "                    from subject s\n"
                    + "                    join Course c on c.subject_id = s.subject_id\n"
                    + "                    where s.subject_id =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(subject_id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    public ArrayList<Subject> SelectAllLecturerSubjectLimit(String account_id, int start, int offset) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT s.subject_id,s.subject_name,s.image\n"
                + "FROM subject s\n"
                + "where s.lecturer_id = ? "
                + "ORDER BY created_date DESC LIMIT ?,?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(account_id));
            ps.setInt(2, start);
            ps.setInt(3, offset);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                String subject_id = rs.getInt(1) + "";
                String subject_name = rs.getString(2);
                String image = rs.getString(3);
                subject.setSubject_id(subject_id);
                subject.setSubject_name(subject_name);
                subject.setImage(image);
                list.add(subject);
            }
        } catch (SQLException e) {
        }
        return (ArrayList<Subject>) list;
    }

    public ArrayList<Subject> SelectAllLecturerSubject(String account_id) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT s.subject_id,s.subject_name,s.image\n"
                + "FROM subject s\n"
                + "where s.lecturer_id = ? ";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(account_id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                String subject_id = rs.getInt(1) + "";
                String subject_name = rs.getString(2);
                String image = rs.getString(3);
                subject.setSubject_id(subject_id);
                subject.setSubject_name(subject_name);
                subject.setImage(image);
                list.add(subject);
            }
        } catch (SQLException e) {
        }
        return (ArrayList<Subject>) list;
    }

    public int countLecturerSubjects(String accountId) {
        String sql = "SELECT COUNT(*) FROM subject WHERE lecturer_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Subject> SelectLecturerByName(String account_id, String searchsubject) {
        List<Subject> list = new ArrayList<>();
        String search = "%" + searchsubject + "%";
        String sql = "SELECT s.subject_id,s.subject_name,s.image\n"
                + "FROM subject s\n"
                + "where s.lecturer_id = ? and s.subject_name like ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(account_id));
            ps.setString(2, search);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                String subject_id = rs.getInt(1) + "";
                String subject_name = rs.getString(2);
                String image = rs.getString(3);
                subject.setSubject_id(subject_id);
                subject.setSubject_name(subject_name);
                subject.setImage(image);
                list.add(subject);
            }
        } catch (SQLException e) {
        }
        return (ArrayList<Subject>) list;
    }

    // lay cho subject cho subject manage
    public List<Subject> selectSubjectManage(int lectuer_id) {
        List<Subject> list = new ArrayList<>();

        String sql = """
                     SELECT s.subject_id,s.subject_name,s.description,s.image,s.price,s.created_date,c.category_name , a.account_id,c.category_id, s.status
                     FROM Subject s
                    JOIN Category c ON c.category_id = s.category_id
                    JOIN Account a ON a.account_id = s.lecturer_id
                    JOIN Role r ON r.role_id = a.role_id
                     where s.lecturer_id = ?
                     """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, lectuer_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategory_id(rs.getString(9));
                c.setCategory_name(rs.getString(7));
                // 
                Subject s = new Subject();
                s.setSubject_id(rs.getString(1));
                s.setSubject_name(rs.getString(2));
                s.setImage(rs.getString(4));
                s.setDescription(rs.getString(3));
                s.setPrice(rs.getInt(5));
                s.setCreated_date(rs.getString(6));
                s.setStatus(rs.getString("status"));
                s.setCate(c);
                list.add(s);

            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }

    

    public boolean checkEnrollSubjects(String subject_id) {
        String sql = """
                     SELECT * 
                     FROM learner_subject
                     where subject_id = ?;
                     """;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subject_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    public ArrayList<Subject> selectTop3SubjectFavorite() {
        ArrayList<Subject> list = new ArrayList<>();

        String sql = "SELECT TOP 3 s.subject_id, s.subject_name, s.description, s.image, s.created_date, " +
                "s.price, s.discount, s.sold, s.favorites_count, a.fullname " +
                "FROM subject s " +
                "JOIN Account a ON a.account_id = s.lecturer_id " +
                "ORDER BY s.favorites_count DESC;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String subject_id = rs.getString(1);
                String subject_name = rs.getString(2);
                String description = rs.getString(3);
                String image = rs.getString(4);
                String created_date = String.valueOf(rs.getDate(5));
                int price = rs.getInt(6);
                String discount = String.valueOf(rs.getInt(7));
                String sold = String.valueOf(rs.getInt(8));
                String favorites_count = String.valueOf(rs.getInt(9));
                String lecturer_name = rs.getString(10);

                Subject subject = new Subject(subject_id, subject_name, description, image, price, discount, sold, created_date, lecturer_name, favorites_count, "0", 1);
                list.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Subject> selectTop5SubjectSold() {
        ArrayList<Subject> list = new ArrayList<>();

        String sql = "select s.subject_id, s.subject_name,s.description, s.image , s.created_date,s.price, s.discount,s.sold,s.favorites_count,a.fullname from subject s\n"
                + "join Account a on a.account_id = s.lecturer_id \n"
                + "order by s.sold desc\n"
                + "limit 0,5 ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String subject_id = rs.getInt(1) + "";
                String subject_name = rs.getString(2);
                String description = rs.getString(3);
                String image = rs.getString(4);
                String created_date = String.valueOf(rs.getDate(5));
                int price = rs.getInt(6);
                String discount = rs.getInt(7) + "";
                String sold = rs.getInt(8) + "";
                String favorites_count = rs.getInt(9) + "";
                String fullname = rs.getString(10);
                int rate = calculate(subject_id);
                Subject subject = new Subject(subject_id, subject_name, description, image, price, discount, sold, created_date, fullname, favorites_count, String.valueOf(rate), 1);
                list.add(subject);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    private int calculate(String subject_id) {
        int rate = 0;

        String sql = "SELECT   SUM(lc.rate) / COUNT(lc.rate) as ratesubject \n"
                + "FROM learner_course lc \n"
                + "JOIN course c ON lc.course_id = c.course_id \n"
                + "JOIN subject s ON s.subject_id = c.subject_id \n"
                + "WHERE lc.rate != 0 and s.subject_id = ? \n"
                + "GROUP BY s.subject_id; ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(subject_id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rate = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return rate;
    }
// lay so trang subject

    public int getNumPageLearner() {
        String sql = """
                    select count(*) from subject where subject.status = 1;
                                        
                    """;
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
        } catch (Exception e) {
        }
        return 0;
    }
    public int getNumPageLecturer() {
        String sql = """
                    select count(*) from subject ;
                                        
                    """;
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
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Subject> getSubjectPageLearner(int index) {
        List<Subject> list = new ArrayList<>();
        String sql = """
                       SELECT * 
                       FROM subject s
                       WHERE s.status = 1
                       ORDER BY s.subject_id
                       OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;
                       """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(10),
                        rs.getString(11)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Subject> getSubjectPageLecturer(int index) {
        List<Subject> list = new ArrayList<>();
        String sql = """
                       SELECT * 
                       FROM subject s
                    
                       ORDER BY s.subject_id
                       OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;
                       """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(10),
                        rs.getString(11)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public int getNumPageByNameLearner(String name) {
        String sql = "select count(*) from subject\n"
                + "where s.status = 1 and subject.subject_name like '%" + name + "%' ";
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

    public int getNumPageByNameLecturer(String name) {
        String sql = "select count(*) from subject\n"
                + "where subject.subject_name like '%" + name + "%' ";
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

    public List<Subject> filterSubjectPagingLecturer(String searchTxt, int index) {
        List<Subject> list = new ArrayList<>();

        String sql = "SELECT s.subject_id, s.subject_name, s.description, s.image, s.price, s.discount, s.created_date, c.category_name, \n"
                + "       s.favorites_count, a.fullname, c.category_id\n"
                + "FROM Subject s \n"
                + "JOIN Category c ON c.category_id = s.category_id \n"
                + "JOIN Account a ON a.account_id = s.lecturer_id \n"
                + "WHERE s.subject_name LIKE '%" + searchTxt + "%' \n"
                + "ORDER BY s.subject_id \n"
                + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, (index - 1) * 6);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Account acc = new Account();
                acc.setFullname(rs.getString(9));
                Category category = new Category();
                category.setCategory_id(rs.getString(8));
                category.setCategory_name(rs.getString(7));

                Subject s = new Subject();
                s.setSubject_id(rs.getString(1));
                s.setSubject_name(rs.getString(2));
                s.setDescription(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setPrice(rs.getInt(5));
                s.setDiscount(rs.getString(6));
                s.setCate(category);
                s.setLecturer(acc);
                s.setFavorites_count(rs.getString("favorites_count"));
                String subject_id = rs.getString(1);
                s.setRate_subject(calculate(subject_id) + "");

                list.add(s);

            }
        } catch (SQLException e) {
        }

        return list;
    }
    
    public List<Subject> filterSubjectPagingLearner(String searchTxt, int index) {
        List<Subject> list = new ArrayList<>();

        String sql = "SELECT s.subject_id, s.subject_name, s.description, s.image, s.price, s.discount, s.created_date, c.category_name, \n"
                + "       s.favorites_count, a.fullname, c.category_id\n"
                + "FROM Subject s \n"
                + "JOIN Category c ON c.category_id = s.category_id \n"
                + "JOIN Account a ON a.account_id = s.lecturer_id \n"
                + "WHERE s.status = 1 and s.subject_name LIKE '%" + searchTxt + "%' \n"
                + "ORDER BY s.subject_id \n"
                + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, (index - 1) * 6);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Account acc = new Account();
                acc.setFullname(rs.getString(9));
                Category category = new Category();
                category.setCategory_id(rs.getString(8));
                category.setCategory_name(rs.getString(7));

                Subject s = new Subject();
                s.setSubject_id(rs.getString(1));
                s.setSubject_name(rs.getString(2));
                s.setDescription(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setPrice(rs.getInt(5));
                s.setDiscount(rs.getString(6));
                s.setCate(category);
                s.setLecturer(acc);
                s.setFavorites_count(rs.getString("favorites_count"));
                String subject_id = rs.getString(1);
                s.setRate_subject(calculate(subject_id) + "");

                list.add(s);

            }
        } catch (SQLException e) {
        }

        return list;
    }
public List<Account> getSubjectsAndStudents(int lecturerId) {
        List<Account> result = new ArrayList<>();
        String sql = """
                SELECT s.subject_name AS subjectName, a.fullname AS studentName
                FROM subject s
                JOIN order_detail od ON s.subject_id = od.subject_id
                JOIN orders o ON od.order_id = o.order_id
                JOIN account a ON o.account_id = a.account_id
                WHERE s.lecturer_id = ? 
                  AND o.payment_status = 'Success';
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, lecturerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setFullname(rs.getString("studentName"));
                Subject subject = new Subject();
                subject.setSubject_name(rs.getString("subjectName"));
                account.setSubjects(List.of(subject)); // Assuming each student has one subject for this query
                result.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

  


    public static void main(String[] args) {
        SubjectDAO sd = new SubjectDAO();

//       List<Subject> habo = sd.getAllSubject();
//        System.out.println(sd.getNumPage());
//        System.out.println(sd.getSubjectPage(2));
//        System.out.println(sd.filterSubjectForLearner("1", "", ""));
        System.out.println(sd.filterSubjectPagingLearner("", 1));

    }
}

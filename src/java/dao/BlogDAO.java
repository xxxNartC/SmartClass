package dao;

import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;

public class BlogDAO extends DBContext {

    // Insert a new blog
    public void insertBlog(Blog blog) throws SQLException {
        String sql = "INSERT INTO blog (title, content, description, created_date, status, marketer_id, tag, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getContent());
            preparedStatement.setString(3, blog.getDescription());
            preparedStatement.setDate(4, new java.sql.Date(blog.getCreatedDate().getTime())); // Sử dụng java.sql.Date cho kiểu date
            preparedStatement.setInt(5, blog.getStatus()); // Truyền số nguyên cho status
            preparedStatement.setInt(6, blog.getMarketerId());
            preparedStatement.setString(7, blog.getTag());
            preparedStatement.setString(8, blog.getImage());

            preparedStatement.executeUpdate();
            System.out.println("Insert successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing blog
    public void updateBlog(Blog blog) throws SQLException {
        String sql = "UPDATE blog SET title=?, content=?, description=?, created_date=?, status=?, marketer_id=?, tag=?, image=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getContent());
            preparedStatement.setString(3, blog.getDescription());
            preparedStatement.setDate(4, new java.sql.Date(blog.getCreatedDate().getTime())); // Sử dụng java.sql.Date cho kiểu date
            preparedStatement.setInt(5, blog.getStatus()); // Truyền số nguyên cho status
            preparedStatement.setInt(6, blog.getMarketerId());
            preparedStatement.setString(7, blog.getTag());
            preparedStatement.setString(8, blog.getImage());
            preparedStatement.setInt(9, blog.getId());

            preparedStatement.executeUpdate();
            System.out.println("Update successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    // Delete a blog by ID
    public void deleteBlog(int id) throws SQLException {
        String sql = "DELETE FROM blog WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Delete successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a blog by ID
    public Blog getBlogById(int id) throws SQLException {
        String sql = "SELECT * FROM blog WHERE id=?";
        Blog blog = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                blog = new Blog();
                blog.setId(resultSet.getInt("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setDescription(resultSet.getString("description"));
                blog.setCreatedDate(resultSet.getDate("created_date")); // Nhận java.sql.Date từ cơ sở dữ liệu
                blog.setStatus(resultSet.getInt("status")); // Lấy giá trị int cho status
                blog.setMarketerId(resultSet.getInt("marketer_id"));
                blog.setTag(resultSet.getString("tag"));
                blog.setImage(resultSet.getString("image"));
                System.out.println("Blog retrieved: " + blog.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blog;
    }

    // Get all blogs
    public List<Blog> getAllBlogs() throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blog";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getInt("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setDescription(resultSet.getString("description"));
                blog.setCreatedDate(resultSet.getDate("created_date")); // Nhận java.sql.Date từ cơ sở dữ liệu
                blog.setStatus(resultSet.getInt("status"));  // Lấy giá trị int cho status
                blog.setMarketerId(resultSet.getInt("marketer_id"));
                blog.setTag(resultSet.getString("tag"));
                blog.setImage(resultSet.getString("image"));

                blogs.add(blog);
            }
            System.out.println("Retrieved " + blogs.size() + " blogs.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    // Get blogs by page
    public List<Blog> getBlogsByPage(int page, int pageSize) throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blog ORDER BY created_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, (page - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getInt("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setDescription(resultSet.getString("description"));
                blog.setCreatedDate(resultSet.getDate("created_date"));
                blog.setStatus(resultSet.getInt("status"));
                blog.setMarketerId(resultSet.getInt("marketer_id"));
                blog.setTag(resultSet.getString("tag"));
                blog.setImage(resultSet.getString("image"));

                blogs.add(blog);
            }
            System.out.println("Retrieved " + blogs.size() + " blogs from page " + page);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    // Get total number of blogs
    public int getTotalBlogs() throws SQLException {
        int totalBlogs = 0;
        String sql = "SELECT COUNT(*) FROM blog";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                totalBlogs = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalBlogs;
    }

    // Get latest blogs
    public List<Blog> getLatestBlogs(int limit) throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blog ORDER BY created_date DESC LIMIT ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, limit);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getInt("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setDescription(resultSet.getString("description"));
                blog.setCreatedDate(resultSet.getDate("created_date"));
                blog.setStatus(resultSet.getInt("status"));
                blog.setMarketerId(resultSet.getInt("marketer_id"));
                blog.setTag(resultSet.getString("tag"));
                blog.setImage(resultSet.getString("image"));

                blogs.add(blog);
            }
            System.out.println("Retrieved " + blogs.size() + " latest blogs");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    // Get blogs by marketer ID
    public List<Blog> getBlogByMarketerId(int marketerId) throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blog WHERE marketer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, marketerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getInt("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setDescription(resultSet.getString("description"));
                blog.setCreatedDate(resultSet.getDate("created_date"));
                blog.setStatus(resultSet.getInt("status"));
                blog.setMarketerId(resultSet.getInt("marketer_id"));
                blog.setTag(resultSet.getString("tag"));
                blog.setImage(resultSet.getString("image"));
                blogs.add(blog);
            }
            System.out.println("Retrieved " + blogs.size() + " blogs by marketer ID: " + marketerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    // Search blogs by title or tag (using LIKE with % for partial matching)
    public List<Blog> searchBlogsByTitleOrTag(String searchText, int page, int pageSize) throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blog WHERE title LIKE ? OR tag LIKE ? ORDER BY created_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Use % for partial matching
            preparedStatement.setString(1, "%" + searchText + "%");
            preparedStatement.setString(2, "%" + searchText + "%");
            preparedStatement.setInt(3, (page - 1) * pageSize);
            preparedStatement.setInt(4, pageSize);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getInt("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setDescription(resultSet.getString("description"));
                blog.setCreatedDate(resultSet.getDate("created_date"));
                blog.setStatus(resultSet.getInt("status"));
                blog.setMarketerId(resultSet.getInt("marketer_id"));
                blog.setTag(resultSet.getString("tag"));
                blog.setImage(resultSet.getString("image"));

                blogs.add(blog);
            }
            System.out.println("Retrieved " + blogs.size() + " blogs matching search text: " + searchText);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    // Get total number of blogs matching search text
    public int getTotalBlogsBySearchText(String searchText) throws SQLException {
        int totalBlogs = 0;
        String sql = "SELECT COUNT(*) FROM blog WHERE title LIKE ? OR tag LIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + searchText + "%");
            preparedStatement.setString(2, "%" + searchText + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalBlogs = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalBlogs;
    }

    // Get blogs by tag
    public List<Blog> getBlogsByTag(String tag, int page, int pageSize) throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blog WHERE tag = ? ORDER BY created_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tag);
            preparedStatement.setInt(2, (page - 1) * pageSize);
            preparedStatement.setInt(3, pageSize);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getInt("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setDescription(resultSet.getString("description"));
                blog.setCreatedDate(resultSet.getDate("created_date"));
                blog.setStatus(resultSet.getInt("status"));
                blog.setMarketerId(resultSet.getInt("marketer_id"));
                blog.setTag(resultSet.getString("tag"));
                blog.setImage(resultSet.getString("image"));

                blogs.add(blog);
            }
            System.out.println("Retrieved " + blogs.size() + " blogs with tag: " + tag);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    // Get total number of blogs with a specific tag
    public int getTotalBlogsByTag(String tag) throws SQLException {
        int totalBlogs = 0;
        String sql = "SELECT COUNT(*) FROM blog WHERE tag = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tag);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalBlogs = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalBlogs;
    }

    // Get all unique tags from the database
    public List<String> getAllTags() throws SQLException {
        List<String> tags = new ArrayList<>();
        String sql = "SELECT DISTINCT tag FROM blog";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                tags.add(resultSet.getString("tag"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }
    public static void main(String[] args)  {
        BlogDAO blogDAO = new BlogDAO();
        Blog blog = new Blog();
        blog.setTitle("Test Blog Title");
        blog.setContent("Test Blog Content");
        blog.setDescription("Test Blog Description");
        blog.setCreatedDate(new java.util.Date());
        blog.setStatus(1);
        blog.setMarketerId(1); // Replace with a valid marketer ID
        blog.setTag("Test, Tag");
        blog.setImage("test.jpg"); // Replace with a valid image name
        try {
            blogDAO.insertBlog(blog);
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}


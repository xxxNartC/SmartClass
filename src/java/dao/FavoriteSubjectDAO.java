/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author TRUONG GIANG
 */

import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.FavoriteSubject;

public class FavoriteSubjectDAO extends DBContext {

    // Method to retrieve all favorite subjects for a specific account
    public List<FavoriteSubject> getFavoriteSubjectsByAccountId(int accountId) {
        List<FavoriteSubject> favoriteSubjects = new ArrayList<>();
        String sql = "SELECT fs.favorite_id, fs.account_id, s.subject_id, s.subject_name, "
                + "s.description, s.image, c.category_name, s.favorites_count, fs.added_date "
                + "FROM favorite_subjects fs "
                + "INNER JOIN subject s ON fs.subject_id = s.subject_id "
                + "INNER JOIN category c ON s.category_id = c.category_id "
                + "WHERE fs.account_id = ? "
                + "ORDER BY fs.added_date DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FavoriteSubject favoriteSubject = new FavoriteSubject();
                favoriteSubject.setFavoriteId(rs.getInt("favorite_id"));
                favoriteSubject.setAccountId(rs.getInt("account_id"));
                favoriteSubject.setSubjectId(rs.getInt("subject_id"));
                favoriteSubject.setSubjectName(rs.getString("subject_name"));
                favoriteSubject.setDescription(rs.getString("description"));
                favoriteSubject.setImage(rs.getString("image"));
                favoriteSubject.setCategoryName(rs.getString("category_name"));
                favoriteSubject.setFavoritesCount(rs.getInt("favorites_count"));
                favoriteSubject.setAddedDate(rs.getDate("added_date"));

                favoriteSubjects.add(favoriteSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteSubjects;
    }

    // Additional CRUD methods if needed
    public void insertFavoriteSubject(FavoriteSubject favoriteSubject) {
        String sql = "INSERT INTO favorite_subjects (account_id, subject_id, added_date) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, favoriteSubject.getAccountId());
            statement.setInt(2, favoriteSubject.getSubjectId());
            statement.setDate(3, new java.sql.Date(favoriteSubject.getAddedDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    public void deleteFavoriteSubject(int favoriteId) {
        String sql = "DELETE FROM favorite_subjects WHERE favorite_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, favoriteId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<FavoriteSubject> getFavoriteSubjectsByAccountIdAndCategoryName(int accountId, String categoryName) {
    List<FavoriteSubject> favoriteSubjects = new ArrayList<>();
    String sql = "SELECT fs.favorite_id, fs.account_id, s.subject_id, s.subject_name, "
               + "s.description, s.image, c.category_name, s.favorites_count, fs.added_date "
               + "FROM favorite_subjects fs "
               + "INNER JOIN subject s ON fs.subject_id = s.subject_id "
               + "INNER JOIN category c ON s.category_id = c.category_id "
               + "WHERE fs.account_id = ? AND c.category_name = ? "
               + "ORDER BY fs.added_date DESC";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, accountId);
        statement.setString(2, categoryName);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            FavoriteSubject favoriteSubject = new FavoriteSubject();
            favoriteSubject.setFavoriteId(rs.getInt("favorite_id"));
            favoriteSubject.setAccountId(rs.getInt("account_id"));
            favoriteSubject.setSubjectId(rs.getInt("subject_id"));
            favoriteSubject.setSubjectName(rs.getString("subject_name"));
            favoriteSubject.setDescription(rs.getString("description"));
            favoriteSubject.setImage(rs.getString("image"));
            favoriteSubject.setCategoryName(rs.getString("category_name"));
            favoriteSubject.setFavoritesCount(rs.getInt("favorites_count"));
            favoriteSubject.setAddedDate(rs.getDate("added_date"));

            favoriteSubjects.add(favoriteSubject);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return favoriteSubjects;
}

    // Method to retrieve favorite subjects by account ID and search term
    public List<FavoriteSubject> getFavoriteSubjectsByAccountIdAndSearchTerm(int accountId, String searchTerm) {
        List<FavoriteSubject> favoriteSubjects = new ArrayList<>();
        String sql = "SELECT fs.favorite_id, fs.account_id, s.subject_id, s.subject_name, "
                + "s.description, s.image, c.category_name, s.favorites_count, fs.added_date "
                + "FROM favorite_subjects fs "
                + "INNER JOIN subject s ON fs.subject_id = s.subject_id "
                + "INNER JOIN category c ON s.category_id = c.category_id "
                + "WHERE fs.account_id = ? AND s.subject_name LIKE ? "
                + "ORDER BY fs.added_date DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            statement.setString(2, "%" + searchTerm + "%"); // Use wildcard for partial matching
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FavoriteSubject favoriteSubject = new FavoriteSubject();
                favoriteSubject.setFavoriteId(rs.getInt("favorite_id"));
                favoriteSubject.setAccountId(rs.getInt("account_id"));
                favoriteSubject.setSubjectId(rs.getInt("subject_id"));
                favoriteSubject.setSubjectName(rs.getString("subject_name"));
                favoriteSubject.setDescription(rs.getString("description"));
                favoriteSubject.setImage(rs.getString("image"));
                favoriteSubject.setCategoryName(rs.getString("category_name"));
                favoriteSubject.setFavoritesCount(rs.getInt("favorites_count"));
                favoriteSubject.setAddedDate(rs.getDate("added_date"));

                favoriteSubjects.add(favoriteSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteSubjects;
    }
    
    public List<FavoriteSubject> getFavoriteSubjectsByAccountIdDESC(int accountId) {
        List<FavoriteSubject> favoriteSubjects = new ArrayList<>();
        String sql = "SELECT fs.favorite_id, fs.account_id, s.subject_id, s.subject_name, "
                + "s.description, s.image, c.category_name, s.favorites_count, fs.added_date "
                + "FROM favorite_subjects fs "
                + "INNER JOIN subject s ON fs.subject_id = s.subject_id "
                + "INNER JOIN category c ON s.category_id = c.category_id "
                + "WHERE fs.account_id = ? "
                + "ORDER BY fs.added_date DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FavoriteSubject favoriteSubject = new FavoriteSubject();
                favoriteSubject.setFavoriteId(rs.getInt("favorite_id"));
                favoriteSubject.setAccountId(rs.getInt("account_id"));
                favoriteSubject.setSubjectId(rs.getInt("subject_id"));
                favoriteSubject.setSubjectName(rs.getString("subject_name"));
                favoriteSubject.setDescription(rs.getString("description"));
                favoriteSubject.setImage(rs.getString("image"));
                favoriteSubject.setCategoryName(rs.getString("category_name"));
                favoriteSubject.setFavoritesCount(rs.getInt("favorites_count"));
                favoriteSubject.setAddedDate(rs.getDate("added_date"));

                favoriteSubjects.add(favoriteSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteSubjects;
    }
    
    public List<FavoriteSubject> getFavoriteSubjectsByAccountIdASC(int accountId) {
        List<FavoriteSubject> favoriteSubjects = new ArrayList<>();
        String sql = "SELECT fs.favorite_id, fs.account_id, s.subject_id, s.subject_name, "
                + "s.description, s.image, c.category_name, s.favorites_count, fs.added_date "
                + "FROM favorite_subjects fs "
                + "INNER JOIN subject s ON fs.subject_id = s.subject_id "
                + "INNER JOIN category c ON s.category_id = c.category_id "
                + "WHERE fs.account_id = ? "
                + "ORDER BY fs.added_date ASC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FavoriteSubject favoriteSubject = new FavoriteSubject();
                favoriteSubject.setFavoriteId(rs.getInt("favorite_id"));
                favoriteSubject.setAccountId(rs.getInt("account_id"));
                favoriteSubject.setSubjectId(rs.getInt("subject_id"));
                favoriteSubject.setSubjectName(rs.getString("subject_name"));
                favoriteSubject.setDescription(rs.getString("description"));
                favoriteSubject.setImage(rs.getString("image"));
                favoriteSubject.setCategoryName(rs.getString("category_name"));
                favoriteSubject.setFavoritesCount(rs.getInt("favorites_count"));
                favoriteSubject.setAddedDate(rs.getDate("added_date"));

                favoriteSubjects.add(favoriteSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteSubjects;
    }
    
    public List<FavoriteSubject> getFavoriteSubjectsByAccountIdAndSearchTermDESC(int accountId, String searchTerm) {
        List<FavoriteSubject> favoriteSubjects = new ArrayList<>();
        String sql = "SELECT fs.favorite_id, fs.account_id, s.subject_id, s.subject_name, "
                + "s.description, s.image, c.category_name, s.favorites_count, fs.added_date "
                + "FROM favorite_subjects fs "
                + "INNER JOIN subject s ON fs.subject_id = s.subject_id "
                + "INNER JOIN category c ON s.category_id = c.category_id "
                + "WHERE fs.account_id = ? AND s.subject_name LIKE ? "
                + "ORDER BY fs.added_date DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            statement.setString(2, "%" + searchTerm + "%"); // Use wildcard for partial matching
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FavoriteSubject favoriteSubject = new FavoriteSubject();
                favoriteSubject.setFavoriteId(rs.getInt("favorite_id"));
                favoriteSubject.setAccountId(rs.getInt("account_id"));
                favoriteSubject.setSubjectId(rs.getInt("subject_id"));
                favoriteSubject.setSubjectName(rs.getString("subject_name"));
                favoriteSubject.setDescription(rs.getString("description"));
                favoriteSubject.setImage(rs.getString("image"));
                favoriteSubject.setCategoryName(rs.getString("category_name"));
                favoriteSubject.setFavoritesCount(rs.getInt("favorites_count"));
                favoriteSubject.setAddedDate(rs.getDate("added_date"));

                favoriteSubjects.add(favoriteSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteSubjects;
    }
    
    public List<FavoriteSubject> getFavoriteSubjectsByAccountIdAndSearchTermASC(int accountId, String searchTerm) {
        List<FavoriteSubject> favoriteSubjects = new ArrayList<>();
        String sql = "SELECT fs.favorite_id, fs.account_id, s.subject_id, s.subject_name, "
                + "s.description, s.image, c.category_name, s.favorites_count, fs.added_date "
                + "FROM favorite_subjects fs "
                + "INNER JOIN subject s ON fs.subject_id = s.subject_id "
                + "INNER JOIN category c ON s.category_id = c.category_id "
                + "WHERE fs.account_id = ? AND s.subject_name LIKE ? "
                + "ORDER BY fs.added_date ASC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            statement.setString(2, "%" + searchTerm + "%"); // Use wildcard for partial matching
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FavoriteSubject favoriteSubject = new FavoriteSubject();
                favoriteSubject.setFavoriteId(rs.getInt("favorite_id"));
                favoriteSubject.setAccountId(rs.getInt("account_id"));
                favoriteSubject.setSubjectId(rs.getInt("subject_id"));
                favoriteSubject.setSubjectName(rs.getString("subject_name"));
                favoriteSubject.setDescription(rs.getString("description"));
                favoriteSubject.setImage(rs.getString("image"));
                favoriteSubject.setCategoryName(rs.getString("category_name"));
                favoriteSubject.setFavoritesCount(rs.getInt("favorites_count"));
                favoriteSubject.setAddedDate(rs.getDate("added_date"));

                favoriteSubjects.add(favoriteSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteSubjects;
    }
    
    // Method to retrieve favorite subjects sorted by favorites count in descending order
    public List<FavoriteSubject> getFavoriteSubjectsByAccountIdSortedByFavoritesCountDESC(int accountId) {
        List<FavoriteSubject> favoriteSubjects = new ArrayList<>();
        String sql = "SELECT fs.favorite_id, fs.account_id, s.subject_id, s.subject_name, "
                + "s.description, s.image, c.category_name, s.favorites_count, fs.added_date "
                + "FROM favorite_subjects fs "
                + "INNER JOIN subject s ON fs.subject_id = s.subject_id "
                + "INNER JOIN category c ON s.category_id = c.category_id "
                + "WHERE fs.account_id = ? "
                + "ORDER BY s.favorites_count DESC"; // Sort by favorites_count in descending order

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FavoriteSubject favoriteSubject = new FavoriteSubject();
                favoriteSubject.setFavoriteId(rs.getInt("favorite_id"));
                favoriteSubject.setAccountId(rs.getInt("account_id"));
                favoriteSubject.setSubjectId(rs.getInt("subject_id"));
                favoriteSubject.setSubjectName(rs.getString("subject_name"));
                favoriteSubject.setDescription(rs.getString("description"));
                favoriteSubject.setImage(rs.getString("image"));
                favoriteSubject.setCategoryName(rs.getString("category_name"));
                favoriteSubject.setFavoritesCount(rs.getInt("favorites_count"));
                favoriteSubject.setAddedDate(rs.getDate("added_date"));

                favoriteSubjects.add(favoriteSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteSubjects;
    }

    // Method to retrieve favorite subjects sorted by favorites count in ascending order
    public List<FavoriteSubject> getFavoriteSubjectsByAccountIdSortedByFavoritesCountASC(int accountId) {
        List<FavoriteSubject> favoriteSubjects = new ArrayList<>();
        String sql = "SELECT fs.favorite_id, fs.account_id, s.subject_id, s.subject_name, "
                + "s.description, s.image, c.category_name, s.favorites_count, fs.added_date "
                + "FROM favorite_subjects fs "
                + "INNER JOIN subject s ON fs.subject_id = s.subject_id "
                + "INNER JOIN category c ON s.category_id = c.category_id "
                + "WHERE fs.account_id = ? "
                + "ORDER BY s.favorites_count ASC"; // Sort by favorites_count in ascending order

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FavoriteSubject favoriteSubject = new FavoriteSubject();
                favoriteSubject.setFavoriteId(rs.getInt("favorite_id"));
                favoriteSubject.setAccountId(rs.getInt("account_id"));
                favoriteSubject.setSubjectId(rs.getInt("subject_id"));
                favoriteSubject.setSubjectName(rs.getString("subject_name"));
                favoriteSubject.setDescription(rs.getString("description"));
                favoriteSubject.setImage(rs.getString("image"));
                favoriteSubject.setCategoryName(rs.getString("category_name"));
                favoriteSubject.setFavoritesCount(rs.getInt("favorites_count"));
                favoriteSubject.setAddedDate(rs.getDate("added_date"));

                favoriteSubjects.add(favoriteSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteSubjects;
    }
    
    // Method to retrieve favorite subjects by account ID, category name, and sort order
    public List<FavoriteSubject> getFavoriteSubjectsByAccountIdAndCategoryName(int accountId, String categoryName, String sortBy) {
        List<FavoriteSubject> favoriteSubjects = new ArrayList<>();
        String sql = "SELECT fs.favorite_id, fs.account_id, s.subject_id, s.subject_name, "
                + "s.description, s.image, c.category_name, s.favorites_count, fs.added_date "
                + "FROM favorite_subjects fs "
                + "INNER JOIN subject s ON fs.subject_id = s.subject_id "
                + "INNER JOIN category c ON s.category_id = c.category_id "
                + "WHERE fs.account_id = ? ";

        if (categoryName != null && !categoryName.isEmpty() && !categoryName.equals("All")) {
            sql += "AND c.category_name = ? ";
        }

        if (sortBy != null && !sortBy.isEmpty()) {
            if (sortBy.equals("addedDateDESC")) {
                sql += "ORDER BY fs.added_date DESC";
            } else if (sortBy.equals("addedDateASC")) {
                sql += "ORDER BY fs.added_date ASC";
            } else if (sortBy.equals("favoritesCountDESC")) {
                sql += "ORDER BY s.favorites_count DESC";
            } else if (sortBy.equals("favoritesCountASC")) {
                sql += "ORDER BY s.favorites_count ASC";
            }
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            if (categoryName != null && !categoryName.isEmpty() && !categoryName.equals("All")) {
                statement.setString(2, categoryName);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FavoriteSubject favoriteSubject = new FavoriteSubject();
                favoriteSubject.setFavoriteId(rs.getInt("favorite_id"));
                favoriteSubject.setAccountId(rs.getInt("account_id"));
                favoriteSubject.setSubjectId(rs.getInt("subject_id"));
                favoriteSubject.setSubjectName(rs.getString("subject_name"));
                favoriteSubject.setDescription(rs.getString("description"));
                favoriteSubject.setImage(rs.getString("image"));
                favoriteSubject.setCategoryName(rs.getString("category_name"));
                favoriteSubject.setFavoritesCount(rs.getInt("favorites_count"));
                favoriteSubject.setAddedDate(rs.getDate("added_date"));

                favoriteSubjects.add(favoriteSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteSubjects;
    }

    // Example main method to test getFavoriteSubjectsByAccountId
    public static void main(String[] args) {
        FavoriteSubjectDAO dao = new FavoriteSubjectDAO();
        List<FavoriteSubject> favorites = dao.getFavoriteSubjectsByAccountId(2);
        for (FavoriteSubject favorite : favorites) {
            System.out.println(favorite);
        }
    }
}

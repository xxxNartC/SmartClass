package model;

import java.util.Date;

/**
 * Model class to represent a Favorite Subject.
 */
public class FavoriteSubject {
    // Fields from the favorite_subjects table
    private int favoriteId;
    private int accountId;
    private int subjectId;
    private Date addedDate;

    // Additional fields from the subject table (optional, if needed)
    private String subjectName;
    private String description;
    private String image;
    private Date createdDate;
    private Date updatedDate;
    private double price;
    private double discount;
    private int sold;
    private String categoryName;  // Assuming category name is retrieved along with subject
    private int lecturerId;
    private String status;
    private int favoritesCount;

    // Getters and Setters
    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    // Additional fields related to the subject
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    // toString method (optional, for debugging)
    @Override
    public String toString() {
        return "FavoriteSubject{" +
                "favoriteId=" + favoriteId +
                ", accountId=" + accountId +
                ", subjectId=" + subjectId +
                ", addedDate=" + addedDate +
                ", subjectName='" + subjectName + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", price=" + price +
                ", discount=" + discount +
                ", sold=" + sold +
                ", categoryName='" + categoryName + '\'' +
                ", lecturerId=" + lecturerId +
                ", status='" + status + '\'' +
                ", favoritesCount=" + favoritesCount +
                '}';
    }
}

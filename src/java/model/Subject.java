/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Subject {

    private String subject_id;
    private String subject_name;
    private String description;
    private String image;
    private int price;
    private String discount;
    private String sold;
    private String created_date;
    private String updated_date;
    private String category_id;
    private String lecturer_id;
    private String lecturer_name;
    private String rate_subject;
    private Account lecturer;
    private Category cate;
    private String status;
    private boolean bought;
    private int status_id;
    private String favorites_count;

    public List<String> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<String> coursesList) {
        this.coursesList = coursesList;
    }
  private List<String> coursesList;
    public Subject() {
    }

    public Subject(String subject_id, String subject_name, String description, String image, int price, String discount, String sold, String created_date, String lecturer_name, String favorites_count, String rate,int status) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.sold = sold;
        this.created_date = created_date;
        this.lecturer_name = lecturer_name;
        this.favorites_count = favorites_count;
        this.rate_subject = rate;
        this.status_id = status;
    }

    public Subject(String subject_id, String subject_name, String description, String image, int price, String discount, String sold, String created_date, String updated_date, String category_id, String lecturer_id, String lecturer_name, String rate_subject, Account lecturer, Category cate, String status, boolean bought, int status_id) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.sold = sold;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.category_id = category_id;
        this.lecturer_id = lecturer_id;
        this.lecturer_name = lecturer_name;
        this.rate_subject = rate_subject;
        this.lecturer = lecturer;
        this.cate = cate;
        this.status = status;
        this.bought = bought;
        this.status_id = status_id;
    }

    public Subject(String subject_id, String subject_name, String description, String image, int price, String created_date, Account lecturer, Category cate) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.created_date = created_date;
        this.lecturer = lecturer;
        this.cate = cate;
    }

    public Subject(String subject_id, String subject_name, String description, String image, int price, String discount, String sold, String created_date, String updated_date, String category_id, String lecturer_id, String lecturer_name, String rate_subject) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.sold = sold;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.category_id = category_id;
        this.lecturer_id = lecturer_id;
        this.lecturer_name = lecturer_name;
        this.rate_subject = rate_subject;
    }

    public Subject(String subject_id, String subject_name, String description, String image, int price, String discount, String sold, String created_date, String updated_date, String category_id, String lecturer_id) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.sold = sold;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.category_id = category_id;
        this.lecturer_id = lecturer_id;
    }

    public Subject(String subject_id, String subject_name, String description, String image, int price, String lecturer_name) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.lecturer_name = lecturer_name;
    }

    public Account getLecturer() {
        return lecturer;
    }

    public void setLecturer(Account lecturer) {
        this.lecturer = lecturer;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(String lecturer_id) {
        this.lecturer_id = lecturer_id;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public String getRate_subject() {
        return rate_subject;
    }

    public void setRate_subject(String rate_subject) {
        this.rate_subject = rate_subject;
    }

    public Subject(Category cate) {
        this.cate = cate;
    }

    public Category getCate() {
        return cate;
    }

    public void setCate(Category cate) {
        this.cate = cate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getFavorites_count() {
        return favorites_count;
    }

    public void setFavorites_count(String favorites_count) {
        this.favorites_count = favorites_count;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author TRUONG GIANG
 */
public class LearnerCourse {
    private int id;
    private int learnerId;
    private int courseId;
    private int statusId;
    private double rate;  // Rate có thể là số thập phân
    private String feedback;
    private Date createdDate;  // Kiểu Date để khớp với kiểu 'date' trong SQL

    public LearnerCourse(int id, int learnerId, int courseId, int statusId, double rate, String feedback, Date createdDate) {
        this.id = id;
        this.learnerId = learnerId;
        this.courseId = courseId;
        this.statusId = statusId;
        this.rate = rate;
        this.feedback = feedback;
        this.createdDate = createdDate;
    }
    
    
    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(int learnerId) {
        this.learnerId = learnerId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}



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
public class LearnerSubject {
    private int id;
    private int learnerId;
    private int subjectId;
    private Date enrolledDate;  // Kiểu Date để khớp với kiểu 'date' trong SQL
    private boolean active;  // active có thể là kiểu boolean
    private int statusId;

    public LearnerSubject(int id, int learnerId, int subjectId, Date enrolledDate, boolean active, int statusId) {
        this.id = id;
        this.learnerId = learnerId;
        this.subjectId = subjectId;
        this.enrolledDate = enrolledDate;
        this.active = active;
        this.statusId = statusId;
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Date getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(Date enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}

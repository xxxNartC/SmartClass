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
public class CourseCertificate {
    private int courseCertificateId;
    private int learnerCourseId;
    private Date issueDate;  

    public CourseCertificate(int courseCertificateId, int learnerCourseId, Date issueDate) {
        this.courseCertificateId = courseCertificateId;
        this.learnerCourseId = learnerCourseId;
        this.issueDate = issueDate;
    }
    
    // Getters và Setters
    public int getCourseCertificateId() {
        return courseCertificateId;
    }

    public void setCourseCertificateId(int courseCertificateId) {
        this.courseCertificateId = courseCertificateId;
    }

    public int getLearnerCourseId() {
        return learnerCourseId;
    }

    public void setLearnerCourseId(int learnerCourseId) {
        this.learnerCourseId = learnerCourseId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}

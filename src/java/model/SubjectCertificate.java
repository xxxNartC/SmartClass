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
public class SubjectCertificate {
    private int subjectCertificateId;
    private int learnerSubjectId;
    private Date issueDate;  // Kiểu Date để khớp với kiểu 'date' trong SQL

    public SubjectCertificate(int subjectCertificateId, int learnerSubjectId, Date issueDate) {
        this.subjectCertificateId = subjectCertificateId;
        this.learnerSubjectId = learnerSubjectId;
        this.issueDate = issueDate;
    }
    
    // Getters và Setters
    public int getSubjectCertificateId() {
        return subjectCertificateId;
    }

    public void setSubjectCertificateId(int subjectCertificateId) {
        this.subjectCertificateId = subjectCertificateId;
    }

    public int getLearnerSubjectId() {
        return learnerSubjectId;
    }

    public void setLearnerSubjectId(int learnerSubjectId) {
        this.learnerSubjectId = learnerSubjectId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}

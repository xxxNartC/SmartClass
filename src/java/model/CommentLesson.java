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
public class CommentLesson {

    private int commentId;
    private int accountId;
    private int lessonId;
    private String comment;
    private Date commentDate;
    private int status;
    private Integer parentCommentId;

    private String fullname;
    private String reply;

    public CommentLesson() {
    }
    
    // Constructor
    public CommentLesson(int commentId, int accountId, int lessonId, String comment, Date commentDate, int status, Integer parentCommentId) {
        this.commentId = commentId;
        this.accountId = accountId;
        this.lessonId = lessonId;
        this.comment = comment;
        this.commentDate = commentDate;
        this.status = status;
        this.parentCommentId = parentCommentId;
    }

    public CommentLesson(int commentId, int accountId, int lessonId, String comment, Date commentDate, String fullname, Integer parentCommentId) {
        this.commentId = commentId;
        this.accountId = accountId;
        this.lessonId = lessonId;
        this.comment = comment;
        this.commentDate = commentDate;
        this.fullname = fullname;
        this.parentCommentId = parentCommentId;
    }

    public CommentLesson(int commentId, int accountId, int lessonId, String comment, Date commentDate, String fullname) {
        this.commentId = commentId;
        this.accountId = accountId;
        this.lessonId = lessonId;
        this.comment = comment;
        this.commentDate = commentDate;
        this.fullname = fullname;
    }

    // Getters and Setters
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
    @Override
    public String toString() {
        return "CommentLesson{"
                + "commentId=" + commentId
                + ", accountId=" + accountId
                + ", lessonId=" + lessonId
                + ", comment='" + comment + '\''
                + ", commentDate=" + commentDate
                + ", status=" + status
                + ", parentCommentId=" + parentCommentId
                + '}';
    }
}

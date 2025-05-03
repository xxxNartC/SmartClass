/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ThaiGay
 */
public class AssignmentSubmitted {
    private String submitted_id, learner_id, assignment_id, answer, document, mark, comment, status, submitted_date, response_date, course_name;

    public AssignmentSubmitted() {
    }

    public AssignmentSubmitted(String submitted_id, String learner_id, String assignment_id, String answer, String document, String mark, String comment, String status, String submitted_date, String response_date) {
        this.submitted_id = submitted_id;
        this.learner_id = learner_id;
        this.assignment_id = assignment_id;
        this.answer = answer;
        this.document = document;
        this.mark = mark;
        this.comment = comment;
        this.status = status;
        this.submitted_date = submitted_date;
        this.response_date = response_date;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    
    
    public String getSubmitted_id() {
        return submitted_id;
    }

    public void setSubmitted_id(String submitted_id) {
        this.submitted_id = submitted_id;
    }

    public String getLearner_id() {
        return learner_id;
    }

    public void setLearner_id(String learner_id) {
        this.learner_id = learner_id;
    }

    public String getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmitted_date() {
        return submitted_date;
    }

    public void setSubmitted_date(String submitted_date) {
        this.submitted_date = submitted_date;
    }

    public String getResponse_date() {
        return response_date;
    }

    public void setResponse_date(String response_date) {
        this.response_date = response_date;
    }
    
    
}

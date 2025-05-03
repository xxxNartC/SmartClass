/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * 
 */
public class QuizResult {

    String id;
    String learner_id;
    String quiz_id;
    String mark;
    String status;
    String quiz_name;
    String no_question;
    String chapter_id;
    String chapter_name;
    String username;
    String fullname;
    String email;

    public QuizResult(String id, String learner_id, String quiz_id, String mark, String status, String quiz_name, String no_question, String chapter_id, String chapter_name, String username, String fullname, String email) {
        this.id = id;
        this.learner_id = learner_id;
        this.quiz_id = quiz_id;
        this.mark = mark;
        this.status = status;
        this.quiz_name = quiz_name;
        this.no_question = no_question;
        this.chapter_id = chapter_id;
        this.chapter_name = chapter_name;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
    }

    public QuizResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLearner_id() {
        return learner_id;
    }

    public void setLearner_id(String learner_id) {
        this.learner_id = learner_id;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String quiz_name) {
        this.quiz_name = quiz_name;
    }

    public String getNo_question() {
        return no_question;
    }

    public void setNo_question(String no_question) {
        this.no_question = no_question;
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}


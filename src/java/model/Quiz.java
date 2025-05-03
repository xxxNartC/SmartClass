/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author
 */
public class Quiz {
    String quiz_id;
    String name;
    String chapter_id;
    String no_question;

    String status;
    
    public Quiz() {
    }

    public Quiz(String quiz_id, String name, String chapter_id, String no_question) {
        this.quiz_id = quiz_id;
        this.name = name;
        this.chapter_id = chapter_id;
        this.no_question = no_question;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getNo_question() {
        return no_question;
    }

    public void setNo_question(String no_question) {
        this.no_question = no_question;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}

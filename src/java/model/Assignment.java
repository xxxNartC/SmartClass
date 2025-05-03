/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ThaiGay
 */
public class Assignment {
    private String assignment_id, content, document, image, course_id;

    public Assignment() {
    }

    public Assignment(String assignment_id, String content, String document, String image, String course_id) {
        this.assignment_id = assignment_id;
        this.content = content;
        this.document = document;
        this.image = image;
        this.course_id = course_id;
    }

    public String getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class Chapter {
   private String chapter_id;
   private String chapter_no;
   private String chapter_name;
   private String course_id;
   private String description;

    public Chapter(String chapter_id, String chapter_no, String chapter_name, String course_id, String description) {
        this.chapter_id = chapter_id;
        this.chapter_no = chapter_no;
        this.chapter_name = chapter_name;
        this.course_id = course_id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
   
   

    public Chapter(String chapter_id, String chapter_no, String chapter_name, String course_id) {
        this.chapter_id = chapter_id;
        this.chapter_no = chapter_no;
        this.chapter_name = chapter_name;
        this.course_id = course_id;
    }

    public Chapter(String chapter_no, String chapter_name, String course_id) {
        this.chapter_no = chapter_no;
        this.chapter_name = chapter_name;
        this.course_id = course_id;
    }

    
    public Chapter() {
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getChapter_no() {
        return chapter_no;
    }

    public void setChapter_no(String chapter_no) {
        this.chapter_no = chapter_no;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
   
}

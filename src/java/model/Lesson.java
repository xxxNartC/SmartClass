/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Lesson {
    private String lesson_id;
    private String lesson_no;
    private String lesson_name;
    private String video;
    private String document;
    private String chapter_id;
    private String description;

    public Lesson() {
    }

    public Lesson(String lesson_id, String lesson_no, String lesson_name, String video, String document, String chapter_id, String description) {
        this.lesson_id = lesson_id;
        this.lesson_no = lesson_no;
        this.lesson_name = lesson_name;
        this.video = video;
        this.document = document;
        this.chapter_id = chapter_id;
        this.description = description;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public String getLesson_no() {
        return lesson_no;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public String getVideo() {
        return video;
    }

    public String getDocument() {
        return document;
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public String getDescription() {
        return description;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public void setLesson_no(String lesson_no) {
        this.lesson_no = lesson_no;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}

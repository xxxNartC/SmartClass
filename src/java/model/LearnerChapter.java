/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class LearnerChapter {
    private String id;
    private String learner_id;
    private String chapter_id;
    private String status_id;

    public LearnerChapter() {
    }

    public LearnerChapter(String id, String learner_id, String chapter_id, String status_id) {
        this.id = id;
        this.learner_id = learner_id;
        this.chapter_id = chapter_id;
        this.status_id = status_id;
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

    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }
    
    
}

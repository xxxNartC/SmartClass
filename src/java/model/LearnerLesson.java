/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TRUONG GIANG
 */
public class LearnerLesson {
    private int id;
    private int learnerId;
    private int lessonId;
    private int statusId;

    // Constructor
    public LearnerLesson() {
    }

    public LearnerLesson(int id, int learnerId, int lessonId, int statusId) {
        this.id = id;
        this.learnerId = learnerId;
        this.lessonId = lessonId;
        this.statusId = statusId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(int learnerId) {
        this.learnerId = learnerId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "LearnerLesson{" +
                "id=" + id +
                ", learnerId=" + learnerId +
                ", lessonId=" + lessonId +
                ", statusId=" + statusId +
                '}';
    }
}

package model;

public class LectureSubject {
    private String lecturerName;
    private String courseName;

    public LectureSubject() {
    }

    public LectureSubject(String lecturerName, String courseName) {
        this.lecturerName = lecturerName;
        this.courseName = courseName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    
}
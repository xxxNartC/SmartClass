/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author bacht
 */
public class GroupedSubject {
    private String subjectName;
    private String studentNames;

    public GroupedSubject() {
    }

    public GroupedSubject(String subjectName, String studentNames) {
        this.subjectName = subjectName;
        this.studentNames = studentNames;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(String studentNames) {
        this.studentNames = studentNames;
    }
    
}

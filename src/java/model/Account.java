/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author admin
 */
public class Account {

    private int account_id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String dob;
    private String phone;
    private String avatar;
    private String otp;
    private int active;
    private int role_id;
    private String roleName;
 private String courses; // String to hold all courses
    private List<String> coursesList;
    private List<Subject> subjects;


    public Account() {
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Account(String fullname, String avatar) {
        this.fullname = fullname;
        this.avatar = avatar;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRole_name() {
        String role_name = "";
        switch (getRole_id()) {
            case 1:
                role_name = "Admin";
                break;
            case 2:
                role_name = "Learner";
                break;
            case 3:
                role_name = "Lecture";
                break;
            case 4:
                role_name = "Marketer";
                break;
            default:
                break;
        }
        return role_name;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }
    
    public List<String> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<String> coursesList) {
        this.coursesList = coursesList;
    }
    
    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }


}

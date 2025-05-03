/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class RoleChangeRequest {

    private int account_id;
    private String username;
    private String fullname;
    private String email;
    private String dob;
    private String phone;
    private int current_role_id;
    private String current_role_name;
    private int requested_role_id;
    private String requested_role_name;
    private String content;

    public RoleChangeRequest() {
    }

    public RoleChangeRequest(int account_id, String username, String fullname, String email, String dob, String phone, int current_role_id, String current_role_name, int requested_role_id, String requested_role_name, String content) {
        this.account_id = account_id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.current_role_id = current_role_id;
        this.current_role_name = current_role_name;
        this.requested_role_id = requested_role_id;
        this.requested_role_name = requested_role_name;
        this.content = content;
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

    public int getCurrent_role_id() {
        return current_role_id;
    }

    public void setCurrent_role_id(int current_role_id) {
        this.current_role_id = current_role_id;
    }

    public String getCurrent_role_name() {
        return current_role_name;
    }

    public void setCurrent_role_name(String current_role_name) {
        this.current_role_name = current_role_name;
    }

    public int getRequested_role_id() {
        return requested_role_id;
    }

    public void setRequested_role_id(int requested_role_id) {
        this.requested_role_id = requested_role_id;
    }

    public String getRequested_role_name() {
        return requested_role_name;
    }

    public void setRequested_role_name(String requested_role_name) {
        this.requested_role_name = requested_role_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class User  {
    
    private int account_id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String dob;
    private String phone;
    private String avatar;
    private int active;
    private int role_id;

    
    
    public User() {
    }

    public User(int account_id, String username, String password, String email) {
        this.account_id = account_id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String fullname, String email, String dob, String phone, int active, int role_id) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.active = active;
        this.role_id = role_id;
    }

    
    
    public User(String username, String password, String fullname, String email, String dob, String phone, int role_id) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.role_id = role_id;
    }

   

    
    public User(int account_id, String username, String password, String fullname, String email, String dob, String phone, String avatar, int active, int role_id) {
        this.account_id = account_id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.avatar = avatar;
        this.active = active;
        this.role_id = role_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getActive() {
        return active;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" + "account_id=" + account_id + ", username=" + username + ", password=" + password + ", fullname=" + fullname + ", email=" + email + ", dob=" + dob + ", phone=" + phone + ", avatar=" + avatar + ", active=" + active + ", role_id=" + role_id + '}';
    }
    
}

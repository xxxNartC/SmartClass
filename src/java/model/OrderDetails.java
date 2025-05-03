/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TRUONG GIANG
 */
import java.util.Date;

public class OrderDetails {
    private int id;
    private int orderId;
    private int subjectId;
    private double price;
    private String subjectName;
    private String username;
    private String email;
    private String phone;
    private Date orderDate;
    private double totalMoney;
    private String paymentStatus;

    // Constructor
    public OrderDetails() {
    }

    public OrderDetails(int id, int orderId, int subjectId, double price, String subjectName, 
                        String username, String email, String phone, Date orderDate, 
                        double totalMoney, String paymentStatus) {
        this.id = id;
        this.orderId = orderId;
        this.subjectId = subjectId;
        this.price = price;
        this.subjectName = subjectName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.orderDate = orderDate;
        this.totalMoney = totalMoney;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", subjectId=" + subjectId +
                ", price=" + price +
                ", subjectName='" + subjectName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", orderDate=" + orderDate +
                ", totalMoney=" + totalMoney +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}

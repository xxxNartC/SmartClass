/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TRUONG GIANG
 */
public class Order {
    private int orderId;          // order_id (PK, int, not null)
    private Integer accountId;     // account_id (FK, int, nullable)
    private String orderDate;      // order_date (date, nullable)
    private Float totalMoney;      // total_money (float, nullable)
    private String paymentStatus;  // payment_status (varchar(20), nullable)

    // Constructor
    public Order(int orderId, Integer accountId, String orderDate, Float totalMoney, String paymentStatus) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.orderDate = orderDate;
        this.totalMoney = totalMoney;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // toString method for easy display
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", accountId=" + accountId +
                ", orderDate='" + orderDate + '\'' +
                ", totalMoney=" + totalMoney +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}


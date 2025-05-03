package model;

public class Orders {
    private int orderId;
    private String accountName;
    private String subjectName;
    private String orderDate;
    private float totalMoney;
    private String paymentStatus;
    private String accountId;
    private float price; // Thêm thuộc tính price

    public Orders() {
    }
    
    public Orders(int orderId, String accountName, String subjectName, String orderDate, float totalMoney, float price, String paymentStatus) {
        this.orderId = orderId;
        this.accountName = accountName;
        this.subjectName = subjectName;
        this.orderDate = orderDate;
        this.totalMoney = totalMoney;
        this.price = price;
        this.paymentStatus = paymentStatus;
    }

    public Orders(int orderId, String accountName, String subjectName, String orderDate, float totalMoney, String paymentStatus) {
        this.orderId = orderId;
        this.accountName = accountName;
        this.subjectName = subjectName;
        this.orderDate = orderDate;
        this.totalMoney = totalMoney;
        this.paymentStatus = paymentStatus;
    }

    public Orders(int orderId, String orderDate, float totalMoney, String paymentStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalMoney = totalMoney;
        this.paymentStatus = paymentStatus;
    }

    public Orders(int orderId, String subjectName, String orderDate, float totalMoney, String paymentStatus, String accountId) {
        this.orderId = orderId;
        this.subjectName = subjectName;
        this.orderDate = orderDate;
        this.totalMoney = totalMoney;
        this.paymentStatus = paymentStatus;
        this.accountId = accountId;
    }
    
    // Getter và Setter cho price
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
}

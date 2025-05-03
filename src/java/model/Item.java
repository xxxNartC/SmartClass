
package model;

public class Item {
    private Subject Subject;
    private int price;
    private int quantity;

    public Item() {
    }

    public Item(Subject Subject, int price, int quantity) {
        this.Subject = Subject;
        this.price = price;
        this.quantity = quantity;
    }

    public Subject getSubject() {
        return Subject;
    }

    public void setSubject(Subject Subject) {
        this.Subject = Subject;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
    
    
    
    
    
    
    
}

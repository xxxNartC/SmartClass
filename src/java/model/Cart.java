/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public int getQuantityById(int id) {
        return getItemById(id).getQuantity();
    }

    private Item getItemById(int id) {
        for (Item i : items) {
            if (Integer.parseInt(i.getSubject().getSubject_id()) == id) {
                return i;
            }
        }
        return null;
    }

    public void addItem(Item t) {
        if (getItemById(Integer.parseInt(t.getSubject().getSubject_id())) != null) {
            Item m = getItemById(Integer.parseInt(t.getSubject().getSubject_id()));
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public int getTotalMoney() {
        int total = 0;
        for (Item item : items) {
            double price = item.getPrice(); // Lấy giá gốc của sản phẩm
            String discountStr = item.getSubject().getDiscount(); // Lấy discount từ Subject

            // Kiểm tra discount có hợp lệ và chuyển thành số
            double discount = (discountStr != null && !discountStr.isEmpty())
                    ? Double.parseDouble(discountStr)
                    : 0;

            // Tính giá sau khi áp dụng chiết khấu
            double discountedPrice = price - (price * discount / 100);

            // Cộng giá đã chiết khấu vào tổng
            total += discountedPrice;
        }
        return total;
    }

    private Subject getProductById(int id, List<Subject> list) {
        for (Subject i : list) {
            if (Integer.parseInt(i.getSubject_id()) == id) {
                return i;
            }
        }
        return null;
    }

    public Cart(String txt, List<Subject> list) {
        items = new ArrayList<>();
        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("/");//thay / cho dau ,
                for (String i : s) {
                    String[] n = i.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quantity = Integer.parseInt(n[1]);
                    Subject p = getProductById(id, list);
                    Item t = new Item(p, p.getPrice(), quantity);
                    addItem(t);
                }
            }
        } catch (NumberFormatException e) {

        }
    }
}

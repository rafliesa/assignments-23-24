package main.java.assignments.assignment2;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String nama;
    private String nomorTelpon;
    private String email;
    private String lokasi;
    public String role;
    private ArrayList<Order> orderHistory;


    public User(String nama, String nomorTelepon, String email, String lokasi, String role){
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.nomorTelpon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
        this.orderHistory = new ArrayList<Order>();
    }

    public String getName() {
        return this.nama;
    }

    public String getNotelpon() {
        return this.nomorTelpon;
    }

    public String getEmail() {
        return this.email;
    }

    public String getLokasi() {
        return this.lokasi;
    }

    public String getRole() {
        return this.role;
    }

    public ArrayList<Order> getOrderHistory() {
        return this.orderHistory;
    }

    public void addOrderHistory(Order order) {
        this.orderHistory.add(order);
    }

    public Boolean orderExist (String orderID) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderID)) {
                return true;
            }
        }
        return false;
    }

    public Order getOrder (String orderID) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderID)) {
                return order;
            }
        }
        return null;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
}

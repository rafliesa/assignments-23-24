package assignments.assignment3.tp2;

import java.lang.reflect.Array;
import java.util.ArrayList;

import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class User {
    // Deklarasi instance variables
    private String nama;
    private String nomorTelpon;
    private String email;
    private String lokasi;
    public String role;
    private ArrayList<Order> orderHistory;
    private long saldo;
    private DepeFoodPaymentSystem payment;


    // Constructor
    public User(String nama, String nomorTelepon, String email, String lokasi, String role){
        this.nama = nama;
        this.nomorTelpon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
        this.orderHistory = new ArrayList<Order>();
    }

    
    // Constructor untuk tp3
    public User(String nama, String nomorTelepon, String email, String lokasi, String role,DepeFoodPaymentSystem payment, long saldo){
        this.nama = nama;
        this.nomorTelpon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
        this.orderHistory = new ArrayList<Order>();
        this.saldo = saldo;
        this.payment = payment;
    }

    // Getter untuk nama
    public String getName() {
        return this.nama;
    }

    // Getter untuk noTelpon
    public String getNotelpon() {
        return this.nomorTelpon;
    }

    // Getter untuk email
    public String getEmail() {
        return this.email;
    }

    // Getter untuk lokasi
    public String getLokasi() {
        return this.lokasi;
    }

    // Getter untuk role
    public String getRole() {
        return this.role;
    }

    // Getter untuk orderHistory
    public ArrayList<Order> getOrderHistory() {
        return this.orderHistory;
    }

    // Setter untuk menambah orderHistory
    public void addOrderHistory(Order order) {
        this.orderHistory.add(order);
    }

    // Mengembalikan boolean apakah order ada di orderHistory
    public Boolean orderExist (String orderID) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderID)) {
                return true;
            }
        }
        return false;
    }

    // Mengembalikan objek Order yang memiliki id sesuai dengan parameter
    public Order getOrder (String orderID) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderID)) {
                return order;
            }
        }
        return null;
    }

    public long getSaldo(){
        return this.saldo;
    }

    public void setSaldo(long amount){
        this.saldo = amount;
    }

    public void addSaldo(long amount){
        this.saldo += amount;
    }

    public void reduceSaldo(long amount){
        this.saldo -= amount;
    }

    public DepeFoodPaymentSystem getPayment(){
        return this.payment;
    }
}
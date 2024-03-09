package main.java.assignments.assignment2;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String tanggalPemesanan;
    private int biayaOngkosKirim;
    private Restaurant restaurant;
    private ArrayList<Menu> items;
    private boolean orderFinished;
    

    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items){
        this.orderId = orderId;
        this.tanggalPemesanan = tanggal;
        this.biayaOngkosKirim = ongkir;
        this.restaurant = resto;
        this.items = items;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getTanggal() {
        return this.tanggalPemesanan;
    }

    public int getOngkir() {
        return this.biayaOngkosKirim;
    }

    public Restaurant getResto() {
        return this.restaurant;
    }

    public ArrayList<Menu> getMenu() {
        return this.items;
    }

    public void setStatus(Boolean ordered) {
        this.orderFinished = ordered;
    }
    
    // TODO: tambahkan methods yang diperlukan untuk class ini
}

package main.java.assignments.assignment2;

import java.util.ArrayList;

import assignments.assignment1.OrderGenerator;

public class Order {
    private String orderId;
    private String tanggalPemesanan;
    private int biayaOngkosKirim;
    private Restaurant restaurant;
    private ArrayList<Menu> items;
    private boolean orderFinished;
    private User belongsTo;
    

    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, ArrayList<Menu> items, User belongsTo){
        this.orderId = orderId;
        this.tanggalPemesanan = tanggal;
        this.biayaOngkosKirim = ongkir;
        this.restaurant = resto;
        this.items = items;
        this.orderFinished = false;
        this.belongsTo = belongsTo;
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
        if (Boolean.compare(this.orderFinished, ordered)!=0) {
            System.out.printf("Status pesanan dengan ID %s berhasil diupdate!", getOrderId());
        } else {
            System.out.printf("Status pesanan dengan ID %s tidak berhasil diupdate!", getOrderId());
        }
        this.orderFinished = ordered;
    }
    
    public void cetakBill(){
        String dd = this.orderId.substring(4,6);
        String mm = this.orderId.substring(6, 8);
        String yyyy = this.orderId.substring(8, 12);

        double totalBiaya = 0;
        // Deklarasi variabel tanggal pemesanan
        String tanggalPemesanan = String.format("%s/%s/%s", dd,mm,yyyy);


        // Mengembalikan bill dalam bentuk String
        System.out.println("Bill");
        System.out.printf("Order ID: %s%n", this.orderId);
        System.out.printf("Tanggal Pemesanan: %s%s%s%n", dd,mm,yyyy);
        System.out.printf("Restaurant: %s", this.restaurant.getName());
        System.out.printf("Lokasi Pengiriman: %s", this.belongsTo.getLokasi());
        System.out.print("Status pengiriman: ");
        if (orderFinished) {
            System.out.println("Finished");
        } else {
            System.out.println("Not Finished");
        }
        System.out.println("Pesanan:");
        for (Menu menu : items) {
            totalBiaya += menu.getHarga();
            System.out.printf("- %s", menu);
        }
        System.out.printf("Biaya Ongkos Kirim: Rp %f%n", ongkosKirim(belongsTo.getLokasi()));
        totalBiaya += ongkosKirim(belongsTo.getLokasi());

        System.out.printf("Total Biaya: %f Rp", totalBiaya);
    }

    public static double ongkosKirim (String lokasi) {
        // Fungsi ini mengembalikan ongkos kirim bedasarkan lokasi
        // Jika lokasi tidak sesuai, akan mengembalikan "-"
        Double biaya = 0.0;
        if (lokasi.equals("P")) {
            biaya = 10000.0;
        } else if (lokasi.equals("U")) {
            biaya = 20000.0;
        } else if (lokasi.equals("T")) {
            biaya = 35000.0;
        } else if (lokasi.equals("S")) {
            biaya = 40000.0;
        } else if (lokasi.equals("B")) {
            biaya = 60000.0;
        }
        return biaya;
    }


    // TODO: tambahkan methods yang diperlukan untuk class ini
}

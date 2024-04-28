package assignments.assignment3.tp2;

import java.util.ArrayList;

import assignments.assignment1.OrderGenerator;

public class Order {
    // Deklarasi instance variables
    private String orderId;
    private String tanggalPemesanan;
    private int biayaOngkosKirim;
    private Restaurant restaurant;
    private ArrayList<Menu> items;
    private boolean orderFinished;
    private User belongsTo;
    
    // Constructor
    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, ArrayList<Menu> items, User belongsTo){
        this.orderId = orderId;
        this.tanggalPemesanan = tanggal;
        this.biayaOngkosKirim = ongkir;
        this.restaurant = resto;
        this.items = items;
        this.orderFinished = false;
        this.belongsTo = belongsTo;
    }

    // Getter untuk orderID
    public String getOrderId() {
        return this.orderId;
    }

    // Getter untuk tanggal
    public String getTanggal() {
        return this.tanggalPemesanan;
    }

    // Getter untuk ongkir
    public int getOngkir() {
        return this.biayaOngkosKirim;
    }

    // Getter untuk restaurant
    public Restaurant getResto() {
        return this.restaurant;
    }

    // Getter untuk menu
    public ArrayList<Menu> getMenu() {
        return this.items;
    }

    // Setter untuk status orderan
    public void setStatus(Boolean ordered) {
        if (Boolean.compare(this.orderFinished, ordered)!=0) {
            System.out.printf("Status pesanan dengan ID %s berhasil diupdate!", getOrderId());
        } else {
            System.out.printf("Status pesanan dengan ID %s tidak berhasil diupdate!", getOrderId());
        }
        this.orderFinished = ordered;
    }
    
    // Fungsi ini mencetak bill
    public void cetakBill(){
        // Mendeklarasikan total biaya
        double totalBiaya = 0;

        // Mengembalikan bill dalam bentuk String
        System.out.println("Bill");
        System.out.printf("Order ID: %s%n", this.orderId);
        System.out.printf("Tanggal Pemesanan: %s%n", getTanggal());
        System.out.printf("Restaurant: %s%n", this.restaurant.getName());
        System.out.printf("Lokasi Pengiriman: %s%n", this.belongsTo.getLokasi());
        System.out.print("Status pengiriman: ");
        if (orderFinished) {
            System.out.println("Finished");
        } else {
            System.out.println("Not Finished");
        }
        System.out.println("Pesanan:");
        for (Menu menu : items) {
            totalBiaya += menu.getHarga();
            System.out.printf("- %s%n", menu);
        }
        System.out.printf("Biaya Ongkos Kirim: Rp %.0f%n", ongkosKirim(belongsTo.getLokasi()));
        totalBiaya += ongkosKirim(belongsTo.getLokasi());

        System.out.printf("Total Biaya: Rp %.0f ", totalBiaya);
    }

    // Fungsi ini mengembalikan biaya ongkos kirim sesuai dengan parameter
    public static double ongkosKirim (String lokasi) {
        // Fungsi ini mengembalikan ongkos kirim bedasarkan lokasi
        // Jika lokasi tidak sesuai, akan mengembalikan "0.0"
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

}

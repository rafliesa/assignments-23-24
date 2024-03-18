package main.java.assignments.assignment2;

public class Menu {
    // Deklarasi instance variables
    private String namaMakanan;
    private double harga;

    // Constructor 
    public Menu(String namaMakanan, double harga){
        this.namaMakanan = namaMakanan;
        this.harga = harga;
    }

    // Getter untuk namaMakanan
    public String getNamaMakanan() {
        return this.namaMakanan;
    }

    // Getter untuk harga
    public Double getHarga() {
        return this.harga;
    }

    // Method toString() untuk merubah objek ini ke tipe String
    public String toString() {
        return String.format("%s %.0f", namaMakanan, harga);
    }
}

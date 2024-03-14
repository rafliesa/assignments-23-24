package main.java.assignments.assignment2;

public class Menu {
    private String namaMakanan;
    private double harga;

    public Menu(String namaMakanan, double harga){
        // TODO: buat constructor untuk class ini
        this.namaMakanan = namaMakanan;
        this.harga = harga;
    }

    public String getNamaMakanan() {
        return this.namaMakanan;
    }

    public Double getHarga() {
        return this.harga;
    }

    public String toString() {
        return String.format("%s %.0f", namaMakanan, harga);
    }
}

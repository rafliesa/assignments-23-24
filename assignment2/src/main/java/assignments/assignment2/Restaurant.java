package main.java.assignments.assignment2;

import java.util.ArrayList;

public class Restaurant {
    // Deklarasi instance variables
    private String nama;
    private ArrayList<Menu> menu;
    
    // Constructor 
    public Restaurant(String nama){
        this.nama = nama;
        this.menu = new ArrayList<Menu>();
    }

    
    // Getter untuk nama 
    public String getName() {
        return this.nama;
    }
    
    // Setter untuk menambah objek Menu ke ArrayList menu
    public void addMenu(Menu obj) {
        menu.add(obj);
    }

    // Getter untuk ArrayList menu
    public ArrayList<Menu> getMenu() {
        return this.menu;
    }


    // Fungsi ini mengembalikan boolean apakah Menu yang dicari ada di dalam ArrayList menu
    public boolean menuExist(String nama) {
        for (Menu makanan : menu) {
            if (makanan.getNamaMakanan().equals(nama)) {
                return true;
            }
        }
        return false;
    }

    // Fungsi ini mengembalikan menu yang memiliki nama yang sesuai dengan parameternya
    public Menu selectMenu(String namaMakanan) {
        for (Menu makanan : this.menu) {
            if (makanan.getNamaMakanan().equals(namaMakanan)) {
                return makanan;
            }
        }
        return null;
    }
    
    // Fungsi ini mencetak seluruh menu yang ada di ArrayList
    public void cetakMenu() {
        int count = 1;

        sortMenu();
        
        System.out.print("Menu:");
        for (Menu daftarMenu : menu) {
            System.out.printf("%n%d. %s",count++, daftarMenu);
        }
    }

    // Fungsi ini berfungsi untuk mensortir menu dari harga yang termurah
    private void sortMenu() {

        for (int i = 0; i < getMenu().size()-1; i++) {
            for (int j = i; j < getMenu().size(); j++) {
                Menu a = getMenu().get(i);
                Menu b = getMenu().get(j);

                if (a.getHarga() > b.getHarga()) {
                    getMenu().set(i, b);
                    getMenu().set(j, a);
                }

            }
        }
    }
}

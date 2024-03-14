package main.java.assignments.assignment2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Restaurant {
    private String nama;
    private ArrayList<Menu> menu;
    

    public Restaurant(String nama){
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.menu = new ArrayList<Menu>();
    }

    public String getName() {
        return this.nama;
    }
    
    public void addMenu(Menu obj) {
        menu.add(obj);
    }

    public ArrayList<Menu> getMenu() {
        return this.menu;
    }

    public Menu menuSelect(String nama) {
        for (Menu makanan : menu) {
            if (makanan.getNamaMakanan().equals(getName())) {
                return makanan;
            }
        }
        return null;
    }

    public boolean menuExist(String nama) {
        for (Menu makanan : menu) {
            if (makanan.getNamaMakanan().equals(nama)) {
                return true;
            }
        }
        return false;
    }

    public Menu selectMenu(String namaMakanan) {
        for (Menu makanan : this.menu) {
            if (makanan.getNamaMakanan().equals(namaMakanan)) {
                return makanan;
            }
        }
        return null;
    }
    
    public void cetakMenu() {
        int count = 1;

        sortMenu();
        
        System.out.print("Menu:");
        for (Menu daftarMenu : menu) {
            System.out.printf("%n%d. %s",count++, daftarMenu);
        }
    }

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

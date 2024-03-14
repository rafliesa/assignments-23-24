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
        Collections.sort(menu, new Comparator<Menu>() {
            public int compare(Menu o1, Menu o2) {
                return o1.getHarga().compareTo(o2.getHarga());
            }

        });

        Collections.sort(menu, (o1,o2)-> o1.getHarga().compareTo(o2.getHarga()));

        System.out.println("Menu:");
        for (Menu daftarMenu : menu) {
            System.out.printf("%d. %s%n",count++, daftarMenu);
        }
    }
}

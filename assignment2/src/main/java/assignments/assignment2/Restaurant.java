package main.java.assignments.assignment2;

import java.util.ArrayList;

public class Restaurant {
    private String nama;
    private ArrayList<Menu> menu;
    

    public Restaurant(String nama){
        // TODO: buat constructor untuk class ini
        this.nama = nama;
    }

    public String getName() {
        return this.nama;
    }
    
    public void addMenu(Menu obj) {
        menu.add(obj);
    }

    public ArrayList<menu> getMenu() {
        return this.menu;
    }

}

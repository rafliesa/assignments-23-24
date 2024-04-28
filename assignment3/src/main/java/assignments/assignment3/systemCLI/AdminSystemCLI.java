package assignments.assignment3.systemCLI;

import java.util.Scanner;

import assignments.assignment3.MainMenu;
import assignments.assignment3.tp2.*;

//TODO: Extends Abstract yang diberikan
public class AdminSystemCLI extends UserSystemCLI{

    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
    boolean handleMenu(int command){
        switch(command){
            case 1 -> MainTepeDua.handleTambahRestoran(MainMenu.restoList); 
            case 2 -> MainTepeDua.handleHapusRestoran(MainMenu.restoList);
            case 3 -> {return false;}
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
    void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }
}

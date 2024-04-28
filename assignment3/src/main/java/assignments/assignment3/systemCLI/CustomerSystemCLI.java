package assignments.assignment3.systemCLI;

import java.util.Scanner;
import assignments.assignment2.*;
import assignments.assignment3.MainMenu;

//TODO: Extends abstract class yang diberikan
public class CustomerSystemCLI extends UserSystemCLI {

    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
    boolean handleMenu(int choice){
        switch(choice){
            case 1 -> assignments.assignment2.MainMenu.handleBuatPesanan(MainMenu.userLoggedIn, MainMenu.restoList); 
            case 2 -> assignments.assignment2.MainMenu.handleCetakBill(MainMenu.userLoggedIn);
            case 3 -> assignments.assignment2.MainMenu.handleLihatMenu(MainMenu.userLoggedIn, MainMenu.restoList);
            case 4 -> handleBayarBill();
            case 5 -> handleUpdateStatusPesanan();
            case 6 -> {
                return false;
            }
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
    void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Bayar Bill");
        System.out.println("5. Cek Saldo");
        System.out.println("6. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    void handleBayarBill(){
        // TODO: Implementasi method untuk handle ketika customer ingin melihat menu
    }

    void handleUpdateStatusPesanan(){

    }
}

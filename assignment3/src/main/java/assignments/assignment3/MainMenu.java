package assignments.assignment3;

import java.util.Scanner;

import java.util.ArrayList;
import assignments.assignment2.*;
import assignments.assignment3.LoginManager;
import assignments.assignment3.systemCLI.AdminSystemCLI;
import assignments.assignment3.systemCLI.CustomerSystemCLI;

public class MainMenu {
    private final Scanner input;
    private final LoginManager loginManager;
    public static User userLoggedIn;
    public static ArrayList<Restaurant> restoList = new ArrayList<Restaurant>();
    public static ArrayList<User> userList;

    public MainMenu(Scanner in, LoginManager loginManager) {
        this.input = in;
        this.loginManager = loginManager;
    }

    public static void main(String[] args) {
        initUser();
        MainMenu mainMenu = new MainMenu(new Scanner(System.in), new LoginManager(new AdminSystemCLI(), new CustomerSystemCLI()));
        mainMenu.run();
    }

    public void run(){
        printHeader();
        boolean exit = false;
        while (!exit) {
            startMenu();
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> login();
                case 2 -> exit = true;
                default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }

        input.close();
    }

    private void login(){
        while (true) {
            System.out.println("\nSilakan Login:");
            System.out.print("Nama: ");
            String nama = input.nextLine();
            System.out.print("Nomor Telepon: ");
            String noTelp = input.nextLine();
    
            // TODO: Validasi input login
            if (!loginVerifier(nama, noTelp)) {
                System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                continue;
            }
            userLoggedIn = getUser(nama, noTelp);
            break;
        }
        
        loginManager.getSystem(userLoggedIn.role);
    }

    private static void printHeader(){
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    private static void startMenu(){
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void initUser(){
        userList = new ArrayList<User>();
        userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer"));
        userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer"));
        userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer"));
        userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer"));
        userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer"));
 
        userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin"));
        userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin"));
     }

     public static boolean loginVerifier(String nama, String noTelpon) {
        // Fungsi ini mengembalikan boolean apakah input login user benar
        for (User user : userList) {
            if (user.getName().equals(nama)) {
                if (user.getNotelpon().equals(noTelpon)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static User getUser(String nama, String nomorTelepon){
        for (User user : userList) {
            if (user.getName().equals(nama)) {
                if (user.getNotelpon().equals(nomorTelepon)) {
                    return user;
                }
            }
        }
        return null;
    }
}

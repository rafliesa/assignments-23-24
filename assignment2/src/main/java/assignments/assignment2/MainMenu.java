package main.java.assignments.assignment2;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Scanner;
import assignments.assignment1.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Restaurant> restoList;
    private static ArrayList<User> userList;

    public static void main(String[] args) {
        initUser();
        initResto();
        
        boolean programRunning = true;
        while(programRunning){
            printHeader();
            startMenu();
            int command = input.nextInt();
            input.nextLine();

            if(command == 1){
                System.out.println("\nSilakan Login:");
                System.out.print("Nama: ");
                String nama = input.nextLine();
                System.out.print("Nomor Telepon: ");
                String noTelp = input.nextLine();

                if (!loginVerifier(nama, noTelp)) {
                    System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                    continue;
                }

                User userLoggedIn = getUser(nama, noTelp);
                boolean isLoggedIn = true;

                if(userLoggedIn.role == "Customer"){
                    System.out.printf("Selamat datang %s!", userLoggedIn.getName());
                    while (isLoggedIn){
                        menuCustomer();
                        int commandCust = input.nextInt();
                        input.nextLine();

                        switch(commandCust){
                            case 1 -> handleBuatPesanan(userLoggedIn);
                            case 2 -> handleCetakBill(userLoggedIn);
                            case 3 -> handleLihatMenu(userLoggedIn);
                            case 4 -> handleUpdateStatusPesanan(userLoggedIn);
                            case 5 -> isLoggedIn = false;
                            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
                        }
                    }
                }else{
                    System.out.print("Selamat datang Admin!");
                    while (isLoggedIn){
                        menuAdmin();
                        int commandAdmin = input.nextInt();
                        input.nextLine();

                        switch(commandAdmin){
                            case 1 -> handleTambahRestoran();
                            case 2 -> handleHapusRestoran();
                            case 3 -> isLoggedIn = false;
                            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
                        }
                    }
                }
            }else if(command == 2){
                programRunning = false;
            }else{
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^");
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

    public static void handleBuatPesanan(User userLoggedIn){
        System.out.println("--------------Buat Pesanan--------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String namaResto = input.nextLine();
    
            if (!restoExists(namaResto)) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }

            Restaurant selectedResto = restoSelector(namaResto);

            System.out.print("Tanggal Pemesanan(DD/MM/YYYY): ");
            String tanggalPemesanan = input.nextLine();

            if (!tanggalPemesanan.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)!\n");
                continue;
            }

            String orderID = OrderGenerator.generateOrderID(namaResto, tanggalPemesanan, userLoggedIn.getNotelpon());

            System.out.print("Jumlah Pesanan: ");
            int jumlahPesanan = Integer.parseInt(input.nextLine());

            String[] selectedFood = new String[jumlahPesanan];
            for (int i = 0; i < jumlahPesanan; i++) {
                selectedFood[i] = input.nextLine();
            }
            
            if (!selectedMenuValid(selectedFood, selectedResto)) {
                System.out.println("Makanan tidak ada di dalam menu!");
                continue;
            }

            ArrayList<Menu> selectedMenu = new ArrayList<>();
            for (String makanan : selectedFood) {
                selectedMenu.add(selectedResto.selectMenu(makanan));
            }
            
            Order order = new Order(orderID, tanggalPemesanan, jumlahPesanan, selectedResto, selectedMenu, userLoggedIn);
            userLoggedIn.addOrderHistory(order);

            System.out.printf("Pesanan dengan ID %s diterima!", orderID);
            break;
        }


        
    }

    public static void handleCetakBill(User user){
        System.out.println("--------------Cetak Bill--------------");

        while (true) {
            System.out.print("Masukan Order ID: ");
            String orderID = input.nextLine();
    
            if (!user.orderExist(orderID)) {
                System.out.println("Order ID tidak dapat ditemukan.");
                continue;
            }

            user.getOrder(orderID).cetakBill();
        }


    }

    public static void handleLihatMenu(User user){
        System.out.println("--------------Lihat Menu--------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String namaResto = input.nextLine();
    
            if (!restoExists(namaResto)) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
    
            Restaurant selectedResto = restoSelector(namaResto);
            selectedResto.cetakMenu();
            break;
        }


    }

    public static void handleUpdateStatusPesanan(User user){
        System.out.println("--------------Update Status Pesanan--------------");
        while (true) {
            System.out.print("Order ID: ");
            String orderID = input.nextLine();
    
            if (!user.orderExist(orderID)) {
                System.out.println("Order ID tidak dapat ditemukan.");
                continue;
            }

            System.out.print("Status: ");
            String statusOrder = input.nextLine();
            
            if (statusOrder.toLowerCase().equals("selesai")) {
                user.getOrder(orderID).setStatus(true);
            }
        }


    }

    public static void handleTambahRestoran(){
        System.out.println("--------------Tambah Restoran--------------");
        while (true) {

            // Meminta input nama
            System.out.print("Nama: ");
            String nama = input.nextLine();

            if (nama.length() < 4) {
                System.out.println("Nama Restoran tidak valid!\n");
                continue;
            }
            // Validasi input nama
            if (restoExists(nama)) {
                System.out.printf("Restoran dengan nama %s sudah pernah terdaftar. Mohon masukkan nama yang berbeda!%n%n", nama);
                continue;
            }
    
            // Deklarasi objek restaurant baru
            Restaurant resto = new Restaurant(nama);
    
            // Meminta input berupa jumlah makanan yang akan dibuat pada menu
            System.out.print("Jumlah makanan: ");
            int jumlahMakanan = Integer.parseInt(input.nextLine());
    
            // Meminta input menu makanan, dengan format Nama makanan - Harga
            String[] menu = new String[jumlahMakanan];
            for (int i = 0; i < jumlahMakanan; i++) {
                menu[i] = input.nextLine();
            }

            // Validasi input menu makanan
            if (!menuValid(menu)) {
                System.out.println("Harga menu harus bilangan bulat!\n");
                continue;
            }

            // Menambahkan menu yang telah dibuat 
            for (String daftarMenu : menu) {
                String[] menuParsed = parseMenu(daftarMenu);
                resto.addMenu(new Menu(menuParsed[0], Double.parseDouble(menuParsed[1])));
            }

            // Mencetak pesan bahwa restoran berhasil dibuat
            restoList.add(resto);
            System.out.printf("Restaurant %s Berhasil terdaftar.", nama);
            break;
        }

    }


    public static void handleHapusRestoran(){
        if (restoList.isEmpty()) {
            System.out.println("Resto masih kosong");
            return;

        }
        
        while (true) {

            System.out.print("Nama Restoran: ");
            String namaResto = input.nextLine();

            if (!restoExists(namaResto)) {
                System.out.println("Restoran tidak terdaftar pada Sistem.");
                continue;
            }

            for (Restaurant restoran : restoList) {
                if (restoran.getName().equals(namaResto)) {
                    restoList.remove(restoran);
                    System.out.print("Restoran berhasil dihapus.");
                    break;
                }
            }
            break;
        }
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

    public static void initResto(){
        restoList = new ArrayList<Restaurant>();
    }

    public static void printHeader(){
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    public static void startMenu(){
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuAdmin(){
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuCustomer(){
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Update Status Pesanan");
        System.out.println("5. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
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

    public static String[] parseMenu(String str) {
        /* Fungsi ini mem-parse input menu yang berformat Makanan-Harga
         * fungsi ini mengembalikan sebuah list of string yang memiliki 2 element
         * element pertama berupa nama makanan
         * element kedua berupa harga makanan
         */
        String[] result = new String[2];
        String[] splitResult = str.split(" ");
        
        String namaMakanan = "";
        String hargaMakanan = "";

        for (String string : splitResult) {
            if (string.matches("[0-9]*")) {
                hargaMakanan = string;
                break;
            }
            namaMakanan = namaMakanan + " " + string;
        }

        result[0] = namaMakanan.strip();
        result[1] = hargaMakanan;
        return result;
    }

    public static boolean restoExists(String nama) {
        // fungsi ini mengembalikan boolean apakah nama restaurant valid atau tidak
        // jika nama restaurant sudah ada, maka akan dianggap tidak valid
        for (Restaurant restaurant : restoList) {
            if (restaurant.getName().equals(nama)) {
                return true;
            }
        }
        return false;
    }

    public static boolean menuValid(String[] menu) {
        /* Fungsi ini mengembalikan boolean apakah input menu valid atau tidak
         * fungsi ini mengecek apakah harga pada menu dalam bentuk angka atau tidak
         */
        for (String namaHarga : menu) {
            String[] splitted = namaHarga.split(" ");
            if (!splitted[splitted.length-1].matches("[0-9]*")) {
                return false;
            }
        }
        return true;
    }

    public static Restaurant restoSelector(String name) {
        for (Restaurant restaurant : restoList) {
            if (restaurant.getName().equals(name)) {
                return restaurant;
            }
        }

        return null;
    }

    public static boolean selectedMenuValid(String[] menu, Restaurant resto) {
        for (String makananSelected : menu) {
            if (!resto.menuExist(makananSelected)) {
                return false;
            }
        }
        return true;
    }



}

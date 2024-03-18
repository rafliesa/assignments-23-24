package assignments.assignment1;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
            
        // Mendeklarasikan variabel string yang akan dipakai
        String namaRestoran, orderID, tanggalPemesanan, noTelpon, lokasi;

        // Mendeklarasikan variabel integer untuk menyimpan pilihan menu user
        int pilihMenu; 

        // Memangil fungsi show menu, fungsi ini mencetak pesan awal program
        // serta pilihan menu
        showMenu();

        // Main loop program
        for (;;) {
            // Meminta input berupa pilihan menu dari user
            System.out.println("--------------------------------------------");
            System.out.print("Pilihan menu: ");
            pilihMenu = input.nextInt(); input.nextLine();

            // Kondisi ketika menu yang dipilih adalah menu 1
            while (pilihMenu == 1) {
                // Generate orderId

                // Meminta input berupa nama restoran
                System.out.print("Nama Restoran: ");
                namaRestoran = input.nextLine().strip();

                // Validasi input, maka user akan dimintai kembali sesuai syarat yang berlaku
                if (namaRestoran.length() < 4) {
                    System.out.println("Nama Restoran tidak valid!\n");
                    continue;
                }

                // Meminta input berupa tanggal pemesanan
                // Tanggal pemesanan berformat DD/MM/YYYY
                System.out.print("Tanggal Pemesanan: ");
                tanggalPemesanan = input.nextLine();

                // Validasi input, maka user akan diminta kembali untuk mengisi sesuai format yang benar
                if (!tanggalPemesanan.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                    System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n");
                    continue;
                }

                // Meminta input berupa nomor telpon 
                System.out.print("No. Telpon: ");
                noTelpon = input.nextLine();

                // Validasi input, maka user diminta kembali untuk mengisi kembali sesuai syarat yang berlaku
                if (!noTelpon.matches("^[0-9]+$")) {
                    System.out.println("Harap masukan nomor telepon dalam bilangan bulat positif.\n");
                    continue;
                }

                // Memanggil fungsi generateOrderID yang akan mengembalikan orderID
                // hasil kembalian disimpan dalam variabel orderID
                orderID = generateOrderID(namaRestoran, tanggalPemesanan, noTelpon);

                // Mencetak Order ID
                System.out.printf("Order ID %s diterima!\n", orderID);
                
                // Mencetak pilihan menu
                showChoicesOnly();

                break;
            }
            
            // Kondisi ketika menu yang dipilih adalah menu 2
            while (pilihMenu == 2) {
                // cetak bill

                // Meminta input berupa Order ID
                System.out.print("Order ID: ");
                orderID = input.nextLine();

                if (orderID.length() < 16) {
                    // Validasi input ketika panjang Order ID tidak sesuai\
                    System.out.println("Order ID minimal 16 karakter\n");
                    continue;
                } else if (!orderID.matches("....[0-9]{10}..")) {
                    // Kondisi ketika format OrderId tidak tepat
                    System.out.println("Silahkan masukkan Order ID yang valid!\n");
                    continue;
                }
                 else if (!orderID.substring(orderID.length()-2)
                                   .equals(getChecksum(orderID.substring(0,14)))) {
                    // Kondisi ketika OrderId yang dimasukan tidak benar, yaitu ketika bagian chcecksum tepat
                    System.out.println("Silahkan masukkan Order ID yang valid!\n");
                    continue;
                }

                // Meminta input berupa lokasi pengiriman
                System.out.print("Lokasi Pengiriman: ");
                lokasi = input.nextLine();

                // Kondisi ketika lokasi pengiriman tidak sesuai
                if (!verifikasiLokasi(lokasi.toUpperCase())) { 
                    System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!\n");
                    continue;
                }


                // Mencetak bill orderan
                System.out.println();
                System.out.println(generateBill(orderID, lokasi));
                
                // Mencetak pilhan menu
                showChoicesOnly();
                break;
            }

            if (pilihMenu == 3) {
                // Jika user memilih menu 3, program akan berhenti
                System.out.println("Terima kasih telah menggunakan DepeFood!");
                break;
            }

        }

    }

    public static void showMenu(){
        // Fungsi ini menampilkan pesan menu awal
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
        System.out.println("Pilih menu:");
        System.out.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    public static void showChoicesOnly() {
        // Fungsi ini menampilkan pilihan menu
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.err.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
        // Fungsi ini mengembalikan Order ID dalam bentuk string
        /* Keterangan orderID yang akan digenerate :
         * - 4 huruf pertama nama restoran dalam kapital
         * - 8 Digit Tanggal orderan dalam format DDMMYYYY
         * - 2 Digit yang berasal dari akumulasi nomor telpon yang dimodulo 100 
         *  (tambahkan 0 di depan jika hasilnya hanya 1 ditgit)
         * - 2 karakter checksum (lihat fungsi getChecksum() untuk ketentuan)
         *  
         *  Lalu ke 16 karakter tersebut akan digabungkan membentuk sebuah string yang akan dikembalikan
         */ 
        String namaRestoranFirstFour = namaRestoran.replaceAll("//s", "").
                                       substring(0,4).toUpperCase();
        String tanggalOrderCut = tanggalOrder.replaceAll("/", "");
        String noTelponParsed = parseNoTelp(noTelepon);
        String checksum = getChecksum(namaRestoranFirstFour + tanggalOrderCut
                                      + noTelponParsed);
        String result = namaRestoranFirstFour + tanggalOrderCut
                        + noTelponParsed + checksum;
        return result;
    }



    public static String generateBill(String OrderID, String lokasi){
        // Fungsi ini mencetak bill dari sebuah orderan

        /* BIll:
         * Order ID:
         * Tanggal Pemesanan: DD/MM//YYY
         * Lokasi Pengiriman:
         * Biaya Ongkir: 
         */

        // deklarasi variabel untuk hari, bulan, dan tahun yang akan digabungkan untuk
        // membentuk tanggal pemesanan
        String dd = OrderID.substring(4,6);
        String mm = OrderID.substring(6, 8);
        String yyyy = OrderID.substring(8, 12);

        // Deklarasi variabel tanggal pemesanan
        String tanggalPemesanan = String.format("%s/%s/%s", dd,mm,yyyy);

        // deklarasi vairabel biaya 
        String biaya = ongkosKirim(lokasi.toUpperCase());

        // Mengembalikan bill dalam bentuk String
        return "Bill:\n" + "Order ID: " + OrderID + "\nTanggal Pemesanan: " + tanggalPemesanan
                + "\nLokasi Pengiriman: "  + lokasi.toUpperCase() + "\nBiaya Ongkos Kirim: " + biaya + "\n";
    }

    public static String parseNoTelp(String noTelpon) {
        /* Fungsi ini mereturn hasil olahan nomor telpon
         * Akumulasi seluruh digit nomor telpon lalu dimodulo 100
         * Jika hasilnya hanya 1 digit maka tambahkan 0 di depannya
         */
        int noTelpSum = 0;
        for (int i = 0; i < noTelpon.length(); i++) {
            noTelpSum += Integer.parseInt(String.valueOf(noTelpon.charAt(i)));
        }
        if (noTelpSum % 100 >= 10) {
            return String.format("%d", noTelpSum % 100);
        } else {
            return String.format("0%d", noTelpSum % 100);
        }
    }
    
    public static String getChecksum(String str) {
        /* Fungsi ini mengembalikan checksum dalam bentuk string yang berisi dua karakter
         * 
         * Ketentuan checksum :
         * - Ubah 14 karakter pertama orderID menjadi code39
         * - Akumulasi jumlah masing - masing posisi genap dan ganjil
         * - Ambil modulo 36 dari masing - masing akumulasi jumlah tersebut 
         * - Ubah hasil modulo tersebut menjadi ascii kembali
         * - karakter pertama diisi oleh hasil genap
         * - karakter kedua diisi oleh hasil ganjil
         */
        int oddSum = 0;
        int evenSum = 0;

        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {
                evenSum += toCode39(str.charAt(i));
            } else {
                oddSum += toCode39(str.charAt(i));
            }
        }
        
        int oddMod = oddSum % 36;
        int evenMod = evenSum % 36;

        String oddAscii = code39ToAscii(oddMod);
        String evenAscii = code39ToAscii(evenMod);
        return String.format("%s%s", evenAscii, oddAscii);
    }

    public static int toCode39(char x) {
        // Fungsi ini mengembalikan terjemahan karakter ascii ke code39
        // 1-9 = karakter 1-9
        // 10-35 = karakter A-Z secara berurutan
        if ('0' <= x && x <= '9') {
            return (int) x - '0'; // cari posisi relatifnya terhadap ascii 0
        } else if ('A' <= x && x <= 'Z') {
            return (int) x - 'A' + 10; // cari posisi relatifnya terhadap ascii A lalu tambah 10
        } else {
            return -1;
        }
    }

    public static String code39ToAscii(int code39) {
        // Fungsi ini mengembalikan terjemahan code39 ke karakter ascii
        // 1-9 = karakter 1-9
        // 10-35 = karakter A-Z secara berurutan
        if (code39 <= 9) {
            return String.format("%d", code39);
        } 
        return String.format("%c", (char) (code39 - 10 + (int) 'A'));
    }

    public static String ongkosKirim (String lokasi) {
        // Fungsi ini mengembalikan ongkos kirim bedasarkan lokasi
        // Jika lokasi tidak sesuai, akan mengembalikan "-"
        String biaya = "-";
        if (lokasi.equals("P")) {
            biaya = "Rp 10.000";
        } else if (lokasi.equals("U")) {
            biaya = "Rp 20.000";
        } else if (lokasi.equals("T")) {
            biaya = "Rp 35.000";
        } else if (lokasi.equals("S")) {
            biaya = "Rp 40.000";
        } else if (lokasi.equals("B")) {
            biaya = "Rp 60.000";
        } 
        return biaya;
    }

    public static boolean verifikasiLokasi (String lokasi) {
        // Fungsi ini megembalikan boolean apakah lokasi valid
        
        String[] listLokasi = {"P","U","T","S","B"}; // list yang berisi lokasi yang valid
        for (String lok : listLokasi) {
            if (lok.equals(lokasi)) {
                return true;
            }
        }
        return false;
    }
}

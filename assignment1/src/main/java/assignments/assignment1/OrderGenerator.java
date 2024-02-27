package assignments.assignment1;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // TODO: Implementasikan program sesuai ketentuan yang diberikanP
        System.out.println(generateOrderID("Yoshi", "22/02/2024", "08123456789"));
        System.out.println(generateBill("HOLY1802202453C3", "S"));
    }
    /* 
    Anda boleh membuat method baru sesuai kebutuhan Anda
    Namun, Anda tidak boleh menghapus ataupun memodifikasi return type method yang sudah ada.
    */

    /*
     * Method  ini untuk menampilkan menu
     */
    public static void showMenu(){
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.err.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
        System.out.println("Pilih menu:");
        System.err.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    /*
     * Method ini digunakan untuk membuat ID
     * dari nama restoran, tanggal order, dan nomor telepon
     * 
     * @return String Order ID dengan format sesuai pada dokumen soal
     */
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
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


    /*
     * Method ini digunakan untuk membuat bill
     * dari order id dan lokasi
     * 
     * @return String Bill dengan format sesuai di bawah:
     *          Bill:
     *          Order ID: [Order ID]
     *          Tanggal Pemesanan: [Tanggal Pemesanan]
     *          Lokasi Pengiriman: [Kode Lokasi]
     *          Biaya Ongkos Kirim: [Total Ongkos Kirim]
     */
    public static String generateBill(String OrderID, String lokasi){
        // TODO:Lengkapi method ini sehingga dapat mengenerate Bill sesuai ketentuan
        String dd = OrderID.substring(4,6);
        String mm = OrderID.substring(6, 8);
        String yyyy = OrderID.substring(8, 12);

        String tanggalPemesanan = String.format("%s/%s/%s", dd,mm,yyyy);
        String biaya = ongkosKirim(lokasi);

        return "Bill:\n" + "Order ID: " + OrderID + "\nTanggal Pemesanan: " + tanggalPemesanan
                + "\nLokasi Pengiriman: "  + lokasi + "\nBiaya Ongkos Kirim: " + biaya;
    }

    public static String parseNoTelp(String noTelpon) {
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
        if ('0' <= x && x <= '9') {
            return (int) x - '0';
        } else if ('A' <= x && x <= 'Z') {
            return (int) x - 'A' + 10;
        } else {
            return -1;
        }
    }

    public static String code39ToAscii(int code39) {
        if (code39 <= 9) {
            return String.format("%d", code39);
        } 
        return String.format("%c", (char) (code39 - 10 + (int) 'A'));
    }

    public static String ongkosKirim (String lokasi) {
        String biaya = "-";
        if (lokasi.equals("P")) {
            biaya = "Rp10.000";
        } else if (lokasi.equals("U")) {
            biaya = "Rp20.000";
        } else if (lokasi.equals("T")) {
            biaya = "Rp35.000";
        } else if (lokasi.equals("S")) {
            biaya = "Rp40.000";
        } else if (lokasi.equals("B")) {
            biaya = "Rp60.000";
        } 
        return biaya;
    }
}

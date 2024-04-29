package assignments.assignment3.systemCLI;

import java.util.Scanner;
import assignments.assignment3.tp2.MainTepeDua;
import assignments.assignment3.tp2.User;
import assignments.assignment3.tp2.Order;
import assignments.assignment3.MainMenu;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;

// Kelas ini merepresentasikan antarmuka untuk user yang memiliki role customer
public class CustomerSystemCLI extends UserSystemCLI {
    static Scanner input = new Scanner(System.in);

    boolean handleMenu(int choice){
        switch(choice){
            case 1 -> MainTepeDua.handleBuatPesanan(MainMenu.userLoggedIn, MainMenu.restoList); 
            case 2 -> MainTepeDua.handleCetakBill(MainMenu.userLoggedIn);
            case 3 -> MainTepeDua.handleLihatMenu(MainMenu.userLoggedIn, MainMenu.restoList);
            case 4 -> handleBayarBill(MainMenu.userLoggedIn);
            case 5 -> handleCekSaldo(MainMenu.userLoggedIn);
            case 6 -> {
                return false;
            }
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

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

    public void handleBayarBill(User user){
        // method ini berfungsi untuk melakukan proses pembayaran bill

        DepeFoodPaymentSystem paymentMethod = user.getPayment();
        Order order;
        long totalHarga;

        // Kondisi ketika user belum melakukan order
        if (user.getOrderHistory().isEmpty()) {
            System.out.println("Belum ada order yang dilakukan oleh user!");
            return;
        }

        while (true) {
            // Meminta order ID
            System.out.print("Masukan Order ID: ");
            String orderID = input.nextLine();
            
            // Validasi order
            if (!user.orderExist(orderID)) {
                System.out.println("Order ID tidak dapat ditemukan.");
                continue;
            }

            order = user.getOrder(orderID);
            totalHarga = order.getTotalBiaya();

            // Kondisi ketika order sudah pernah dibayar sebelumnya
            if (order.getStatus()) {
                System.out.println("Pesanan dengan ID ini sudah lunas!");
                return;
            }

            // Cetak bill
            System.out.println();
            user.getOrder(orderID).cetakBill();
            break;
        }

        // Meminta metode pembayaran
        System.out.println("\n");
        System.out.println("Opsi Pembayaran: ");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit");
        System.out.println("Pilihan Metode Pembayaran: ");

        int paymentChoice;
        while (true) {
            paymentChoice = Integer.parseInt(input.nextLine());
            if (paymentChoice == 1 && paymentMethod instanceof DebitPayment ||
                paymentChoice == 2 && paymentMethod instanceof CreditCardPayment) {
                System.out.println("User belum memiliki metode pembayaran ini!");
                return;
            }
            if (!(paymentChoice == 1 || paymentChoice == 2)) {
                System.out.println("Mohon pilih opsi pembayaran yang valid!");
                continue;
            } 
            break;
    
        }

        paymentMethod.processPayment(totalHarga, order);

    }

    void handleCekSaldo(User user){
        long saldo = user.getSaldo();
        System.out.println("Sisa saldo sebesar Rp" + saldo);
    }
}

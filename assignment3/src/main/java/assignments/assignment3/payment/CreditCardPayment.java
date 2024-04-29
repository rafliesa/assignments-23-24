package assignments.assignment3.payment;

import assignments.assignment3.MainMenu;
import assignments.assignment3.tp2.Order;
import assignments.assignment3.tp2.Restaurant;
import assignments.assignment3.tp2.User;

// Kelas ini merepresentasikan metode pembayaran menggunakan credit card
public class CreditCardPayment implements DepeFoodPaymentSystem {
    // Mendeklarasikan konstanta persentase biaya transaksi
    final private double TRANSACTION_FEE_PERCENTAGE = 0.02;

    @Override
    public void processPayment(long amount, Order order) {
        User user = MainMenu.userLoggedIn;
        Restaurant resto = order.getResto();
        long saldoUser = user.getSaldo();
        long totalBiaya = amount + (long) countTransactionFee(amount);
        boolean orderStatus = order.getStatus();

        // Kondisi ketika pesanan ternyata  sudah lunas
        if (orderStatus) {
            System.out.println("Pesanan dengan ID ini sudah lunas!");
            return;
        }

        // Kondisi ketika ternyata biaya order lebih dari saldo user
        if (amount > saldoUser) {
            System.out.println("Saldo tidak mencukupi mohon menggunakan metode pembayaran yang lain");
        }

        // Mengurangi saldo user sesuai total biaya dan menambah saldo resto sesuai total biaya
        user.reduceSaldo(totalBiaya);
        resto.addSaldo(totalBiaya);

        // Mengupdate status pemesanan orderan
        order.finishOrder();

        System.out.printf("Berhasil Membayar Bill sebesar Rp%d dengan biaya transaksi sebesar Rp%d",
                           amount, (long) countTransactionFee(amount));
    }

    public double countTransactionFee(long amount) {
        // Fungsi ini menghitung biaya transaksi
        return amount * TRANSACTION_FEE_PERCENTAGE;
    }    
}

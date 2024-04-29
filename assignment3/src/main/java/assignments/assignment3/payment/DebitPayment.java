package assignments.assignment3.payment;

import assignments.assignment3.MainMenu;
import assignments.assignment3.tp2.Order;
import assignments.assignment3.tp2.Restaurant;
import assignments.assignment3.tp2.User;

// Kelas ini merepresentasikan metode pembayaran menggunakan debit
public class DebitPayment implements DepeFoodPaymentSystem{
    // Mendeklarasikan konstanta minimal biaya order
    final private double MINIMUM_TOTAL_PRICE = 50_000;
    
    @Override
    public void processPayment(long amount, Order order) {
        User user = MainMenu.userLoggedIn;
        Restaurant resto = order.getResto();
        long saldoUser = user.getSaldo();

        // Kondisi ketika biaya order kurang dari biaya minimal order
        if (amount < MINIMUM_TOTAL_PRICE) {
            System.out.println("Jumlah pesanan < 50000 mohon menggunakan metode pembayaran yang lain");
            return;
        }

        // Kondisi ketika total biaya order lebih dari saldo
        if (amount > saldoUser) {
            System.out.println("Saldo tidak mencukupi mohon menggunakan metode pembayaran yang lain");
        }

        // Mengurangi saldo user sesuai total biaya dan menambah saldo resto sesuai total biaya
        user.reduceSaldo(amount);
        resto.addSaldo(amount);

        // Mengupdate status pemesanan orderan
        order.finishOrder();

        System.out.println("Berhasil Membayar Bill sebesar Rp" + amount);
    }

}

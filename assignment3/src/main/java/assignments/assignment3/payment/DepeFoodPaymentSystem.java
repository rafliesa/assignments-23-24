package assignments.assignment3.payment;
import assignments.assignment3.tp2.*;

/* Interface untuk DepeFoodPaymentSystem
   Interface ini menyaratkan kelas yang mengimplementasikannya untuk memiliki method
   processPayment sebagai pemroses transaksi
*/
 
public interface DepeFoodPaymentSystem {
    // logika untuk melakukan transaksi
    public abstract void processPayment(long amount, Order order);
}

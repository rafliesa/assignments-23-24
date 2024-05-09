package assignments.assignment3.payment;
import assignments.assignment3.tp2.*;

/* Interface untuk DepeFoodPaymentSystem
   Interface ini menyaratkan kelas yang mengimplementasikannya untuk memiliki method
   processPayment sebagai pemroses transaksi
*/
 
public interface DepeFoodPaymentSystem {

    public long processPayment(long saldo, long amount) throws Exception;
}

package assignments.assignment3.payment;

/* Interface untuk DepeFoodPaymentSystem
   Interface ini menyaratkan kelas yang mengimplementasikannya untuk memiliki method
   processPayment sebagai pemroses transaksi
*/
 
public interface DepeFoodPaymentSystem {

    public long processPayment(long saldo, long amount) throws Exception;
}

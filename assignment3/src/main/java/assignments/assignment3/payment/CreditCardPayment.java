package assignments.assignment3.payment;

public class CreditCardPayment implements DepeFoodPaymentSystem {
    //TODO: implementasikan class yang implement interface di sini
    // Anda dibebaskan untuk membuat method yang diperlukan
    final private double TRANSACTION_FEE_PERCENTAGE = 0.02;

    @Override
    public long processPayment(long amount) {
        return 0; 
    }

    public double countTransactionFee(long amount) {
        return amount * TRANSACTION_FEE_PERCENTAGE;
    }
}

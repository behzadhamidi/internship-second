package payment;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(Connection connection) {
        this.paymentService = new PaymentService(connection);
    }

    public void addPayment(Payment payment) throws SQLException {
        paymentService.addPayment(payment);
    }

    public Payment getPayment(int paymentId) throws SQLException {
        return paymentService.getPayment(paymentId);
    }

    public List<Payment> getAllPayments() throws SQLException {
        return paymentService.getAllPayments();
    }

    public void updatePayment(Payment payment) throws SQLException {
        paymentService.updatePayment(payment);
    }

    public void deletePayment(int paymentId) throws SQLException {
        paymentService.deletePayment(paymentId);
    }
}

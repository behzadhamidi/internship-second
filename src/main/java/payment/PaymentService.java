package payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentService(Connection connection) {
        this.paymentRepository = new PaymentRepository(connection);
    }

    public void addPayment(Payment payment) throws SQLException {
        paymentRepository.addPayment(payment);
    }

    public Payment getPayment(int paymentId) throws SQLException {
        return paymentRepository.getPayment(paymentId);
    }

    public List<Payment> getAllPayments() throws SQLException {
        return paymentRepository.getAllPayments();
    }

    public void updatePayment(Payment payment) throws SQLException {
        paymentRepository.updatePayment(payment);
    }

    public void deletePayment(int paymentId) throws SQLException {
        paymentRepository.deletePayment(paymentId);
    }
}

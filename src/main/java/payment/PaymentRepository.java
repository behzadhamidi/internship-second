package payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private Connection connection;

    public PaymentRepository(Connection connection) {
        this.connection = connection;
    }

    public void addPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (orderid, userid, amount, paymentdate, paymentstatus) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, payment.getOrderId());
            statement.setInt(2, payment.getUserId());
            statement.setBigDecimal(3, payment.getAmount());
            statement.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            statement.setString(5, payment.getPaymentStatus());
            statement.executeUpdate();
        }
    }

    public Payment getPayment(int paymentId) throws SQLException {
        String sql = "SELECT * FROM payments WHERE paymentid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, paymentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Payment(
                            resultSet.getInt("paymentid"),
                            resultSet.getInt("orderid"),
                            resultSet.getInt("userid"),
                            resultSet.getBigDecimal("amount"),
                            resultSet.getDate("paymentdate"),
                            resultSet.getString("paymentstatus")
                    );
                }
            }
        }
        return null;
    }

    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                payments.add(new Payment(
                        resultSet.getInt("paymentid"),
                        resultSet.getInt("orderid"),
                        resultSet.getInt("userid"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getDate("paymentdate"),
                        resultSet.getString("paymentstatus")
                ));
            }
        }
        return payments;
    }

    public void updatePayment(Payment payment) throws SQLException {
        String sql = "UPDATE payments SET orderid = ?, userid = ?, amount = ?, paymentdate = ?, paymentstatus = ? WHERE paymentid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, payment.getOrderId());
            statement.setInt(2, payment.getUserId());
            statement.setBigDecimal(3, payment.getAmount());
            statement.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            statement.setString(5, payment.getPaymentStatus());
            statement.setInt(6, payment.getPaymentId());
            statement.executeUpdate();
        }
    }

    public void deletePayment(int paymentId) throws SQLException {
        String sql = "DELETE FROM payments WHERE paymentid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, paymentId);
            statement.executeUpdate();
        }
    }
}



package orderitem;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {
    private Connection connection;

    public OrderItemRepository(Connection connection) {
        this.connection = connection;
    }

    public void addOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "INSERT INTO order_items (orderid, productid, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderItem.getOrderId());
            statement.setInt(2, orderItem.getProductId());
            statement.setInt(3, orderItem.getQuantity());
            statement.setBigDecimal(4, orderItem.getPrice());
            statement.executeUpdate();
        }
    }

    public OrderItem getOrderItem(int orderItemId) throws SQLException {
        String sql = "SELECT * FROM order_items WHERE orderitemid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderItemId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new OrderItem(
                            resultSet.getInt("orderitemid"),
                            resultSet.getInt("orderid"),
                            resultSet.getInt("productid"),
                            resultSet.getInt("quantity"),
                            resultSet.getBigDecimal("price")
                    );
                }
            }
        }
        return null;
    }

    public List<OrderItem> getAllOrderItems() throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_items";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                orderItems.add(new OrderItem(
                        resultSet.getInt("orderItemId"),
                        resultSet.getInt("orderid"),
                        resultSet.getInt("productid"),
                        resultSet.getInt("quantity"),
                        resultSet.getBigDecimal("price")
                ));
            }
        }
        return orderItems;
    }

    public void updateOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "UPDATE order_items SET orderid = ?, productid = ?, quantity = ?, price = ? WHERE orderitemid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderItem.getOrderId());
            statement.setInt(2, orderItem.getProductId());
            statement.setInt(3, orderItem.getQuantity());
            statement.setBigDecimal(4, orderItem.getPrice());
            statement.setInt(5, orderItem.getOrderItemId());
            statement.executeUpdate();
        }
    }

    public void deleteOrderItem(int orderItemId) throws SQLException {
        String sql = "DELETE FROM order_items WHERE orderitemid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderItemId);
            statement.executeUpdate();
        }
    }
}

package order;




import orderitem.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private Connection connection;

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    public void addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (userid, totalprice, orderDate) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getUserId());
            statement.setBigDecimal(2, order.getTotalPrice());
            statement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            statement.executeUpdate();
        }


        for (OrderItem item : order.getOrderItems()) {
            String itemSql = "INSERT INTO order_items (orderid, productid, quantity, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement itemStatement = connection.prepareStatement(itemSql)) {
                itemStatement.setInt(1, order.getOrderId());
                itemStatement.setInt(2, item.getProductId());
                itemStatement.setInt(3, item.getQuantity());
                itemStatement.setBigDecimal(4, item.getPrice());
                itemStatement.executeUpdate();
            }
        }
    }

    public Order getOrder(int orderId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE orderid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // دریافت OrderItems
                    String itemSql = "SELECT * FROM order_items WHERE orderid = ?";
                    try (PreparedStatement itemStatement = connection.prepareStatement(itemSql)) {
                        itemStatement.setInt(1, orderId);
                        List<OrderItem> items = new ArrayList<>();
                        try (ResultSet itemResultSet = itemStatement.executeQuery()) {
                            while (itemResultSet.next()) {
                                items.add(new OrderItem(
                                        itemResultSet.getInt("orderitemid"),
                                        itemResultSet.getInt("orderid"),
                                        itemResultSet.getInt("productid"),
                                        itemResultSet.getInt("quantity"),
                                        itemResultSet.getBigDecimal("price")
                                ));
                            }
                        }
                        return new Order(
                                resultSet.getInt("orderid"),
                                resultSet.getInt("userid"),
                                items,
                                resultSet.getBigDecimal("totalprice"),
                                resultSet.getDate("orderdate")
                        );
                    }
                }
            }
        }
        return null;
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                String itemSql = "SELECT * FROM order_items WHERE orderid = ?";
                try (PreparedStatement itemStatement = connection.prepareStatement(itemSql)) {
                    itemStatement.setInt(1, resultSet.getInt("orderid"));
                    List<OrderItem> items = new ArrayList<>();
                    try (ResultSet itemResultSet = itemStatement.executeQuery()) {
                        while (itemResultSet.next()) {
                            items.add(new OrderItem(
                                    itemResultSet.getInt("orderitemid"),
                                    itemResultSet.getInt("orderid"),
                                    itemResultSet.getInt("productid"),
                                    itemResultSet.getInt("quantity"),
                                    itemResultSet.getBigDecimal("price")
                            ));
                        }
                    }
                    orders.add(new Order(
                            resultSet.getInt("orderid"),
                            resultSet.getInt("userid"),
                            items,
                            resultSet.getBigDecimal("totalprice"),
                            resultSet.getDate("orderdate")
                    ));
                }
            }
        }
        return orders;
    }

    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE orders SET userid = ?, totalprice = ?, orderdate = ? WHERE orderid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getUserId());
            statement.setBigDecimal(2, order.getTotalPrice());
            statement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setInt(4, order.getOrderId());
            statement.executeUpdate();
        }

        // بروزرسانی OrderItems
        String deleteItemsSql = "DELETE FROM order_items WHERE orderid = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteItemsSql)) {
            deleteStatement.setInt(1, order.getOrderId());
            deleteStatement.executeUpdate();
        }

        for (OrderItem item : order.getOrderItems()) {
            String itemSql = "INSERT INTO order_items (orderid, productid, quantity, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement itemStatement = connection.prepareStatement(itemSql)) {
                itemStatement.setInt(1, order.getOrderId());
                itemStatement.setInt(2, item.getProductId());
                itemStatement.setInt(3, item.getQuantity());
                itemStatement.setBigDecimal(4, item.getPrice());
                itemStatement.executeUpdate();
            }
        }
    }

    public void deleteOrder(int orderId) throws SQLException {
        String sql = "DELETE FROM orders WHERE orderid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }

        // حذف OrderItems
        String deleteItemsSql = "DELETE FROM order_items WHERE orderid = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteItemsSql)) {
            deleteStatement.setInt(1, orderId);
            deleteStatement.executeUpdate();
        }
    }
}


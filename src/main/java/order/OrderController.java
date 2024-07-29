package order;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderController {
    private OrderService orderService;

    public OrderController(Connection connection) {
        this.orderService = new OrderService(connection);
    }

    public void addOrder(Order order) throws SQLException {
        orderService.addOrder(order);
    }

    public Order getOrder(int orderId) throws SQLException {
        return orderService.getOrder(orderId);
    }

    public List<Order> getAllOrders() throws SQLException {
        return orderService.getAllOrders();
    }

    public void updateOrder(Order order) throws SQLException {
        orderService.updateOrder(order);
    }

    public void deleteOrder(int orderId) throws SQLException {
        orderService.deleteOrder(orderId);
    }
}


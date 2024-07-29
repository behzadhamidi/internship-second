package order;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(Connection connection) {
        this.orderRepository = new OrderRepository(connection);
    }

    public void addOrder(Order order) throws SQLException {
        orderRepository.addOrder(order);
    }

    public Order getOrder(int orderId) throws SQLException {
        return orderRepository.getOrder(orderId);
    }

    public List<Order> getAllOrders() throws SQLException {
        return orderRepository.getAllOrders();
    }

    public void updateOrder(Order order) throws SQLException {
        orderRepository.updateOrder(order);
    }

    public void deleteOrder(int orderId) throws SQLException {
        orderRepository.deleteOrder(orderId);
    }
}

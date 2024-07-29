package orderitem;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    public OrderItemService(Connection connection) {
        this.orderItemRepository = new OrderItemRepository(connection);
    }

    public void addOrderItem(OrderItem orderItem) throws SQLException {
        orderItemRepository.addOrderItem(orderItem);
    }

    public OrderItem getOrderItem(int orderItemId) throws SQLException {
        return orderItemRepository.getOrderItem(orderItemId);
    }

    public List<OrderItem> getAllOrderItems() throws SQLException {
        return orderItemRepository.getAllOrderItems();
    }

    public void updateOrderItem(OrderItem orderItem) throws SQLException {
        orderItemRepository.updateOrderItem(orderItem);
    }

    public void deleteOrderItem(int orderItemId) throws SQLException {
        orderItemRepository.deleteOrderItem(orderItemId);
    }
}

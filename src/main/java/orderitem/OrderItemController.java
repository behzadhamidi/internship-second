package orderitem;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderItemController {
    private OrderItemService orderItemService;

    public OrderItemController(Connection connection) {
        this.orderItemService = new OrderItemService(connection);
    }

    public void addOrderItem(OrderItem orderItem) throws SQLException {
        orderItemService.addOrderItem(orderItem);
    }

    public OrderItem getOrderItem(int orderItemId) throws SQLException {
        return orderItemService.getOrderItem(orderItemId);
    }

    public List<OrderItem> getAllOrderItems() throws SQLException {
        return orderItemService.getAllOrderItems();
    }

    public void updateOrderItem(OrderItem orderItem) throws SQLException {
        orderItemService.updateOrderItem(orderItem);
    }

    public void deleteOrderItem(int orderItemId) throws SQLException {
        orderItemService.deleteOrderItem(orderItemId);
    }
}


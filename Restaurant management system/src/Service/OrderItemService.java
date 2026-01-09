package Service;

import DAO.OrderItemDao;
import DAOIMPL.OrderItemDaoImpl;
import Exception.OrderItemException;
import Model.OrderItem;

import java.util.List;

public class OrderItemService {
    private final OrderItemDao orderItemDao;

    public OrderItemService() {
        this.orderItemDao = new OrderItemDaoImpl();
    }

    // Create order item
    public void createOrderItem(OrderItem orderItem) throws OrderItemException {
        orderItemDao.createOrderItem(orderItem);
    }

    // Get order item by ID
    public OrderItem getOrderItemById(int orderItemId) throws OrderItemException {
        return orderItemDao.getOrderItemById(orderItemId);
    }

    // Get all order items
    public List<OrderItem> getAllOrderItems() throws OrderItemException {
        return orderItemDao.getAllOrderItems();
    }

    // Get order items by order ID
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws OrderItemException {
        return orderItemDao.getOrderItemsByOrderId(orderId);
    }

    // Update order item
    public void updateOrderItem(OrderItem orderItem) throws OrderItemException {
        orderItemDao.updateOrderItem(orderItem);
    }

    // Delete order item
    public void deleteOrderItem(int orderItemId) throws OrderItemException {
        orderItemDao.deleteOrderItem(orderItemId);
    }
}

package DAO;

import java.util.List;
import Exception.OrderItemException;
import Model.OrderItem;

public interface OrderItemDao
{

    // Read
    public OrderItem getOrderItemById(int orderItemId) throws OrderItemException;
    public List<OrderItem> getAllOrderItems() throws OrderItemException;
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws OrderItemException;

    // Create
    public boolean createOrderItem(OrderItem orderItem) throws OrderItemException;

    // Update
    public boolean updateOrderItem(OrderItem orderItem) throws OrderItemException;

    // Delete
    public boolean deleteOrderItem(int orderItemId) throws OrderItemException;
}


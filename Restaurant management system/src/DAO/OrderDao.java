package DAO;

import java.util.List;
import Exception.OrderException;
import Model.Order;

public interface OrderDao
{
    // Read
    public Order getOrderById(int orderId) throws OrderException;
    public List<Order> getAllOrders() throws OrderException;

    // Create
    public boolean createOrder(Order order) throws OrderException;

    // Update
    public boolean updateOrder(Order order) throws OrderException;

    // Delete
    public boolean deleteOrder(int orderId) throws OrderException;
}

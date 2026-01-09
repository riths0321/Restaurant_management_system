package Service;

import DAO.OrderDao;
import DAOIMPL.OrderDaoImpl;
import Exception.OrderException;
import Model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDaoImpl();
    }

    // Create order
    public void createOrder(Order order) throws OrderException {
        orderDao.createOrder(order);
    }

    // Get order by ID
    public Order getOrderById(int orderId) throws OrderException {
        return orderDao.getOrderById(orderId);
    }

    // Get all orders
    public List<Order> getAllOrders() throws OrderException {
        return orderDao.getAllOrders();
    }

    // Get orders by customer ID
    public List<Order> getOrdersByCustomerId(int customerId) throws OrderException {
        return getAllOrders().stream()
                .filter(order -> order.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }

    // Get orders by date range
    public List<Order> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) throws OrderException {
        return getAllOrders().stream()
                .filter(order -> !order.getOrderDate().isBefore(startDate) && !order.getOrderDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    // Get orders by status
    public List<Order> getOrdersByStatus(String status) throws OrderException {
        return getAllOrders().stream()
                .filter(order -> order.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    // Update order
    public void updateOrder(Order order) throws OrderException {
        orderDao.updateOrder(order);
    }

    // Update order status
    public void updateOrderStatus(int orderId, String status) throws OrderException {
        Order order = getOrderById(orderId);
        order.setStatus(status);
        orderDao.updateOrder(order);
    }

    // Delete order
    public void deleteOrder(int orderId) throws OrderException {
        orderDao.deleteOrder(orderId);
    }

    // Get total sales amount
    public double getTotalSalesAmount() throws OrderException {
        return getAllOrders().stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();
    }
}

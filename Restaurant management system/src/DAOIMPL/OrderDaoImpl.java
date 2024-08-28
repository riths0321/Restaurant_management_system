package DAOIMPL;

import DAO.OrderDao;
import Exception.OrderException;
import Model.Order;
import Utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao
{
    private Connection connection;
    private PreparedStatement ppst = null;
    private ResultSet rs = null;

    public OrderDaoImpl() {
        this.connection = DBConnection.getConnection();
    }

    // 1. Get Order by ID
    @Override
    public Order getOrderById(int orderId) throws OrderException {
        String sql = "SELECT * FROM orders WHERE order_id=?";
        try {
            ppst = connection.prepareStatement(sql);
            ppst.setInt(1, orderId);
            rs = ppst.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                );
            } else {
                throw new OrderException("Order with ID: " + orderId + " not found.");
            }
        } catch (SQLException e) {
            throw new OrderException("Error retrieving Order: " + e.getMessage());
        }
    }

    // 2. Get all Orders
    @Override
    public List<Order> getAllOrders() throws OrderException
    {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try
        {
            ppst = connection.prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                );
                orders.add(order);
            }
        }
        catch (SQLException e)
        {
            throw new OrderException("Error retrieving Orders: " + e.getMessage());
        }
        return orders;
    }


    // 3.Create Order
    @Override
    public boolean createOrder(Order order) throws OrderException
    {
        String sql = "INSERT INTO orders (customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?)";
        try {
            ppst = connection.prepareStatement(sql);
            ppst.setInt(1, order.getCustomerId());
            ppst.setDate(2, new java.sql.Date.valueof(order.getOrderDate()));
            ppst.setDouble(3, order.getTotalAmount());
            ppst.setString(4, order.getStatus());
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new OrderException("Error creating Order: " + e.getMessage());
        }
    }


    // 4. Update Order
    @Override
    public boolean updateOrder(Order order) throws OrderException
    {
        String sql = "UPDATE orders SET customer_id=?, order_date=?, total_amount=?, status=? WHERE order_id=?";
        try {
            ppst = connection.prepareStatement(sql);
            ppst.setInt(1, order.getCustomerId());
            ppst.setDate(2,  new java.sql.Date.valueof(order.getOrderDate()));
            ppst.setDouble(3, order.getTotalAmount());
            ppst.setString(4, order.getStatus());
            ppst.setInt(5, order.getOrderId());
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new OrderException("Error updating Order: " + e.getMessage());
        }
    }


    // 5.Delete Order
    @Override
    public boolean deleteOrder(int orderId) throws OrderException {
        String sql = "DELETE FROM orders WHERE order_id=?";
        try {
            ppst = connection.prepareStatement(sql);
            ppst.setInt(1, orderId);
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new OrderException("Error deleting Order: " + e.getMessage());
        }
    }
}

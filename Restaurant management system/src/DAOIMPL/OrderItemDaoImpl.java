package DAOIMPL;

import DAO.OrderItemDao;
import Exception.OrderItemException;
import Model.OrderItem;
import Utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao
{
    private Connection connection;
    PreparedStatement ppst = null;
    ResultSet rs = null;

    public OrderItemDaoImpl()
    {
        this.connection = DBConnection.getConnection();
    }

    // 1. Get OrderItem by ID
    @Override
    public OrderItem getOrderItemById(int orderItemId) throws OrderItemException
    {
        String sql = "SELECT * FROM order_items WHERE order_item_id=?";
        try (PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setInt(1, orderItemId);
            rs = ppst.executeQuery();
            if (rs.next())
            {
                return extractOrderItemFromResultSet(rs);
            }
            else
            {
                throw new OrderItemException("Order item with ID: " + orderItemId + " not found.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new OrderItemException(e.getMessage());
        }
    }

    // 2. Get all OrderItems
    @Override
    public List<OrderItem> getAllOrderItems() throws OrderItemException
    {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_items";
        try (PreparedStatement ppst = connection.prepareStatement(sql))
        {
            rs = ppst.executeQuery();
            while (rs.next())
            {
                orderItems.add(extractOrderItemFromResultSet(rs));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new OrderItemException(e.getMessage());
        }
        return orderItems;
    }

    // 3. Get OrderItems by Order ID
    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws OrderItemException
    {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id=?";
        try (PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setInt(1, orderId);
            rs = ppst.executeQuery();
            while (rs.next())
            {
                orderItems.add(extractOrderItemFromResultSet(rs));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new OrderItemException(e.getMessage());
        }
        return orderItems;
    }

    // 4. Create OrderItem
    @Override
    public boolean createOrderItem(OrderItem orderItem) throws OrderItemException
    {
        String sql = "INSERT INTO order_items (order_id, menu_item_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setInt(1, orderItem.getOrderId());
            ppst.setInt(2, orderItem.getItemId());
            ppst.setInt(3, orderItem.getQuantity());
            ppst.setDouble(4, orderItem.getPrice());
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new OrderItemException(e.getMessage());
        }
    }

    // 5. Update OrderItem
    @Override
    public boolean updateOrderItem(OrderItem orderItem) throws OrderItemException
    {
        String sql = "UPDATE order_items SET order_id=?, menu_item_id=?, quantity=?, price=? WHERE order_item_id=?";
        try (PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setInt(1, orderItem.getOrderId());
            ppst.setInt(2, orderItem.getItemId());
            ppst.setInt(3, orderItem.getQuantity());
            ppst.setDouble(4, orderItem.getPrice());
            ppst.setInt(5, orderItem.getOrderItemId());
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new OrderItemException(e.getMessage());
        }
    }

    // 6. Delete OrderItem
    @Override
    public boolean deleteOrderItem(int orderItemId) throws OrderItemException
    {
        String sql = "DELETE FROM order_items WHERE order_item_id=?";
        try (PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setInt(1, orderItemId);
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new OrderItemException("Error deleting order item: " + e.getMessage());
        }
    }

    // Helper method to extract OrderItem from ResultSet
    private OrderItem extractOrderItemFromResultSet(ResultSet rs) throws SQLException
    {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(rs.getInt("order_item_id"));
        orderItem.setOrderId(rs.getInt("order_id"));
        orderItem.setItemId(rs.getInt("menu_item_id"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setPrice(rs.getDouble("price"));
        return orderItem;
    }
}
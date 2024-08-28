package DAOIMPL;

import DAO.MenuItemDao;
import Exception.MenuItemException;
import Model.MenuItem;
import Utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDaoImpl implements MenuItemDao
{
    private Connection connection;
    private PreparedStatement ppst = null;
    private ResultSet rs = null;

    public MenuItemDaoImpl()
    {
        this.connection = DBConnection.getConnection();
    }

    // 1.Get MenuItem by ID
    @Override
    public MenuItem getMenuItemById(int itemId) throws MenuItemException
    {
        String sql = "SELECT * FROM menu_items WHERE item_id=?";
        try
        {
            ppst = connection.prepareStatement(sql);
            ppst.setInt(1, itemId);
            rs = ppst.executeQuery();
            if (rs.next())
            {
                return new MenuItem(
                        rs.getInt("item_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getString("category")
                );
            }
            else
            {
                throw new MenuItemException("MenuItem with ID: " + itemId + " not found.");
            }
        }
        catch (SQLException e)
        {
            throw new MenuItemException("Error retrieving MenuItem: " + e.getMessage());
        }
    }

    // 2.Get all MenuItems
    @Override
    public List<MenuItem> getAllMenuItems() throws MenuItemException
    {
        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";
        try
        {
            ppst = connection.prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next())
            {
                MenuItem menuItem = new MenuItem(
                        rs.getInt("item_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getString("category")
                );
                menuItems.add(menuItem);
            }
        }
        catch (SQLException e)
        {
            throw new MenuItemException("Error retrieving MenuItems: " + e.getMessage());
        }
        return menuItems;
    }

    // 3.Create MenuItem
    @Override
    public boolean createMenuItem(MenuItem menuItem) throws MenuItemException
    {
        String sql = "INSERT INTO menu_items (name, description, price, category) VALUES (?, ?, ?, ?)";
        try
        {
            ppst = connection.prepareStatement(sql);
            ppst.setString(1, menuItem.getItemName());
            ppst.setString(2, menuItem.getDescription());
            ppst.setDouble(3, menuItem.getPrice());
            ppst.setString(4, menuItem.getCategory());
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e)
        {
            throw new MenuItemException("Error creating MenuItem: " + e.getMessage());
        }
    }

    // 4.Update MenuItem
    @Override
    public boolean updateMenuItem(MenuItem menuItem) throws MenuItemException
    {
        String sql = "UPDATE menu_items SET name=?, description=?, price=?, category=? WHERE item_id=?";
        try
        {
            ppst = connection.prepareStatement(sql);
            ppst.setString(1, menuItem.getItemName());
            ppst.setString(2, menuItem.getDescription());
            ppst.setDouble(3, menuItem.getPrice());
            ppst.setString(4, menuItem.getCategory());
            ppst.setInt(5, menuItem.getItemId());
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e)
        {
            throw new MenuItemException("Error updating MenuItem: " + e.getMessage());
        }
    }

    // 5.Delete MenuItem
    @Override
    public boolean deleteMenuItem(int itemId) throws MenuItemException
    {
        String sql = "DELETE FROM menu_items WHERE item_id=?";
        try
        {
            ppst = connection.prepareStatement(sql);
            ppst.setInt(1, itemId);
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e)
        {
            throw new MenuItemException("Error deleting MenuItem: " + e.getMessage());
        }
    }
}
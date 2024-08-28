package DAO;

import java.util.List;
import Exception.MenuItemException;
import Model.MenuItem;

public interface MenuItemDao
{
    // Read
    public MenuItem getMenuItemById(int itemId) throws MenuItemException;
    public List<MenuItem> getAllMenuItems() throws MenuItemException;

    // Create
    public boolean createMenuItem(MenuItem menuItem) throws MenuItemException;

    // Update
    public boolean updateMenuItem(MenuItem menuItem) throws MenuItemException;

    // Delete
    public boolean deleteMenuItem(int itemId) throws MenuItemException;
}


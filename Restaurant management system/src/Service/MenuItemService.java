package Service;

import DAO.MenuItemDao;
import DAOIMPL.MenuItemDaoImpl;
import Exception.MenuItemException;
import Model.MenuItem;

import java.util.List;
import java.util.stream.Collectors;

public class MenuItemService {
    private final MenuItemDao menuItemDao;

    public MenuItemService() {
        this.menuItemDao = new MenuItemDaoImpl();
    }

    // Create menu item
    public void createMenuItem(int itemId, String name, double price, String category) throws MenuItemException {
        MenuItem menuItem = new MenuItem(itemId, name, "", (int) price, category);
        menuItemDao.createMenuItem(menuItem);
    }

    // Get menu item by ID
    public MenuItem getMenuItemById(int itemId) throws MenuItemException {
        return menuItemDao.getMenuItemById(itemId);
    }

    // Get all menu items
    public List<MenuItem> getAllMenuItems() throws MenuItemException {
        return menuItemDao.getAllMenuItems();
    }

    // Get menu items by category
    public List<MenuItem> getMenuItemsByCategory(String category) throws MenuItemException {
        return getAllMenuItems().stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Get menu items by price range
    public List<MenuItem> getMenuItemsByPriceRange(int minPrice, int maxPrice) throws MenuItemException {
        return getAllMenuItems().stream()
                .filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // Update menu item
    public void updateMenuItem(int itemId, String name, double price, String category) throws MenuItemException {
        MenuItem menuItem = new MenuItem(itemId, name, "", (int) price, category);
        menuItemDao.updateMenuItem(menuItem);
    }

    // Delete menu item
    public void deleteMenuItem(int itemId) throws MenuItemException {
        menuItemDao.deleteMenuItem(itemId);
    }
}

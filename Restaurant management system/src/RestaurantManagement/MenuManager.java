package RestaurantManagement;

import Exception.MenuItemException;
import Model.MenuItem;
import Service.MenuItemService;

import java.util.List;
import java.util.Scanner;

public class MenuManager {
    private final Scanner sc;
    private final MenuItemService menuItemService;

    public MenuManager(Scanner sc, MenuItemService menuItemService) {
        this.sc = sc;
        this.menuItemService = new MenuItemService();
    }

    public MenuManager(Scanner sc, MenuItemService menuItemService, Scanner sc1, MenuItemService menuItemService1) {
        this.sc = sc1;
        this.menuItemService = menuItemService1;
    }

    // Handle menu management operations
    public void handleMenuManagement() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("Menu Management:\n" +
                    "1. Add Menu Item\n" +
                    "2. Update Menu Item\n" +
                    "3. Delete Menu Item\n" +
                    "4. View All Menu Items\n" +
                    "5. View Menu Items by Category\n" +
                    "6. View Menu Items by Price Range\n" +
                    "7. Go Back\n" +
                    "Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> addMenuItem();
                case 2 -> updateMenuItem();
                case 3 -> deleteMenuItem();
                case 4 -> viewAllMenuItems();
                case 5 -> viewMenuItemsByCategory();
                case 6 -> viewMenuItemsByPriceRange();
                case 7 -> keepRunning = false;
                default -> System.out.println("Invalid choice. Please try again!\n");
            }
        }
    }

    // Add a new menu item
    private void addMenuItem() {
        System.out.print("Enter menu item ID: ");
        int itemId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter menu item name: ");
        String name = sc.nextLine();
        System.out.print("Enter menu item price: ");
        double price = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Enter menu item category: ");
        String category = sc.nextLine();

        menuItemService.createMenuItem(itemId, name, price, category);
        System.out.println("Menu item added successfully.\n");
    }

    // Update an existing menu item
    private void updateMenuItem() {
        System.out.print("Enter menu item ID to update: ");
        int itemId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new menu item name: ");
        String name = sc.nextLine();
        System.out.print("Enter new menu item price: ");
        double price = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new menu item category: ");
        String category = sc.nextLine();

        menuItemService.updateMenuItem(itemId, name, price, category);
        System.out.println("Menu item updated successfully.\n");
    }

    // Delete a menu item
    private void deleteMenuItem() {
        System.out.print("Enter menu item ID to delete: ");
        int itemId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            menuItemService.deleteMenuItem(itemId);
            System.out.println("Menu item deleted successfully.\n");
        } catch (MenuItemException e) {
            System.out.println("Error deleting menu item: " + e.getMessage() + "\n");
        }
    }

    // View all menu items
    private void viewAllMenuItems() {
        try {
            List<MenuItem> menuItems = menuItemService.getAllMenuItems();
            if (menuItems.isEmpty()) {
                System.out.println("No menu items found.");
            } else {
                printMenuItems(menuItems);
            }
        } catch (MenuItemException e) {
            System.out.println("Error retrieving menu items: " + e.getMessage() + "\n");
        }
    }

    // View menu items by category
    private void viewMenuItemsByCategory() {
        System.out.print("Enter category to view menu items: ");
        String category = sc.nextLine();

        try {
            List<MenuItem> menuItems = menuItemService.getMenuItemsByCategory(category);
            if (menuItems.isEmpty()) {
                System.out.println("No menu items found in this category.");
            } else {
                printMenuItems(menuItems);
            }
        } catch (MenuItemException e) {
            System.out.println("Error retrieving menu items by category: " + e.getMessage() + "\n");
        }
    }

    // View menu items by price range
    private void viewMenuItemsByPriceRange() {
        System.out.print("Enter minimum price: ");
        int minPrice = sc.nextInt();
        System.out.print("Enter maximum price: ");
        int maxPrice = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            List<MenuItem> menuItems = menuItemService.getMenuItemsByPriceRange(minPrice, maxPrice);
            if (menuItems.isEmpty()) {
                System.out.println("No menu items found in this price range.");
            } else {
                printMenuItems(menuItems);
            }
        } catch (MenuItemException e) {
            System.out.println("Error retrieving menu items by price range: " + e.getMessage() + "\n");
        }
    }

    // Print a list of menu items
    private void printMenuItems(List<MenuItem> menuItems) {
        System.out.println("ID\tName\t\tPrice\tCategory");
        for (MenuItem item : menuItems) {
            System.out.println(item.getItemId() + "\t" + item.getName() + "\t" + item.getPrice() + "\t" + item.getCategory());
        }
    }
}

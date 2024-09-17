package RestaurantManagement;


import Exception.InvalidLoginException;
import Model.Admin;
import Service.AdminService;
import Service.MenuItemService;
import Service.TableService;
import Service.OrderService;
import Service.CustomerService;

import java.util.Scanner;

public class AdminManager {
    private final Scanner sc;
    private final AdminService adminService;
    private final MenuItemService menuItemService;
    private final TableService tableService;
    private final OrderService orderService;
    private final CustomerService customerService;
    private boolean isAuthenticated = false;

    public AdminManager(Scanner sc, AdminService adminService) {
        this.sc = sc;
        this.adminService = adminService;
        this.menuItemService = menuItemService;
        this.tableService = tableService;
        this.orderService = orderService;
        this.customerService = customerService;
    }

    // Handle admin login
    public boolean handleAdminLogin() {
        System.out.print("Enter admin username: ");
        String username = sc.nextLine();
        System.out.print("Enter admin password: ");
        String password = sc.nextLine();

        try {
            isAuthenticated = adminService.authenticateAdmin(username, password);
            if (isAuthenticated) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Authentication failed: Invalid username or password.\n");
                return false;
            }
        } catch (InvalidLoginException e) {
            System.out.println("Invalid login details: " + e.getMessage() + "\n");
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage() + "\n");
            return false;
        }
    }

    // Handle account creation for admins
    public void handleAdminAccountCreation() {
        if (adminService.getAdminCount() >= 5) {
            System.out.println("Cannot create more than 5 admin accounts.\n");
            return;
        }

        System.out.print("Admin ID: ");
        int adminId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter admin username/email: ");
        String username = sc.nextLine();
        System.out.print("Enter admin password: ");
        String password = sc.nextLine();

        try {
            Admin admin = new Admin(adminId, username, password);
            adminService.addAdmin(admin);
            System.out.println("Admin account created successfully.\n");

            // Automatically log in the newly created admin
            if (adminService.authenticateAdmin(username, password)) {
                System.out.println("Login successful!\n");
                handleAdminManagement(); // Call the admin management directly after login
            }
        } catch (Exception e) {
            System.out.println("Error creating admin account: " + e.getMessage() + "\n");
        }
    }

    // Handle admin management tasks
    public void handleAdminManagement() {
        if (!isAuthenticated) {
            System.out.println("You need to be logged in as an admin to manage admins.\n");
            return;
        }

        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("Admin Management:\n" +
                    "1. Manage Menu Items\n" +
                    "2. Manage Tables\n" +
                    "3. Manage Orders\n" +
                    "4. Manage Customers\n" +
                    "5. Go Back To Main Menu\n" +
                    "Enter your choice: ");
            int adminChoice = sc.nextInt();
            sc.nextLine();
            switch (adminChoice) {
                case 1 -> manageMenuItems();
                case 2 -> manageTables();
                case 3 -> manageOrders();
                case 4 -> manageCustomers();
                case 5 -> keepRunning = false;
                default -> System.out.println("Invalid choice. Please try again!\n");
            }
        }
    }

    // Methods to manage menu items
    private void manageMenuItems() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("Menu Item Management:\n" +
                    "1. Add Menu Item\n" +
                    "2. Update Menu Item\n" +
                    "3. Delete Menu Item\n" +
                    "4. View All Menu Items\n" +
                    "5. Go Back\n" +
                    "Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> addMenuItem();
                case 2 -> updateMenuItem();
                case 3 -> deleteMenuItem();
                case 4 -> viewAllMenuItems();
                case 5 -> keepRunning = false;
                default -> System.out.println("Invalid choice. Please try again!\n");
            }
        }
    }

    private void addMenuItem() {
        System.out.print("Enter menu item ID: ");
        int itemId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter menu item name: ");
        String name = sc.nextLine();
        System.out.print("Enter menu item price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter menu item category: ");
        String category = sc.nextLine();

        try {
            menuItemService.createMenuItem(itemId, name, price, category);
            System.out.println("Menu item added successfully.\n");
        } catch (Exception e) {
            System.out.println("Error adding menu item: " + e.getMessage() + "\n");
        }
    }

    private void updateMenuItem() {
        System.out.print("Enter menu item ID to update: ");
        int itemId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new menu item name: ");
        String name = sc.nextLine();
        System.out.print("Enter new menu item price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter new menu item category: ");
        String category = sc.nextLine();

        try {
            menuItemService.updateMenuItem(itemId, name, price, category);
            System.out.println("Menu item updated successfully.\n");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage() + "\n");
        }
    }

    private void deleteMenuItem() {
        System.out.print("Enter menu item ID to delete: ");
        int itemId = sc.nextInt();
        sc.nextLine();

        try {
            menuItemService.deleteMenuItem(itemId);
            System.out.println("Menu item deleted successfully.\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private void viewAllMenuItems() {
        try {
            menuItemService.getAllMenuItems().forEach(item -> System.out.println(item + "\n"));
        } catch (Exception e) {
            System.out.println("Error retrieving menu items: " + e.getMessage() + "\n");
        }
    }

    // Methods to manage tables
    private void manageTables() {
        // Implement table management methods here similar to menu item management
    }

    // Methods to manage orders
    private void manageOrders() {
        // Implement order management methods here similar to menu item management
    }

    // Methods to manage customers
    private void manageCustomers() {
        // Implement customer management methods here similar to menu item management
    }
}


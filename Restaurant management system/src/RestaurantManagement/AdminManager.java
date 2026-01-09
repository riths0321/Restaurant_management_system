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
        this.menuItemService = new MenuItemService();
        this.tableService = new TableService();
        this.orderService = new OrderService();
        this.customerService = new CustomerService();
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
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("Table Management:\n" +
                    "1. Add Table\n" +
                    "2. Update Table\n" +
                    "3. Delete Table\n" +
                    "4. View All Tables\n" +
                    "5. View Available Tables\n" +
                    "6. Go Back\n" +
                    "Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> addTable();
                case 2 -> updateTable();
                case 3 -> deleteTable();
                case 4 -> viewAllTables();
                case 5 -> viewAvailableTables();
                case 6 -> keepRunning = false;
                default -> System.out.println("Invalid choice. Please try again!\n");
            }
        }
    }

    private void addTable() {
        System.out.print("Enter table ID: ");
        int tableId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter table capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter table status: ");
        String status = sc.nextLine();

        try {
            Model.Table table = new Model.Table(tableId, capacity, status);
            tableService.creatTable(table);
            System.out.println("Table added successfully.\n");
        } catch (Exception e) {
            System.out.println("Error adding table: " + e.getMessage() + "\n");
        }
    }

    private void updateTable() {
        System.out.print("Enter table ID to update: ");
        int tableId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new status: ");
        String status = sc.nextLine();

        try {
            Model.Table table = new Model.Table(tableId, capacity, status);
            tableService.updateTable(table);
            System.out.println("Table updated successfully.\n");
        } catch (Exception e) {
            System.out.println("Error updating table: " + e.getMessage() + "\n");
        }
    }

    private void deleteTable() {
        System.out.print("Enter table ID to delete: ");
        int tableId = sc.nextInt();
        sc.nextLine();

        try {
            tableService.deleteTable(tableId);
            System.out.println("Table deleted successfully.\n");
        } catch (Exception e) {
            System.out.println("Error deleting table: " + e.getMessage() + "\n");
        }
    }

    private void viewAllTables() {
        try {
            tableService.getAllTables().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error retrieving tables: " + e.getMessage() + "\n");
        }
    }

    private void viewAvailableTables() {
        try {
            tableService.getAvailableTables().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error retrieving available tables: " + e.getMessage() + "\n");
        }
    }

    // Methods to manage orders
    private void manageOrders() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("Order Management:\n" +
                    "1. View All Orders\n" +
                    "2. View Order by ID\n" +
                    "3. Update Order Status\n" +
                    "4. Delete Order\n" +
                    "5. Go Back\n" +
                    "Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> viewAllOrders();
                case 2 -> viewOrderById();
                case 3 -> updateOrderStatus();
                case 4 -> deleteOrder();
                case 5 -> keepRunning = false;
                default -> System.out.println("Invalid choice. Please try again!\n");
            }
        }
    }

    private void viewAllOrders() {
        try {
            orderService.getAllOrders().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error retrieving orders: " + e.getMessage() + "\n");
        }
    }

    private void viewOrderById() {
        System.out.print("Enter order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        try {
            System.out.println(orderService.getOrderById(orderId));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private void updateOrderStatus() {
        System.out.print("Enter order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new status: ");
        String status = sc.nextLine();

        try {
            orderService.updateOrderStatus(orderId, status);
            System.out.println("Order status updated successfully.\n");
        } catch (Exception e) {
            System.out.println("Error updating order: " + e.getMessage() + "\n");
        }
    }

    private void deleteOrder() {
        System.out.print("Enter order ID to delete: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        try {
            orderService.deleteOrder(orderId);
            System.out.println("Order deleted successfully.\n");
        } catch (Exception e) {
            System.out.println("Error deleting order: " + e.getMessage() + "\n");
        }
    }

    // Methods to manage customers
    private void manageCustomers() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("Customer Management:\n" +
                    "1. View All Customers\n" +
                    "2. View Customer by ID\n" +
                    "3. Delete Customer\n" +
                    "4. Go Back\n" +
                    "Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> viewAllCustomers();
                case 2 -> viewCustomerById();
                case 3 -> deleteCustomer();
                case 4 -> keepRunning = false;
                default -> System.out.println("Invalid choice. Please try again!\n");
            }
        }
    }

    private void viewAllCustomers() {
        try {
            customerService.getAllCustomers().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error retrieving customers: " + e.getMessage() + "\n");
        }
    }

    private void viewCustomerById() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine();

        try {
            System.out.println(customerService.getCustomerById(customerId));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private void deleteCustomer() {
        System.out.print("Enter customer ID to delete: ");
        int customerId = sc.nextInt();
        sc.nextLine();

        try {
            customerService.deleteCustomer(customerId);
            System.out.println("Customer deleted successfully.\n");
        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage() + "\n");
        }
    }
}


package Operation;

import Exception.MenuItemException;
import Exception.TableException;
import Exception.OrderException;
import Exception.AdminNotFoundException;
import RestaurantManagement.AdminManager;
import RestaurantManagement.TableManager;
import RestaurantManagement.MenuManager;
import RestaurantManagement.OrderManager;
import RestaurantManagement.CustomerManager;
import Service.AdminService;
import Service.MenuItemService;
import Service.TableService;
import Service.OrderService;
import Service.CustomerService;

import java.util.Scanner;

public class AdminOperation {
    private final Scanner sc;
    private final AdminService adminService;
    private final MenuItemService menuItemService;
    private final TableService tableService;
    private final OrderService orderService;
    private final CustomerService customerService;

    public AdminOperation(Scanner sc, AdminService adminService, MenuItemService menuItemService, TableService tableService, OrderService orderService, CustomerService customerService) {
        this.sc = sc;
        this.adminService = adminService;
        this.menuItemService = menuItemService;
        this.tableService = tableService;
        this.orderService = orderService;
        this.customerService = customerService;
    }

    public void handleAdminFunctionalities() throws MenuItemException, TableException, OrderException, AdminNotFoundException {
        AdminManager adminManager = new AdminManager(sc, adminService);
        MenuManager menuManager = new MenuManager(sc, menuItemService);
        TableManager tableManager = new TableManager(sc, tableService);
        OrderManager orderManager = new OrderManager(sc, orderService, tableService);
        CustomerManager customerManager = new CustomerManager(sc, customerService);

        boolean adminLoggedIn = false;
        while (true) {
            if (!adminLoggedIn) {
                System.out.println("Restaurant Management System Menu:\n1. Admin Login\n2. Create Admin Account\n3. Exit\nEnter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Clear the newline character from the buffer

                switch (choice) {
                    case 1 -> adminLoggedIn = adminManager.handleAdminLogin();
                    case 2 -> adminManager.handleAdminAccountCreation();
                    case 3 -> {
                        System.out.println("Exiting...");
                        return; // Exit the application
                    }
                    default -> System.out.println("Invalid choice. Please try again!");
                }
            } else {
                boolean stayLoggedIn = true;
                while (stayLoggedIn) {
                    System.out.println("Admin Menu:\n1. Manage Menus\n2. Manage Tables\n3. Manage Orders\n4. Manage Customers\n5. Logout\nEnter your choice:");
                    int adminChoice = sc.nextInt();
                    sc.nextLine(); // Clear the newline character from the buffer

                    switch (adminChoice) {
                        case 1 -> menuManager.handleMenuManagement(); // Calls the Menu Management method
                        case 2 -> tableManager.handleTableManagement(); // Calls the Table Management method
                        case 3 -> orderManager.handleOrderManagement(); // Calls the Order Management method
                        case 4 -> customerManager.handleCustomerManagement(); // Calls the Customer Management method
                        case 5 -> {
                            adminLoggedIn = false;
                            stayLoggedIn = false; // Exit Admin Menu loop
                            System.out.println("Logged out successfully.");
                        }
                        default -> System.out.println("Invalid choice. Please try again!");
                    }
                }
            }
        }
    }
}


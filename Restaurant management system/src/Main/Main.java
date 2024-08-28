package Main;
import DAO.*;
import Exception.*;
import Model.*;
import DAOIMPL.*;
import Utility.DBConnection;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize DAOs
        TableDao tableDao = new TableDaoImpl();
        MenuItemDao menuItemDao = new MenuItemDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        CustomerDao customerDao = new CustomerDaoImpl();

        while (true) {
            System.out.println("\nRestaurant Management System");
            System.out.println("1. Manage Tables");
            System.out.println("2. Manage Menu Items");
            System.out.println("3. Manage Orders");
            System.out.println("4. Manage Order Items");
            System.out.println("5. Manage Customers");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageTables(tableDao);
                    break;
                case 2:
                    manageMenuItems(menuItemDao);
                    break;
                case 3:
                    manageOrders(orderDao);
                    break;
                case 4:
                    manageOrderItems(orderItemDao);
                    break;
                case 5:
                    manageCustomers(customerDao);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    DBConnection.closeConnection();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void manageTables(TableDao tableDao) {
        System.out.println("\nManage Tables");
        System.out.println("1. Add Table");
        System.out.println("2. View Table");
        System.out.println("3. View All Tables");
        System.out.println("4. Update Table");
        System.out.println("5. Delete Table");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter Table ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Status: ");
                    String status = scanner.nextLine();

                    Table newTable = new Table(id, capacity, status);
                    tableDao.createTable(newTable);
                    System.out.println("Table added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Table ID to view: ");
                    int viewId = scanner.nextInt();
                    Table table = tableDao.getTableById(viewId);
                    System.out.println(table);
                    break;
                case 3:
                    List<Table> tables = tableDao.getAllTables();
                    tables.forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter Table ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Capacity: ");
                    int newCapacity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Status: ");
                    String newStatus = scanner.nextLine();

                    Table updateTable = new Table(updateId, newCapacity, newStatus);
                    tableDao.updateTable(updateTable);
                    System.out.println("Table updated successfully.");
                    break;
                case 5:
                    System.out.print("Enter Table ID to delete: ");
                    int deleteId = scanner.nextInt();
                    tableDao.deleteTable(deleteId);
                    System.out.println("Table deleted successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (TableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void manageMenuItems(MenuItemDao menuItemDao) {
        // Similar structure to manageTables but for Menu Items
    }

    private static void manageOrders(OrderDao orderDao) {
        // Similar structure to manageTables but for Orders
    }

    private static void manageOrderItems(OrderItemDao orderItemDao) {
        // Similar structure to manageTables but for Order Items
    }

    private static void manageCustomers(CustomerDao customerDao) {
        // Similar structure to manageTables but for Customers
    }
}


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
        System.out.println("\nManage Menu Items");
        System.out.println("1. Add Menu Item");
        System.out.println("2. View Menu Item");
        System.out.println("3. View All Menu Items");
        System.out.println("4. Update Menu Item");
        System.out.println("5. Delete Menu Item");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter Item ID: ");
                    int itemId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Item Name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter Item Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    int price = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();

                    MenuItem newItem = new MenuItem(itemId, itemName, description, price, category);
                    menuItemDao.createMenuItem(newItem);
                    System.out.println("Menu item added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Item ID to view: ");
                    int viewItemId = scanner.nextInt();
                    MenuItem item = menuItemDao.getMenuItemById(viewItemId);
                    System.out.println(item);
                    break;
                case 3:
                    List<MenuItem> items = menuItemDao.getAllMenuItems();
                    items.forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter Item ID to update: ");
                    int updateItemId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Item Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new Description: ");
                    String newDesc = scanner.nextLine();
                    System.out.print("Enter new Price: ");
                    int newPrice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Category: ");
                    String newCategory = scanner.nextLine();

                    MenuItem updateItem = new MenuItem(updateItemId, newName, newDesc, newPrice, newCategory);
                    menuItemDao.updateMenuItem(updateItem);
                    System.out.println("Menu item updated successfully.");
                    break;
                case 5:
                    System.out.print("Enter Item ID to delete: ");
                    int deleteItemId = scanner.nextInt();
                    menuItemDao.deleteMenuItem(deleteItemId);
                    System.out.println("Menu item deleted successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (MenuItemException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void manageOrders(OrderDao orderDao) {
        System.out.println("\nManage Orders");
        System.out.println("1. Add Order");
        System.out.println("2. View Order");
        System.out.println("3. View All Orders");
        System.out.println("4. Update Order");
        System.out.println("5. Delete Order");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter Order ID: ");
                    int orderId = scanner.nextInt();
                    System.out.print("Enter Customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Order Date (YYYY-MM-DD): ");
                    String dateStr = scanner.nextLine();
                    System.out.print("Enter Total Amount: ");
                    double totalAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Status: ");
                    String status = scanner.nextLine();

                    Order newOrder = new Order(orderId, customerId, java.time.LocalDate.parse(dateStr), totalAmount, status);
                    orderDao.createOrder(newOrder);
                    System.out.println("Order added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Order ID to view: ");
                    int viewOrderId = scanner.nextInt();
                    Order order = orderDao.getOrderById(viewOrderId);
                    System.out.println(order);
                    break;
                case 3:
                    List<Order> orders = orderDao.getAllOrders();
                    orders.forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter Order ID to update: ");
                    int updateOrderId = scanner.nextInt();
                    System.out.print("Enter new Customer ID: ");
                    int newCustomerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Order Date (YYYY-MM-DD): ");
                    String newDateStr = scanner.nextLine();
                    System.out.print("Enter new Total Amount: ");
                    double newTotalAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Status: ");
                    String newStatus = scanner.nextLine();

                    Order updateOrder = new Order(updateOrderId, newCustomerId, java.time.LocalDate.parse(newDateStr), newTotalAmount, newStatus);
                    orderDao.updateOrder(updateOrder);
                    System.out.println("Order updated successfully.");
                    break;
                case 5:
                    System.out.print("Enter Order ID to delete: ");
                    int deleteOrderId = scanner.nextInt();
                    orderDao.deleteOrder(deleteOrderId);
                    System.out.println("Order deleted successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (OrderException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void manageOrderItems(OrderItemDao orderItemDao) {
        System.out.println("\nManage Order Items");
        System.out.println("1. Add Order Item");
        System.out.println("2. View Order Item");
        System.out.println("3. View All Order Items");
        System.out.println("4. Update Order Item");
        System.out.println("5. Delete Order Item");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter Order Item ID: ");
                    int orderItemId = scanner.nextInt();
                    System.out.print("Enter Order ID: ");
                    int orderId = scanner.nextInt();
                    System.out.print("Enter Menu Item ID: ");
                    int itemId = scanner.nextInt();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    OrderItem newOrderItem = new OrderItem(orderItemId, orderId, itemId, quantity, price);
                    orderItemDao.createOrderItem(newOrderItem);
                    System.out.println("Order item added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Order Item ID to view: ");
                    int viewOrderItemId = scanner.nextInt();
                    OrderItem orderItem = orderItemDao.getOrderItemById(viewOrderItemId);
                    System.out.println(orderItem);
                    break;
                case 3:
                    List<OrderItem> orderItems = orderItemDao.getAllOrderItems();
                    orderItems.forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter Order Item ID to update: ");
                    int updateOrderItemId = scanner.nextInt();
                    System.out.print("Enter new Order ID: ");
                    int newOrderId = scanner.nextInt();
                    System.out.print("Enter new Menu Item ID: ");
                    int newItemId = scanner.nextInt();
                    System.out.print("Enter new Quantity: ");
                    int newQuantity = scanner.nextInt();
                    System.out.print("Enter new Price: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    OrderItem updateOrderItem = new OrderItem(updateOrderItemId, newOrderId, newItemId, newQuantity, newPrice);
                    orderItemDao.updateOrderItem(updateOrderItem);
                    System.out.println("Order item updated successfully.");
                    break;
                case 5:
                    System.out.print("Enter Order Item ID to delete: ");
                    int deleteOrderItemId = scanner.nextInt();
                    orderItemDao.deleteOrderItem(deleteOrderItemId);
                    System.out.println("Order item deleted successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (OrderItemException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void manageCustomers(CustomerDao customerDao) {
        System.out.println("\nManage Customers");
        System.out.println("1. Add Customer");
        System.out.println("2. View Customer");
        System.out.println("3. View All Customers");
        System.out.println("4. Update Customer");
        System.out.println("5. Delete Customer");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int custId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Customer Name: ");
                    String custName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    Customer newCustomer = new Customer(custId, custName, email, password, phoneNumber);
                    customerDao.createCustomer(newCustomer);
                    System.out.println("Customer added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Customer ID to view: ");
                    int viewCustId = scanner.nextInt();
                    Customer customer = customerDao.getCustomerDetailsById(viewCustId);
                    System.out.println(customer);
                    break;
                case 3:
                    List<Customer> customers = customerDao.getAllCustomerDetails();
                    customers.forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter Customer ID to update: ");
                    int updateCustId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Name: ");
                    String newCustName = scanner.nextLine();
                    System.out.print("Enter new Email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter new Password: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Enter new Phone Number: ");
                    String newPhoneNumber = scanner.nextLine();

                    Customer updateCustomer = new Customer(updateCustId, newCustName, newEmail, newPassword, newPhoneNumber);
                    customerDao.updateCustomerDetails(updateCustomer);
                    System.out.println("Customer updated successfully.");
                    break;
                case 5:
                    System.out.print("Enter Customer ID to delete: ");
                    int deleteCustId = scanner.nextInt();
                    customerDao.deleteCustomer(deleteCustId);
                    System.out.println("Customer deleted successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (CustomerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


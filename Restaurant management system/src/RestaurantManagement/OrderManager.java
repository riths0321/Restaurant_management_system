package RestaurantManagement;

import Exception.OrderException;
import Model.Order;
import Service.OrderService;
import Service.TableService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class OrderManager {
    private final Scanner sc;
    private final OrderService orderService;

    public OrderManager(Scanner sc, OrderService orderService, TableService tableService) {
        this.sc = sc;
        this.orderService = new OrderService();
    }

    public OrderManager(Scanner sc, OrderService orderService, Scanner sc1, OrderService orderService1) {
        this.sc = sc1;
        this.orderService = orderService1;
    }

    // Handle order management operations
    public void handleOrderManagement() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("Order Management:\n" +
                    "1. Create Order\n" +
                    "2. Update Order\n" +
                    "3. Delete Order\n" +
                    "4. View All Orders\n" +
                    "5. View Order by ID\n" +
                    "6. View Orders by Customer ID\n" +
                    "7. View Orders by Date Range\n" +
                    "8. View Orders by Status\n" +
                    "9. Update Order Status\n" +
                    "10. View Total Sales Amount\n" +
                    "11. Go Back\n" +
                    "Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> createOrder();
                case 2 -> updateOrder();
                case 3 -> deleteOrder();
                case 4 -> viewAllOrders();
                case 5 -> viewOrderById();
                case 6 -> viewOrdersByCustomerId();
                case 7 -> viewOrdersByDateRange();
                case 8 -> viewOrdersByStatus();
                case 9 -> updateOrderStatus();
                case 10 -> viewTotalSalesAmount();
                case 11 -> keepRunning = false;
                default -> System.out.println("Invalid choice. Please try again!\n");
            }
        }
    }

    // Create a new order
    private void createOrder() {
        System.out.print("Enter order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter total amount: ");
        double totalAmount = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Enter order date (yyyy-mm-dd): ");
        String dateInput = sc.nextLine();
        LocalDate orderDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.print("Enter order status: ");
        String status = sc.nextLine();

        try {
            Order order = new Order(orderId, customerId, orderDate, totalAmount, status);
            orderService.createOrder(order);
            System.out.println("Order created successfully.\n");
        } catch (OrderException e) {
            System.out.println("Error creating order: " + e.getMessage() + "\n");
        }
    }

    // Update an existing order
    private void updateOrder() {
        System.out.print("Enter order ID to update: ");
        int orderId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new total amount: ");
        double totalAmount = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new order date (yyyy-mm-dd): ");
        String dateInput = sc.nextLine();
        LocalDate orderDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.print("Enter new order status: ");
        String status = sc.nextLine();

        try {
            Order order = new Order(orderId, customerId, orderDate, totalAmount, status);
            orderService.updateOrder(order);
            System.out.println("Order updated successfully.\n");
        } catch (OrderException e) {
            System.out.println("Error updating order: " + e.getMessage() + "\n");
        }
    }

    // Delete an order
    private void deleteOrder() {
        System.out.print("Enter order ID to delete: ");
        int orderId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            orderService.deleteOrder(orderId);
            System.out.println("Order deleted successfully.\n");
        } catch (OrderException e) {
            System.out.println("Error deleting order: " + e.getMessage() + "\n");
        }
    }

    // View all orders
    private void viewAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            if (orders.isEmpty()) {
                System.out.println("No orders found.");
            } else {
                printOrderList(orders);
            }
        } catch (OrderException e) {
            System.out.println("Error retrieving orders: " + e.getMessage() + "\n");
        }
    }

    // View order by ID
    private void viewOrderById() {
        System.out.print("Enter order ID to view: ");
        int orderId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            Order order = orderService.getOrderById(orderId);
            printOrderDetails(order);
        } catch (OrderException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    // View orders by customer ID
    private void viewOrdersByCustomerId() {
        System.out.print("Enter customer ID to view orders: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            List<Order> orders = orderService.getOrdersByCustomerId(customerId);
            if (orders.isEmpty()) {
                System.out.println("No orders found for the specified customer.");
            } else {
                printOrderList(orders);
            }
        } catch (OrderException e) {
            System.out.println("Error retrieving orders by customer ID: " + e.getMessage() + "\n");
        }
    }

    // View orders by date range
    private void viewOrdersByDateRange() {
        System.out.print("Enter start date (yyyy-mm-dd): ");
        String startDateInput = sc.nextLine();
        LocalDate startDate = LocalDate.parse(startDateInput, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Enter end date (yyyy-mm-dd): ");
        String endDateInput = sc.nextLine();
        LocalDate endDate = LocalDate.parse(endDateInput, DateTimeFormatter.ISO_LOCAL_DATE);

        try {
            List<Order> orders = orderService.getOrdersByDateRange(startDate, endDate);
            if (orders.isEmpty()) {
                System.out.println("No orders found in the specified date range.");
            } else {
                printOrderList(orders);
            }
        } catch (OrderException e) {
            System.out.println("Error retrieving orders by date range: " + e.getMessage() + "\n");
        }
    }

    // View orders by status
    private void viewOrdersByStatus() {
        System.out.print("Enter order status to view: ");
        String status = sc.nextLine();

        try {
            List<Order> orders = orderService.getOrdersByStatus(status);
            if (orders.isEmpty()) {
                System.out.println("No orders found with the specified status.");
            } else {
                printOrderList(orders);
            }
        } catch (OrderException e) {
            System.out.println("Error retrieving orders by status: " + e.getMessage() + "\n");
        }
    }

    // Update order status
    private void updateOrderStatus() {
        System.out.print("Enter order ID to update status: ");
        int orderId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new status: ");
        String newStatus = sc.nextLine();

        try {
            orderService.updateOrderStatus(orderId, newStatus);
            System.out.println("Order status updated successfully.\n");
        } catch (OrderException e) {
            System.out.println("Error updating order status: " + e.getMessage() + "\n");
        }
    }

    // View total sales amount
    private void viewTotalSalesAmount() {
        double totalSales = orderService.getTotalSalesAmount();
        System.out.println("Total Sales Amount: " + totalSales + "\n");
    }

    // Print a list of orders
    private void printOrderList(List<Order> orders) {
        System.out.println("ID\tCustomer ID\tTotal Amount\tDate\t\tStatus");
        for (Order order : orders) {
            printOrderDetails(order);
        }
    }

    // Print details of a single order
    private void printOrderDetails(Order order) {
        System.out.println(order.getOrderId() + "\t" +
                order.getCustomerId() + "\t\t" +
                order.getTotalAmount() + "\t\t" +
                order.getOrderDate() + "\t" +
                order.getStatus());
    }
}

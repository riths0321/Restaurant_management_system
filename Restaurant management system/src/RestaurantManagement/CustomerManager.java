package RestaurantManagement;

import Exception.CustomerException;
import Exception.InvalidLoginException;
import Model.Customer;
import Service.CustomerService;

import java.util.List;
import java.util.Scanner;

public class CustomerManager {
    private final Scanner sc;
    private final CustomerService customerService;

    public CustomerManager(Scanner sc, CustomerService customerService) {
        this.sc = sc;
        this.customerService = new CustomerService();
    }

    public CustomerManager(Scanner sc, Object customerService, Scanner sc1, CustomerService customerService1) {
        this.sc = sc1;
        this.customerService = customerService1;
    }

    // Handle customer management operations
    public void handleCustomerManagement() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("Customer Management:\n" +
                    "1. Authenticate Customer\n" +
                    "2. View Customer by ID\n" +
                    "3. View All Customers\n" +
                    "4. Create Customer\n" +
                    "5. Update Customer\n" +
                    "6. Delete Customer\n" +
                    "7. Change Customer Password\n" +
                    "8. Get Customer Count\n" +
                    "9. Go Back\n" +
                    "Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> authenticateCustomer();
                case 2 -> viewCustomerById();
                case 3 -> viewAllCustomers();
                case 4 -> createCustomer();
                case 5 -> updateCustomer();
                case 6 -> deleteCustomer();
                case 7 -> changeCustomerPassword();
                case 8 -> getCustomerCount();
                case 9 -> keepRunning = false;
                default -> System.out.println("Invalid choice. Please try again!\n");
            }
        }
    }

    // Authenticate a customer
    private void authenticateCustomer() {
        System.out.print("Enter customer email: ");
        String email = sc.nextLine();
        System.out.print("Enter customer password: ");
        String password = sc.nextLine();

        try {
            boolean isAuthenticated = customerService.authenticateCustomer(email, password);
            if (isAuthenticated) {
                System.out.println("Authentication successful.\n");
            }
        } catch (InvalidLoginException e) {
            System.out.println("Authentication failed: " + e.getMessage() + "\n");
        }
    }

    // View a customer by ID
    private void viewCustomerById() {
        System.out.print("Enter customer ID to view: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            Customer customer = customerService.getCustomerById(customerId);
            printCustomerDetails(customer);
        } catch (CustomerException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    // View all customers
    private void viewAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            if (customers.isEmpty()) {
                System.out.println("No customers found.");
            } else {
                printCustomerList(customers);
            }
        } catch (CustomerException e) {
            System.out.println("Error retrieving customers: " + e.getMessage() + "\n");
        }
    }

    // Create a new customer
    private void createCustomer() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();
        System.out.print("Enter customer email: ");
        String email = sc.nextLine();
        System.out.print("Enter customer password: ");
        String password = sc.nextLine();

        try {
            Customer customer = new Customer(customerId, name, email, password);
            customerService.Customer(customer);
            System.out.println("Customer created successfully.\n");
        } catch (InvalidLoginException | CustomerException e) {
            System.out.println("Error creating customer: " + e.getMessage() + "\n");
        }
    }

    // Update customer details
    private void updateCustomer() {
        System.out.print("Enter customer ID to update: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new customer name: ");
        String name = sc.nextLine();
        System.out.print("Enter new customer email: ");
        String email = sc.nextLine();
        System.out.print("Enter new customer password: ");
        String password = sc.nextLine();

        try {
            Customer customer = new Customer(customerId, name, email, password);
            customerService.updateCustomerDetails(customer);
            System.out.println("Customer updated successfully.\n");
        } catch (CustomerException e) {
            System.out.println("Error updating customer: " + e.getMessage() + "\n");
        }
    }

    // Delete a customer
    private void deleteCustomer() {
        System.out.print("Enter customer ID to delete: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            customerService.deleteCustomer(customerId);
            System.out.println("Customer deleted successfully.\n");
        } catch (CustomerException e) {
            System.out.println("Error deleting customer: " + e.getMessage() + "\n");
        }
    }

    // Change customer password
    private void changeCustomerPassword() {
        System.out.print("Enter customer ID to change password: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new password: ");
        String newPassword = sc.nextLine();

        try {
            customerService.changeCustomerPassword(customerId, newPassword);
            System.out.println("Password changed successfully.\n");
        } catch (InvalidLoginException e) {
            System.out.println("Error changing password: " + e.getMessage() + "\n");
        }
    }

    // Get customer count
    private void getCustomerCount() {
        int customerCount = customerService.getCustomerCount();
        System.out.println("Total number of customers: " + customerCount + "\n");
    }

    // Print a list of customers
    private void printCustomerList(List<Customer> customers) {
        System.out.println("ID\tName\tEmail");
        for (Customer customer : customers) {
            printCustomerDetails(customer);
        }
    }

    // Print details of a single customer
    private void printCustomerDetails(Customer customer) {
        System.out.println(customer.getCustomerId() + "\t" +
                customer.getName() + "\t" +
                customer.getEmail());
    }
}

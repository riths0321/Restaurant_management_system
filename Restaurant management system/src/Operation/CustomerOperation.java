package Operation;

import Exception.CustomerException;
import Exception.InvalidLoginException;
import Model.Customer;
import Service.CustomerService;

import java.util.Scanner;

public class CustomerOperation {
    private Scanner sc;
    private final CustomerService customerService;

    public CustomerOperation(Scanner sc, CustomerService customerService) {
        this.sc = sc;
        this.customerService = customerService;
    }

    // Handle customer menu
    public void handleCustomerMenu() {
        boolean userLoggedIn = false;

        while (true) {
            if (!userLoggedIn) {
                System.out.println("Customer Menu:");
                System.out.println("1. Login");
                System.out.println("2. Create Account");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> userLoggedIn = login();
                    case 2 -> createAccount();
                    case 3 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Customer Menu:\n" +
                        "1. View Account Details\n" +
                        "2. Update Account Details\n" +
                        "3. Delete Account\n" +
                        "4. Change Password\n" +
                        "5. Logout\n" +
                        "Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> viewAccountDetails();
                    case 2 -> updateAccountDetails();
                    case 3 -> deleteAccount();
                    case 4 -> changePassword();
                    case 5 -> {
                        userLoggedIn = false;
                        System.out.println("Logged out successfully.");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    // Customer login
    private boolean login() {
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        try {
            boolean success = customerService.authenticateCustomer(email, password);
            if (success) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Invalid credentials.");
                return false;
            }
        } catch (InvalidLoginException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Create a new customer account
    private void createAccount() {
        System.out.print("Enter customer ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        try {
            Customer customer = new Customer(id, name, email, phoneNumber, password);
            customerService.Customer(customer);
            System.out.println("Account created successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // View account details
    private void viewAccountDetails() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            Customer customer = customerService.getCustomerById(customerId);
            if (customer != null) {
                System.out.println("Customer Details:");
                System.out.println("ID: " + customer.getCustomerId());
                System.out.println("Name: " + customer.getName());
                System.out.println("Email: " + customer.getEmail());
            } else {
                System.out.println("Customer not found.");
            }
        } catch (CustomerException e) {
            System.out.println("Error retrieving customer details: " + e.getMessage());
        }
    }

    // Update account details
    private void updateAccountDetails() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        System.out.print("Enter new email: ");
        String email = sc.nextLine();
        System.out.print("Enter new phone number: ");
        String phoneNumber = sc.nextLine();

        try {
            Customer customer = new Customer(customerId, name, email, phoneNumber);
            customerService.updateCustomerDetails(customer);
            System.out.println("Customer details updated successfully.");
        } catch (CustomerException e) {
            System.out.println("Error updating customer details: " + e.getMessage());
        }
    }

    // Delete account
    private void deleteAccount() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            customerService.deleteCustomer(customerId);
            System.out.println("Customer account deleted successfully.");
        } catch (CustomerException e) {
            System.out.println("Error deleting customer account: " + e.getMessage());
        }
    }

    // Change password
    private void changePassword() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new password: ");
        String newPassword = sc.nextLine();

        try {
            customerService.changeCustomerPassword(customerId, newPassword);
            System.out.println("Password changed successfully.");
        } catch (InvalidLoginException e) {
            System.out.println("Error changing password: " + e.getMessage());
        }
    }
}

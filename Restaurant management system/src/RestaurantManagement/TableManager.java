package RestaurantManagement;

import Exception.TableException;
import Model.Table;
import Service.TableService;

import java.util.List;
import java.util.Scanner;

public class TableManager {
    private final Scanner sc;
    private final TableService tableService;

    public TableManager(Scanner sc, TableService tableService) {
        this.sc = sc;
        this.tableService = new TableService();
    }

    public TableManager(Scanner sc, TableService tableService, Scanner sc1, TableService tableService1) {
        this.sc = sc1;
        this.tableService = tableService1;
    }

    // Handle table management operations
    public void handleTableManagement() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("Table Management:\n" +
                    "1. Add Table\n" +
                    "2. Update Table\n" +
                    "3. Delete Table\n" +
                    "4. View All Tables\n" +
                    "5. View Table by ID\n" +
                    "6. View Tables by Capacity\n" +
                    "7. View Available Tables\n" +
                    "8. Update Table Status\n" +
                    "9. Go Back\n" +
                    "Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> addTable();
                case 2 -> updateTable();
                case 3 -> deleteTable();
                case 4 -> viewAllTables();
                case 5 -> viewTableById();
                case 6 -> viewTablesByCapacity();
                case 7 -> viewAvailableTables();
                case 8 -> updateTableStatus();
                case 9 -> keepRunning = false;
                default -> System.out.println("Invalid choice. Please try again!\n");
            }
        }
    }

    // Add a new table
    private void addTable() {
        System.out.print("Enter table ID: ");
        int tableId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter table capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter table status (Available/Occupied): ");
        String status = sc.nextLine();

        try {
            Table table = new Table(tableId, capacity, status);
            tableService.creatTable(table);
            System.out.println("Table added successfully.\n");
        } catch (TableException e) {
            System.out.println("Error adding table: " + e.getMessage() + "\n");
        }
    }

    // Update an existing table
    private void updateTable() {
        System.out.print("Enter table ID to update: ");
        int tableId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new table capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new table status (Available/Occupied): ");
        String status = sc.nextLine();

        try {
            Table table = new Table(tableId, capacity, status);
            tableService.updateTable(table);
            System.out.println("Table updated successfully.\n");
        } catch (TableException e) {
            System.out.println("Error updating table: " + e.getMessage() + "\n");
        }
    }

    // Delete a table
    private void deleteTable() {
        System.out.print("Enter table ID to delete: ");
        int tableId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            tableService.deleteTable(tableId);
            System.out.println("Table deleted successfully.\n");
        } catch (TableException e) {
            System.out.println("Error deleting table: " + e.getMessage() + "\n");
        }
    }

    // View all tables
    private void viewAllTables() {
        try {
            List<Table> tables = tableService.getAllTables();
            if (tables.isEmpty()) {
                System.out.println("No tables found.");
            } else {
                printTableList(tables);
            }
        } catch (TableException e) {
            System.out.println("Error retrieving tables: " + e.getMessage() + "\n");
        }
    }

    // View table by ID
    private void viewTableById() {
        System.out.print("Enter table ID to view: ");
        int tableId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            Table table = tableService.getTableById(tableId);
            printTableDetails(table);
        } catch (TableException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    // View tables by capacity
    private void viewTablesByCapacity() {
        System.out.print("Enter the capacity to view tables: ");
        int capacity = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            List<Table> tables = tableService.getTablesByCapacity(capacity);
            if (tables.isEmpty()) {
                System.out.println("No tables found with the specified capacity.");
            } else {
                printTableList(tables);
            }
        } catch (TableException e) {
            System.out.println("Error retrieving tables by capacity: " + e.getMessage() + "\n");
        }
    }

    // View available tables
    private void viewAvailableTables() {
        try {
            List<Table> tables = tableService.getAvailableTables();
            if (tables.isEmpty()) {
                System.out.println("No available tables found.");
            } else {
                printTableList(tables);
            }
        } catch (TableException e) {
            System.out.println("Error retrieving available tables: " + e.getMessage() + "\n");
        }
    }

    // Update table status
    private void updateTableStatus() {
        System.out.print("Enter table ID to update status: ");
        int tableId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new status (Available/Occupied): ");
        String newStatus = sc.nextLine();

        try {
            tableService.updateTable(tableId, newStatus);
            System.out.println("Table status updated successfully.\n");
        } catch (TableException e) {
            System.out.println("Error updating table status: " + e.getMessage() + "\n");
        }
    }

    // Print a list of tables
    private void printTableList(List<Table> tables) {
        System.out.println("ID\tCapacity\tStatus");
        for (Table table : tables) {
            printTableDetails(table);
        }
    }

    // Print details of a single table
    private void printTableDetails(Table table) {
        System.out.println(table.getTableId() + "\t" + table.getCapacity() + "\t\t" + table.getStatus());
    }
}


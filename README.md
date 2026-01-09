# Restaurant Management System

## Project Overview
A comprehensive Java-based restaurant management system that enables efficient management of tables, menus, orders, customers, and administrators. The system implements the DAO (Data Access Object) design pattern for clean separation of business logic and data persistence layers.

## Features
- **Admin Management**: Secure admin authentication and account management
- **Customer Management**: Customer registration, login, and profile management
- **Table Management**: Track and manage dining tables with capacity and availability status
- **Menu Management**: Manage menu items with categories and pricing
- **Order Management**: Create, track, and manage customer orders
- **Order Items**: Manage individual items within orders with pricing and quantity tracking
- **Database Integration**: MySQL database for persistent data storage

## Technology Stack
- **Language**: Java 8+
- **Database**: MySQL
- **Architecture Pattern**: DAO (Data Access Object)
- **Build Tool**: Maven (optional)

## Project Structure
```
src/
├── Main/
│   └── Main.java                    # Application entry point
├── Model/
│   ├── Admin.java
│   ├── Customer.java
│   ├── MenuItem.java
│   ├── Order.java
│   ├── OrderItem.java
│   └── Table.java
├── DAO/                             # Data Access Object Interfaces
│   ├── AdminDao.java
│   ├── CustomerDao.java
│   ├── MenuItemDao.java
│   ├── OrderDao.java
│   ├── OrderItemDao.java
│   └── TableDao.java
├── DAOIMPL/                         # DAO Implementations
│   ├── AdminDaoImpl.java
│   ├── CustomerDaoImpl.java
│   ├── MenuItemDaoImpl.java
│   ├── OrderDaoImpl.java
│   ├── OrderItemDaoImpl.java
│   └── TableDaoImpl.java
├── Service/                         # Business Logic Layer
│   ├── AdminService.java
│   ├── CustomerService.java
│   ├── MenuItemService.java
│   ├── OrderService.java
│   ├── OrderItemService.java
│   └── TableService.java
├── Exception/                       # Custom Exceptions
│   ├── AdminNotFoundException.java
│   ├── CustomerException.java
│   ├── DatabaseException.java
│   ├── InvalidDataException.java
│   ├── InvalidLoginException.java
│   ├── MenuItemException.java
│   ├── OrderException.java
│   ├── OrderItemException.java
│   └── TableException.java
├── Utility/
│   ├── DBConnection.java            # Database connection singleton
│   └── DAO.java
├── RestaurantManagement/            # Manager Classes
│   ├── AdminManager.java
│   ├── CustomerManager.java
│   ├── MenuManager.java
│   ├── OrderManager.java
│   └── TableManager.java
└── Operation/                       # Operation Classes
    ├── AdminOperation.java
    └── CustomerOperation.java
```

## Setup Instructions

### Prerequisites
- Java 8 or higher installed
- MySQL 5.7 or higher installed and running
- MySQL JDBC Driver (already configured in the project)

### Database Setup
1. Open MySQL command line or MySQL Workbench
2. Run the SQL script to create the database and tables:
   ```sql
   SOURCE path/to/database_setup.sql;
   ```
   Or copy and execute the contents of `database_setup.sql` file

3. Verify the database creation:
   ```sql
   USE restaurant_management_system;
   SHOW TABLES;
   ```

### Database Configuration
Edit the database connection credentials in `Utility/DBConnection.java`:
```java
private static final String url = "jdbc:mysql://localhost:3306/restaurant_management_system";
private static final String user = "root";
private static final String password = "6264@#Mysql";
```

Replace the values with your actual MySQL credentials.

### Running the Application
1. Compile the project:
   ```bash
   javac -d bin src/**/*.java
   ```

2. Run the main application:
   ```bash
   java -cp bin Main.Main
   ```

## API/Features Usage

### Admin Operations
- Login as admin with username and password
- Manage menu items (Add, Update, Delete, View)
- Manage tables (Add, Update, Delete, View)
- Manage orders (View, Update status)
- Manage customers

### Customer Operations
- Register and create customer account
- Login with email and password
- View personal details
- Update profile information
- Change password
- Delete account

### Table Management
- Add new dining tables with capacity
- Update table information
- Delete tables
- View all tables
- Check table availability
- Update table status (Available/Occupied)

### Menu Management
- Add menu items with price and category
- Update menu item details
- Delete items
- View menu by category
- Filter items by price range

### Order Management
- Create new orders
- Track order status
- View orders by customer or date range
- Update order details
- Calculate total sales

## Database Schema

### Tables
- **admin**: Stores admin user credentials
- **customers**: Customer account information
- **tables**: Restaurant table details
- **menu_items**: Menu items with pricing
- **orders**: Order records with customer and status info
- **order_items**: Individual items in each order

## Sample Data
The database setup includes sample data for testing:
- 2 admin accounts
- 5 dining tables with varying capacities
- 8 menu items with categories and prices

## Error Handling
The system includes comprehensive custom exceptions:
- `AdminNotFoundException`: When admin record is not found
- `CustomerException`: For customer-related errors
- `InvalidLoginException`: For authentication failures
- `MenuItemException`: For menu item operations
- `OrderException`: For order-related errors
- `TableException`: For table management errors

## Default Credentials
**Admin Account** (for testing):
- Username: `admin1`
- Password: `admin123`

## Future Enhancements
- Payment gateway integration
- Reservation system
- Multiple location support
- Analytics and reporting
- Mobile app integration
- Real-time order tracking

## Notes
- Database connection uses singleton pattern for efficiency
- All database operations use prepared statements to prevent SQL injection
- The system implements proper exception handling for robust error management
- User passwords are stored (should be hashed in production)

## License
This project is for educational purposes.
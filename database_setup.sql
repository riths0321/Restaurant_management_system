-- Restaurant Management System Database Setup Script
-- MySQL Database

-- Create the database
CREATE DATABASE IF NOT EXISTS restaurant_management_system;
USE restaurant_management_system;

-- Create admin table
CREATE TABLE IF NOT EXISTS admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create customers table
CREATE TABLE IF NOT EXISTS customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create tables (restaurant dining tables)
CREATE TABLE IF NOT EXISTS tables (
    table_id INT PRIMARY KEY AUTO_INCREMENT,
    table_number INT NOT NULL UNIQUE,
    capacity INT NOT NULL,
    status VARCHAR(50) DEFAULT 'available',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create menu_items table
CREATE TABLE IF NOT EXISTS menu_items (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create orders table
CREATE TABLE IF NOT EXISTS orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    table_id INT,
    order_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (table_id) REFERENCES tables(table_id) ON DELETE SET NULL
);

-- Create order_items table
CREATE TABLE IF NOT EXISTS order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    menu_item_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (menu_item_id) REFERENCES menu_items(item_id) ON DELETE CASCADE
);

-- Create indexes for better query performance
CREATE INDEX idx_customer_id ON orders(customer_id);
CREATE INDEX idx_table_id ON orders(table_id);
CREATE INDEX idx_order_id ON order_items(order_id);
CREATE INDEX idx_menu_item_id ON order_items(menu_item_id);
CREATE INDEX idx_order_date ON orders(order_date);
CREATE INDEX idx_status ON orders(status);

-- Sample data for testing
INSERT INTO admin (username, password, email) VALUES
('admin1', 'admin123', 'admin1@restaurant.com'),
('admin2', 'admin456', 'admin2@restaurant.com');

INSERT INTO tables (table_number, capacity, status) VALUES
(1, 2, 'available'),
(2, 4, 'available'),
(3, 4, 'available'),
(4, 6, 'available'),
(5, 8, 'available');

INSERT INTO menu_items (name, description, price, category) VALUES
('Biryani', 'Fragrant Indian rice dish', 250, 'Main Course'),
('Butter Chicken', 'Creamy tomato-based chicken curry', 280, 'Main Course'),
('Paneer Tikka', 'Grilled cottage cheese with spices', 220, 'Appetizer'),
('Dal Makhani', 'Rich lentil curry with cream', 200, 'Main Course'),
('Garlic Naan', 'Wheat bread with garlic', 80, 'Bread'),
('Tandoori Chicken', 'Spiced grilled chicken', 300, 'Main Course'),
('Mango Lassi', 'Sweet yogurt-based mango drink', 120, 'Beverage'),
('Gulab Jamun', 'Sweet milk solids in syrup', 100, 'Dessert');

-- Verify the database setup
SELECT 'Database setup completed successfully!' as status;

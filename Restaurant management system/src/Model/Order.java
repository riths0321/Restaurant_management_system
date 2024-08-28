package Model;

import java.util.*;
import java.time.LocalDate;

public class Order {
    private int orderId;
    private int tableId;
    private int customerId;
    private LocalDate orderDate;
    private double totalAmount;
    private String status;

        // Constructor
        public Order(int orderId, int tableId, int customerId, LocalDate orderDate, double totalAmount, String status) {
            this.orderId = orderId;
            this.tableId = tableId;
            this.customerId = customerId;
            this.orderDate = orderDate;
            this.totalAmount = totalAmount;
            this.status = status;
        }

        public Order(int orderId, int customerId, LocalDate orderDate, double totalAmount, String status) {}

    public Order(int orderId, int customerId, Date orderDate, double totalAmount, String status) {
    }

    // Getters and Setters
        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getTableId() {
            return tableId;
        }

        public void setTableId(int tableId) {
            this.tableId = tableId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public LocalDate getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Order[ orderId=" + orderId +", tableId=" + tableId +", customerId=" + customerId +", orderDate=" + orderDate +", totalAmount=" + totalAmount +", status=" + status +"]";
        }
}
package Model;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int itemId;
    private int quantity;
    private double price;

        // Constructor
        public OrderItem(int orderItemId, int orderId, int itemId, int quantity, double price) {
            this.orderItemId = orderItemId;
            this.orderId = orderId;
            this.itemId = itemId;
            this.quantity = quantity;
            this.price = price;
        }

        public OrderItem() {}

        // Getters and Setters
        public int getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(int orderItemId) {
            this.orderItemId = orderItemId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "OrderItem[ orderItemId=" + orderItemId + ", orderId=" + orderId + ", itemId=" + itemId + ", quantity=" + quantity + ", price=" + price + "]";
        }
}



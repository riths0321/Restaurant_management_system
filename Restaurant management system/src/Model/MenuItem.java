package Model;

public class MenuItem
{
    private int itemId;
    private String itemName;
    private String description;
    private int price;
    private String category;
    //getter and setter

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return (int)price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MenuItem(int itemId, String itemName, String description,int price, String category) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public MenuItem(){}

    @Override
    public String toString() {
        return "MenuItem[ itemId=" + itemId +", itemName=" + itemName +", description=" + description+",price=" + price+", category=" + category + "]";
    }
}

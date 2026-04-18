
public class StockItem {

    private int itemId;
    private String itemName;
    private String category;
    private double quantity;
    private String unit;
    private double reorderLevel;


    public StockItem(int itemId, String itemName, String category,
                     double quantity, String unit, double reorderLevel) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
        this.reorderLevel = reorderLevel;
    }


    public int getItemId()          { return itemId; }
    public String getItemName()     { return itemName; }
    public String getCategory()     { return category; }
    public double getQuantity()     { return quantity; }
    public String getUnit()         { return unit; }
    public double getReorderLevel() { return reorderLevel; }


    public void setItemName(String itemName)         { this.itemName = itemName; }
    public void setCategory(String category)         { this.category = category; }
    public void setQuantity(double quantity)         { this.quantity = quantity; }
    public void setUnit(String unit)                 { this.unit = unit; }
    public void setReorderLevel(double reorderLevel) { this.reorderLevel = reorderLevel; }



    public void addStock(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to add must be greater than zero.");
        }
        this.quantity += amount;
    }

    public void useStock(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to use must be greater than zero.");
        }
        if (amount > this.quantity) {
            throw new IllegalArgumentException("Not enough stock. Available: " + this.quantity + " " + unit);
        }
        this.quantity -= amount;
    }

    public boolean isLowStock() {
        return this.quantity <= this.reorderLevel;
    }


    @Override
    public String toString() {
        return String.format("[%d] %s | Category: %s | Qty: %.2f %s | Reorder at: %.2f %s | Low Stock: %s",
                itemId, itemName, category, quantity, unit, reorderLevel, unit, isLowStock() ? "YES" : "No");
    }
}
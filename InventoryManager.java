import java.util.ArrayList;

public class InventoryManager {

    private ArrayList<StockItem> stockList;


    public InventoryManager() {
        this.stockList = new ArrayList<>();
    }


    public void addItem(StockItem item) {
        for (StockItem s : stockList) {
            if (s.getItemId() == item.getItemId()) {
                System.out.println("Error: Item with ID '" + item.getItemId() + "' already exists.");
                return;
            }
        }
        stockList.add(item);
        System.out.println("Item added: " + item.getItemName());
    }


    public void removeItem(int itemId) {
        StockItem found = findById(itemId);
        if (found != null) {
            stockList.remove(found);
            System.out.println("Item removed: " + found.getItemName());
        } else {
            System.out.println("Error: Item ID '" + itemId + "' not found.");
        }
    }


    public void useStock(int itemId, double amount) {
        StockItem item = findById(itemId);
        if (item != null) {
            try {
                item.useStock(amount);
                System.out.println("Used " + amount + " of " + item.getItemName()
                        + ". Remaining: " + item.getQuantity() + " " + item.getUnit());
                if (item.isLowStock()) {
                    System.out.println("WARNING: " + item.getItemName() + " is running low!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Error: Item ID '" + itemId + "' not found.");
        }
    }


    public void addStock(int itemId, double amount) {
        StockItem item = findById(itemId);
        if (item != null) {
            try {
                item.addStock(amount);
                System.out.println("Added " + amount + " to " + item.getItemName()
                        + ". New quantity: " + item.getQuantity() + " " + item.getUnit());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Error: Item ID '" + itemId + "' not found.");
        }
    }


    public StockItem findById(int itemId) {
        for (StockItem s : stockList) {
            if (s.getItemId() == itemId) {
                return s;
            }
        }
        return null;
    }


    public ArrayList<StockItem> searchByName(String keyword) {
        ArrayList<StockItem> results = new ArrayList<>();
        for (StockItem s : stockList) {
            if (s.getItemName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(s);
            }
        }
        return results;
    }


    public ArrayList<StockItem> searchByCategory(String category) {
        ArrayList<StockItem> results = new ArrayList<>();
        for (StockItem s : stockList) {
            if (s.getCategory().equalsIgnoreCase(category)) {
                results.add(s);
            }
        }
        return results;
    }


    public void displayAll() {
        if (stockList.isEmpty()) {
            System.out.println("No stock items found.");
            return;
        }
        System.out.println("\n===== INVENTORY LIST =====");
        for (StockItem s : stockList) {
            System.out.println(s.toString());
        }
        System.out.println("==========================\n");
    }


    public void displayLowStock() {
        System.out.println("\n===== LOW STOCK ALERTS =====");
        boolean found = false;
        for (StockItem s : stockList) {
            if (s.isLowStock()) {
                System.out.println("LOW: " + s.getItemName()
                        + " | Qty: " + s.getQuantity()
                        + " | Reorder Level: " + s.getReorderLevel()
                        + " " + s.getUnit());
                found = true;
            }
        }
        if (!found) {
            System.out.println("All stock levels are sufficient.");
        }
        System.out.println("============================\n");
    }


    public ArrayList<StockItem> getStockList() {
        return stockList;
    }


    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();


        StockItem feed = new StockItem(1, "Cattle Feed", "Feed", 50.0, "kg", 10.0);
        StockItem meds = new StockItem(2, "Antibiotics", "Medicine", 5.0, "bottles", 3.0);

        manager.addItem(feed);
        manager.addItem(meds);
        manager.displayAll();

        manager.useStock(2, 3.0);
        manager.displayLowStock();

        manager.addStock(2, 10.0);
        manager.displayAll();
    }
}
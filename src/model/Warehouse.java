import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<String, Product> inventory;
    private AlertService alertService;

    public Warehouse(AlertService alertService) {
        this.inventory = new HashMap<>();
        this.alertService = alertService;
    }

    // Add a new product
    public void addProduct(String productId, String name, int quantity, int threshold) {
        if (inventory.containsKey(productId)) {
            System.out.println("Product with ID " + productId + " already exists!");
        } else {
            Product product = new Product(productId, name, quantity, threshold);
            inventory.put(productId, product);
            System.out.println("Added new product: " + name);
        }
    }

    // Receive shipment → increase stock
    public void receiveShipment(String productId, int quantity) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Invalid Product ID: " + productId);
            return;
        }
        product.setQuantity(product.getQuantity() + quantity);
        System.out.println("Received " + quantity + " units of " + product.getName() + ". Total: " + product.getQuantity());
    }

    // Fulfill order → decrease stock
    public void fulfillOrder(String productId, int quantity) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Invalid Product ID: " + productId);
            return;
        }
        if (product.getQuantity() < quantity) {
            System.out.println("Insufficient stock for " + product.getName());
            return;
        }
        product.setQuantity(product.getQuantity() - quantity);
        System.out.println("Fulfilled order of " + quantity + " units of " + product.getName() + ". Remaining: " + product.getQuantity());

        // Trigger alert if below threshold
        if (product.getQuantity() < product.getThreshold()) {
            alertService.sendAlert("Low stock for " + product.getName() + " – only " + product.getQuantity() + " left!");
        }
    }

    // Display all products
    public void showInventory() {
        System.out.println("\n------ Current Inventory ------");
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}

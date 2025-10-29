public class Main {
    public static void main(String[] args) {

        // Step 1: Create an Alert Service (Observer)
        AlertService alertService = new ConsoleAlertService();

        // Step 2: Create Warehouse and pass alert service
        Warehouse warehouse = new Warehouse(alertService);

        // Step 3: Add Products
        warehouse.addProduct("P101", "Laptop", 0, 5);
        warehouse.addProduct("P102", "Mobile", 0, 10);

        // Step 4: Receive shipment (increase stock)
        warehouse.receiveShipment("P101", 10);
        warehouse.receiveShipment("P102", 15);

        // Step 5: Fulfill some orders (reduce stock)
        warehouse.fulfillOrder("P101", 6); // should trigger alert
        warehouse.fulfillOrder("P102", 3);

        // Step 6: Try invalid product
        warehouse.fulfillOrder("P999", 2); // invalid product ID

        // Step 7: Try insufficient stock
        warehouse.fulfillOrder("P101", 20); // insufficient stock

        // Step 8: Display all products
        warehouse.showInventory();
    }
}

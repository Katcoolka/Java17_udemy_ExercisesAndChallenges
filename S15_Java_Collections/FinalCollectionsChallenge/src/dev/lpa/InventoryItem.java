package dev.lpa;

public class InventoryItem {
    private Product product;
    private double price;
    private int qtyTotal;
    private int qtyReserved;
    private int qtyReorder;
    private int qtyLow;

    public InventoryItem(Product product, double price, int qtyTotal, int qtyLow) {
        this.product = product;
        this.price = price;
        this.qtyTotal = qtyTotal;
        this.qtyLow = qtyLow;
        this.qtyReorder = qtyTotal;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    //method to be called when adding an Item to the shopping cart
    public boolean reserveItem(int qty) {
        if ((qtyTotal - qtyReserved) >= qty) {
            qtyReserved += qty;
            return true;
        }
        return false;
    }

    //method to be called when a Shopper is removing the Item from the shopping cart
    public void releaseItem(int qty) {
        qtyReserved -= qty;
    }
    //method to be called during check out process
    public boolean sellItem(int qty){
        if (qtyTotal >= qty){
            qtyTotal -= qty;
            qtyReserved -= qty;
            if (qtyTotal <= qtyLow) {
                placeInventoryOrder();
            }
            return true;
        }
        return false;
    }
    //method is private as it is triggered by a condition in previous method
    private void placeInventoryOrder(){
        System.out.printf("Ordering qty %d : %s%n", qtyReorder, product);
    }

    @Override
    public String toString() {
        return "%s, $%.2f : [%04d, % 2d]".formatted(product, price, qtyTotal, qtyReserved);
    }
}

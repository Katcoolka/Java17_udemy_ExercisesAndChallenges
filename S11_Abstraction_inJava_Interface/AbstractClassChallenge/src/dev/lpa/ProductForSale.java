package dev.lpa;

public abstract class ProductForSale {

    protected String type;
    protected double price;
    protected String description;

    public ProductForSale(String type, double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }

    /* %2d means an integer will get printed with two digits, right justified.
    Then %8.2f means a decimal number, a double or float, will get printed with a precision of 2
    digits after the period, and a total width of 8.
    Then there's %-15s, and this prints out a String,
    the dash means left justify that, and the 15 means allow 15 spaces as a minimum.
    That's our item type. And then we'll left justify
    the description, the last thing we want printed on the line item.*/
    public void printPricedItem(int qty) {
        System.out.printf("%2d qty at $%8.2f each. %-15s %-35s %n", qty, price, type, description);
    }

    public double getSalesPrice(int qty) {
        return qty * price;
    }

    public abstract void showDetails();

}

package dev.lpa;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;

public class Store {
    private static Random random = new Random();
    private Map<String, InventoryItem> inventory;
    private NavigableSet<Cart> carts = new TreeSet<>(Comparator.comparing(Cart::getId));
    private Map<Category, Map<String, InventoryItem>> aisleInventory;

    public static void main(String[] args) {

        Store myStore = new Store();
        myStore.stockStore();
        myStore.listInventory();
        System.out.println("-----------------");

        myStore.stockAisles();
        myStore.listProductsByCategory();
        System.out.println("-----------------");

        myStore.manageStoreCarts();
        myStore.listProductsByCategory(false, true);
        System.out.println("-----------------");

        myStore.carts.forEach(System.out::println);
        System.out.println("-----------------");

        myStore.abandonCarts();
        myStore.listProductsByCategory(false, true);
        System.out.println("-----------------");
        myStore.carts.forEach(System.out::println);
        System.out.println("-----------------");
    }

    private void manageStoreCarts() {
        Cart cart1 = new Cart(Cart.CartType.PHYSICAL, 1);
        carts.add(cart1);

        InventoryItem item = aisleInventory.get(Category.PRODUCE).get("apple");
        cart1.addItem(item, 6);
        cart1.addItem(aisleInventory.get(Category.PRODUCE).get("pear"), 5);
        cart1.addItem(aisleInventory.get(Category.BEVERAGE).get("coffee"), 1);
        System.out.println(cart1);
        System.out.println("-----------------");

        cart1.removeItem(aisleInventory.get(Category.PRODUCE).get("pear"), 2);
        System.out.println(cart1);
        System.out.println("-----------------");

        Cart cart2 = new Cart(Cart.CartType.VIRTUAL, 1);
        carts.add(cart2);
        cart2.addItem(inventory.get("L103"), 20);
        cart2.addItem(inventory.get("B100"), 10);
        System.out.println(cart2);
        System.out.println("-----------------");

        Cart cart3 = new Cart(Cart.CartType.VIRTUAL, 0);
        carts.add(cart3);
        cart3.addItem(inventory.get("R777"), 998);
        System.out.println(cart3);
        if (!checkOutCart(cart3)) {
            System.out.println("Something went wrong, could not check out");
        }

        System.out.println("-----------------");

        Cart cart4 = new Cart(Cart.CartType.PHYSICAL, 0);
        carts.add(cart4);
        cart4.addItem(aisleInventory.get(Category.BEVERAGE).get("tea"), 1);
        System.out.println(cart4);
        System.out.println("-----------------");
    }

    private boolean checkOutCart(Cart cart) {
        for (var cartItem : cart.getProducts().entrySet()) {
            var item = inventory.get(cartItem.getKey());
            int qty = cartItem.getValue();
            if (!item.sellItem(qty)) return false;
        }
        cart.printSalesSlip(inventory);
        carts.remove(cart);
        return true;
    }

    private void abandonCarts() {
        int dayOfYear = LocalDate.now().getDayOfYear();
        Cart lastCart = null;
        for (Cart cart : carts) {
            if (cart.getCartDate().getDayOfYear() == dayOfYear) {
                break;
            }
            lastCart = cart;
        }

        var oldCarts = carts.headSet(lastCart, true);
        Cart abandonCart = null;
        while ((abandonCart = oldCarts.pollFirst()) != null) {
            for (String sku : abandonCart.getProducts().keySet()) {
                InventoryItem item = inventory.get(sku);
                item.releaseItem(abandonCart.getProducts().get(sku));
            }
        }
    }

    private void listProductsByCategory() {
        //1.) code before overloaded method
//        aisleInventory.keySet().forEach(k -> {
//            System.out.println("----------\n" + k + "\n---------");
//            aisleInventory.get(k).keySet().forEach(System.out::println);
//        });

        //2.) code after overloaded method
        listProductsByCategory(true, false);
    }

    //overloaded method with category detail
    private void listProductsByCategory(boolean includeHeader, boolean includeDetail) {
        aisleInventory.keySet().forEach(k -> {
            if (includeHeader) System.out.println("----------\n" + k + "\n---------");
            if (!includeDetail) {
                aisleInventory.get(k).keySet().forEach(System.out::println);
            } else {
                aisleInventory.get(k).values().forEach(System.out::println);
            }
        });
    }

    //helper methods to set up the store
    private void stockStore() {
        //the HashMap is used because we do not need to have our products sorted, Map is used to quickly retrieve required product
        inventory = new HashMap<>();
        List<Product> products = new ArrayList<>(List.of(
                new Product("A100", "apple", "local", Category.PRODUCE),
                new Product("B100", "banana", "local", Category.PRODUCE),
                new Product("P100", "pear", "local", Category.PRODUCE),
                new Product("L103", "lemon", "local", Category.PRODUCE),
                new Product("M201", "milk", "farm", Category.DAIRY),
                new Product("Y001", "yogurt", "farm", Category.DAIRY),
                new Product("C333", "cheese", "farm", Category.DAIRY),
                new Product("R777", "rice chex", "Nabisco", Category.CEREAL),
                new Product("G111", "granola", "Nat Valley", Category.CEREAL),
                new Product("BB11", "ground beef", "butcher", Category.MEAT),
                new Product("CC11", "chicken", "butcher", Category.MEAT),
                new Product("BC11", "bacon", "butcher", Category.MEAT),
                new Product("BC77", "coke", "coca cola", Category.BEVERAGE),
                new Product("BC88", "coffee", "value", Category.BEVERAGE),
                new Product("BC99", "tea", "herbal", Category.BEVERAGE)
        ));
        products.forEach(p -> inventory.put(p.sku(), new InventoryItem(p, random.nextDouble(0, 1.25), 1000, 5)));
    }

    private void stockAisles() {
        aisleInventory = new EnumMap<>(Category.class);
        for (InventoryItem item : inventory.values()) {
            Category aisle = item.getProduct().category();

            Map<String, InventoryItem> productMap = aisleInventory.get(aisle);
            if (productMap == null) {
                productMap = new TreeMap<>();
            }
            productMap.put(item.getProduct().name(), item);
            aisleInventory.putIfAbsent(aisle, productMap);
        }
    }

    private void listInventory() {
        System.out.println("---------------List of inventory----------------");
        inventory.values().forEach(System.out::println);
    }
}
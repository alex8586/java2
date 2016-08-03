package lv.javaguru.java2.domain;

import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> items = new HashMap<>();
    
    public void add(Product product) {
        add(product, 1);
    }

    public void add(Product product, int quantity) {
        enforcePositiveQuantity(quantity);
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            items.put(product, currentQuantity + quantity);
        } else {
            items.put(product, quantity);
        }
    }

    private void enforcePositiveQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be >= 1.");
        }
    }

    public void remove(Product product) {
        items.remove(product);
    }

    public void remove(Product product, int quantity) {
        enforcePositiveQuantity(quantity);
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            if (currentQuantity > quantity) {
                items.put(product, currentQuantity - quantity);
            } else {
                remove(product);
            }
        }
    }

    public Integer getQuantity(Product product) {
        return items.get(product);
    }
}

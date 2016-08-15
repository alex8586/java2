package lv.javaguru.java2.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private HashMap<Product, Integer> items = new HashMap<>();

    public void add(Product product) {
        add(product, 1);
    }

    public void add(Product product, int quantity) {
        enforcePositiveQuantity(quantity);
        int currentQuantity = items.getOrDefault(product, 0);
        items.put(product, currentQuantity + quantity);
    }

    private void enforcePositiveQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
    }

    public void remove(Product product) {
        items.remove(product);
    }

    public void remove(Product product, int quantity) {
        enforcePositiveQuantity(quantity);
        int currentQuantity = items.getOrDefault(product, 0);
        // removing some, not all of items
        if (currentQuantity > quantity) {
            items.put(product, currentQuantity - quantity);
            // removing all or more items
        } else {
            remove(product);
        }
    }

    public Integer getQuantity(Product product) {
        return items.get(product);
    }

    public void setQuantity(Product product, int quantity) {
        enforcePositiveQuantity(quantity);
        items.put(product, quantity);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%s {%n", this.getClass().getSimpleName()));
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            stringBuilder.append(String.format(
                    "\t%s\t%d%n", entry.getKey().toString(), entry.getValue()
            ));
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}

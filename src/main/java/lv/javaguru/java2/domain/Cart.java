package lv.javaguru.java2.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Cart {

    private HashMap<Product, Integer> items;

    public Cart(){
        this.items = new HashMap<>();
    }

    public HashMap<Product, Integer> getAll(){
        return items;
    }

    public long getTotalPrice(Cart cart) {
        long result = 0;
        if (cart == null)
            return 0;
        HashMap<Product, Integer> map = cart.getAll();
        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
            result += entry.getKey().getPrice() * entry.getValue();
        }
        return result;
    }

    public long getTotalPrice() {
        return getTotalPrice(this);
    }

    public void add(Product product) {
        add(product, 1);
    }

    public void add(Product product, int quantity) {
        enforcePositiveQuantity(quantity);
        int currentQuantity = items.getOrDefault(product, 0);
        items.put(product, currentQuantity + quantity);
    }

    public void remove(Product product) {
        enforcePositiveQuantity(items.getOrDefault(product, 0));
        items.remove(product);
    }

    public void remove(Product product, int quantity) {
        enforcePositiveQuantity(quantity);
        int currentQuantity = items.getOrDefault(product, 0);
        if (currentQuantity > quantity) {
            items.put(product, currentQuantity - quantity);
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

    private void enforcePositiveQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
    }

    public long getHashCode() {
        return this.hashCode();
    }



}

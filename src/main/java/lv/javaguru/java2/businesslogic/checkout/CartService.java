package lv.javaguru.java2.businesslogic.checkout;

import lv.javaguru.java2.domain.Cart;

import java.util.Map;

public interface CartService {
    Map<String, Object> model(Cart cart);

    Map<String, Object> model();
    void addProduct(Cart cart, long id);
    void removeProduct(Cart cart, long id);

    void addProducts(Cart cart, long id, int quantity);

    void removeProducts(Cart cart, long id, int quantity);
}

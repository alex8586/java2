package lv.javaguru.java2.businesslogic.checkout;

import lv.javaguru.java2.domain.Cart;

public interface CartService {
    void addProduct(Cart cart, long id);
    void removeProduct(Cart cart, long id);

    void addProducts(Cart cart, long id, int quantity);

    void removeProducts(Cart cart, long id, int quantity);
}

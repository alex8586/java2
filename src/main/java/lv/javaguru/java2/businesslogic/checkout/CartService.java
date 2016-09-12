package lv.javaguru.java2.businesslogic.checkout;

import lv.javaguru.java2.domain.Cart;

public interface CartService {
    void addProduct(Cart cart, long id);

    void removeProduct(Cart cart, long id);
}

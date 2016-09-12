package lv.javaguru.java2.businesslogic.checkout;

import lv.javaguru.java2.domain.Cart;

public interface CartProvider {

    Cart getCart();
    void setCart(Cart cart);
    boolean isEmpty();

    void empty();
}

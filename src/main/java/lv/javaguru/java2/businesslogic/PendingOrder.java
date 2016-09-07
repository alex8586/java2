package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.Cart;


public interface PendingOrder {
    Cart getCart();
    void setCart(Cart cart);
    boolean isPending();
}

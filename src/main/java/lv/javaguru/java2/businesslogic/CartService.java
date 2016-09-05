package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.Cart;

public interface CartService {

    Cart addToCart(long productId);

    long addProduct(long id);

    long removeProduct(long id);
}

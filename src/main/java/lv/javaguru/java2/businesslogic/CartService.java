package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.Cart;

public interface CartService {

    void addToCart(long productId);

    long getPrice(Cart cart);

    void addProduct(long id);

    void deleteProduct(long id);
}

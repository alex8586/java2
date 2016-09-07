package lv.javaguru.java2.businesslogic.order;

import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Map;


@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class PendingOrderImpl implements PendingOrder {
    private Cart cart;

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = new Cart();
        for (Map.Entry<Product, Integer> cartLine : cart.getAll().entrySet()) {
            this.cart.add(cartLine.getKey(), cartLine.getValue());
        }
    }


    public boolean isPending() {
        return this.cart != null;
    }

}

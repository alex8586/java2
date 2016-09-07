package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.Cart;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class SessionCartProvider implements CartProvider {

    private Cart cart;

    public SessionCartProvider(){
        this.cart = new Cart();
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void setCart(Cart cart) {
        this.cart = cart;
    }

}

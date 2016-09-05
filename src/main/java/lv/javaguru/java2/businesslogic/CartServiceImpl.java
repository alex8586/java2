package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private CartProvider cartProvider;
    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;

    @Override
    public Cart addToCart(long productId) {
        Product product = productDAO.getById(productId);
        Cart cart = cartProvider.getCart();
        cart.add(product);
        cartProvider.setCart(cart);
        return cart;
    }

    @Override
    public long addProduct(long id) {
        Product product = productDAO.getById(id);
        Cart cart = cartProvider.getCart();
        cart.add(product, 1);
        cartProvider.setCart(cart);
        return cart.getTotalPrice(cart);
    }

    @Override
    public long removeProduct(long id) {
        Product product = productDAO.getById(id);
        Cart cart = cartProvider.getCart();
        cart.remove(product, 1);
        cartProvider.setCart(cart);
        return cart.getTotalPrice(cart);
    }

}

package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private CartProvider cartProvider;
    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;

    public void addToCart(long productId) {
        Product product = productDAO.getById(productId);
        Cart cart;
        if (cartProvider.isEmpty()) {
            cart = new Cart();
            cart.add(product);
            cartProvider.setCart(cart);
        } else {
            cart = cartProvider.getCart();
            cart.add(product);
            cartProvider.setCart(cart);
        }
    }

    @Override
    public long getPrice(Cart cart) {
        long result = 0;
        if (cart == null)
            return 0;
        HashMap<Product, Integer> map = cart.getAll();
        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
            result += entry.getKey().getPrice() * entry.getValue();
        }
        return result;
    }

    @Override
    public void addProduct(long id) {
        Product product = productDAO.getById(id);
        Cart cart = cartProvider.getCart();
        cart.add(product, 1);
        cartProvider.setCart(cart);
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productDAO.getById(id);
        Cart cart = cartProvider.getCart();
        cart.remove(product, 1);
        cartProvider.setCart(cart);
    }


}

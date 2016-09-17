package lv.javaguru.java2.businesslogic.checkout;

import lv.javaguru.java2.businesslogic.product.StockService;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.dto.CartDTO;
import lv.javaguru.java2.dto.builders.CartDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CartServiceImpl implements CartService {

    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CartDTOUtil cartDTOUtil;

    @Autowired
    private CartProvider cartProvider;

    @Autowired
    private StockService stockService;

    @Override
    public Map<String, Object> model() {
        return model(cartProvider.getCart());
    }

    @Override
    public Map<String, Object> model(Cart cart) {
        Map<String, Object> map = new HashMap<String, Object>();
        CartDTO cartDTO = cartDTOUtil.build(cart);
        map.put("cart", cartDTO);
        return map;
    }

    @Override
    public void addProduct(Cart cart, long id) {
        addProducts(cart, id, 1);
    }

    @Override
    public void removeProduct(Cart cart, long id) {
        removeProducts(cart, id, 1);
    }

    @Override
    public void addProducts(Cart cart, long id, int quantity) {
        Product product = productDAO.getById(id);
        if (product == null)
            return;
        if (!stockService.isValid(quantity, product))
            return;
        cart.add(product, quantity);
    }

    @Override
    public void removeProducts(Cart cart, long id, int quantity) {
        Product product = productDAO.getById(id);
        if (product != null)
            cart.remove(product, quantity);
    }

}

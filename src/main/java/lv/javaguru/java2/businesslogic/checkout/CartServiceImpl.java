package lv.javaguru.java2.businesslogic.checkout;

import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CartServiceImpl implements CartService {

    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;

    @Override
    public void addProduct(Cart cart, long id) {
        Product product = productDAO.getById(id);
        if (product != null)
            cart.add(product);
    }

    @Override
    public void removeProduct(Cart cart, long id) {
        Product product = productDAO.getById(id);
        if (product != null)
            cart.remove(product, 1);
    }

}

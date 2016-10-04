package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.serviceexception.InsufficientSupplyException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StockServiceImpl implements StockService {

    @Autowired
    @Qualifier("ORM_ProductDAO")
    ProductDAO productDAO;

    @Override
    public void supply(Cart cart) throws ServiceException {
        HashMap<Product, Integer> cartLines = cart.getAll();
        for (Map.Entry<Product, Integer> cartLine : cartLines.entrySet()) {
            Product product = productDAO.getById(cartLine.getKey().getId());
            supply(product, cartLine.getValue());
            productDAO.merge(product);
        }
    }

    private void supply(Product product, Integer quantity) throws ServiceException {
        if (product.getFreshStockQuantity() < quantity)
            throw new InsufficientSupplyException(product);

        for (Stock stock : product.getFresh()) {
            if (quantity < stock.getQuantity()) {
                stock.substractQuantity(quantity);
                quantity = 0;
            } else {
                quantity -= stock.getQuantity();
                stock.setQuantity(0);
            }
        }
        if (quantity > 0) {
            throw new InsufficientSupplyException(product);
        }
        if (quantity < 0) {
            throw new IllegalStateException();
        }
    }

    @Override
    public boolean isValid(int quantity, Product product) {
        return product.getFreshStockQuantity() >= quantity;
    }

}

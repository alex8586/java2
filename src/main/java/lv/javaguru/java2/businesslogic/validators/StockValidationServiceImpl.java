package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.checkout.CartProvider;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.dto.ProductCard;
import lv.javaguru.java2.dto.builders.ProductCardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StockValidationServiceImpl implements StockValidationService {

    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ProductCardUtil productCardUtil;
    @Autowired
    private CartProvider cartProvider;

    @Override
    public boolean isValid(int quantity, long productId){
        Product product = productDAO.getById(productId);
        ProductCard productCard = productCardUtil.build(product);

        long stockQuantity = productCard.getStockQuantity();
        Cart cart = cartProvider.getCart();
        long productInCart;
        try{
            productInCart = cart.getQuantity(product);
        }catch (NullPointerException e){
            productInCart = 0;
        }

        return quantity <= (stockQuantity - productInCart);
    }
}

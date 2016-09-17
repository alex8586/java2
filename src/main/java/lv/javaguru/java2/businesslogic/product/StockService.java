package lv.javaguru.java2.businesslogic.product;


import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;

public interface StockService {
    void supply(Cart cart) throws ServiceException;

    boolean isValid(int quantity, Product product);
}

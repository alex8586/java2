package lv.javaguru.java2.businesslogic.product;


import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.Cart;

public interface StockService {
    void supply(Cart cart) throws ServiceException;
}

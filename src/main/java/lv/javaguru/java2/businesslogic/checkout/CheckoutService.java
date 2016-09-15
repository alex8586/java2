package lv.javaguru.java2.businesslogic.checkout;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.dto.ShippingDetails;

import java.util.Map;

public interface CheckoutService {
    Map<String, Object> model() throws ServiceException;

    Map<String, Object> model(Cart cart, User user) throws ServiceException;

    Order composeOrder(String checkSum, Cart cart, ShippingDetails shippingDetails) throws ServiceException;
}

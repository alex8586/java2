package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.dto.ShippingDetails;

import java.util.Map;

public interface CheckoutService {
    Map<String, Object> serve();

    Order createOrder(Cart cart, String hashcode, ShippingDetails shippingDetails) throws ServiceException;
}

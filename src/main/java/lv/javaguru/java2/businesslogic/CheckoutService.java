package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.order.Order;

import java.util.Map;

public interface CheckoutService {
    Map<String, Object> serve();

    Order createOrder(Cart cart, ShippingProfile shippingProfile);
}

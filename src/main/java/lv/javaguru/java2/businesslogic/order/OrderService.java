package lv.javaguru.java2.businesslogic.order;

import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.order.Order;

public interface OrderService {
    Order create(Cart cart, ShippingProfile shippingProfile);
}

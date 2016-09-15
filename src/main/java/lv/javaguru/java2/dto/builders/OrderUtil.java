package lv.javaguru.java2.dto.builders;


import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.domain.order.OrderLine;
import lv.javaguru.java2.dto.ShippingDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Component
public class OrderUtil {

    public OrderLine buildOrderLine(Product product, int quantity) {
        OrderLine orderLine = new OrderLine();
        orderLine.setDescription(product.getDescription());
        orderLine.setName(product.getName());
        orderLine.setPrice(product.getPrice());
        orderLine.setProductId(product.getId());

        orderLine.setQuantity(quantity);
        orderLine.setExpireDate(new Date());
        return orderLine;
    }

    public void build(ShippingDetails shippingDetails, Order order) {
        order.setPhone(shippingDetails.getPhone());
        order.setAddress(shippingDetails.getAddress());
        order.setDocument(shippingDetails.getDocument());
        order.setPerson(shippingDetails.getPerson());
    }

    public void build(Cart cart, Order order) {
        order.setTotal(cart.getTotalPrice());
        order.setOrderLines(new ArrayList<>());
        for (Map.Entry<Product, Integer> cartLine : cart.getAll().entrySet()) {
            OrderLine orderLine = buildOrderLine(cartLine.getKey(), cartLine.getValue());
            orderLine.setOrder(order);
            order.getOrderLines().add(orderLine);
        }
    }

    public void build(User user, Order order) {
        if (user != null)
            order.setUserId(user.getId());
    }


}

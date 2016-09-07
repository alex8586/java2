package lv.javaguru.java2.businesslogic.order;

import lv.javaguru.java2.businesslogic.UserProvider;
import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.domain.order.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserProvider userProvider;

    @Autowired
    OrderDAO orderDAO;

    public Order create(Cart cart, ShippingProfile shippingProfile) {
        Order order = new Order();
        order.setPhone(shippingProfile.getPhone());
        order.setAddress(shippingProfile.getAddress());
        order.setDocument(shippingProfile.getDocument());
        order.setPerson(shippingProfile.getPerson());
        order.setOrderDate(new Date());
        order.setDeliveryDate(new Date());
        order.setTotal(cart.getTotalPrice());
        if (userProvider.authorized())
            order.setUserId(userProvider.getUser().getId());

        Set<OrderLine> orderLines = new HashSet<OrderLine>();
        for (Map.Entry<Product, Integer> cartLine : cart.getAll().entrySet()) {
            OrderLine orderLine = new OrderLine();
            orderLine.setDescription(cartLine.getKey().getDescription());
            orderLine.setName(cartLine.getKey().getName());
            orderLine.setPrice(cartLine.getKey().getPrice());
            orderLine.setProductId(cartLine.getKey().getId());
            orderLine.setQuantity(cartLine.getValue());
            orderLine.setExpireDate(new Date());
            orderLine.setOrder(order);
        }
        order.setOrderLines(orderLines);
        orderDAO.create(order);
        return order;
    }
}

package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.database.StockDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.domain.order.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private UserProvider userProvider;
    @Autowired
    private CartProvider cartProvider;
    @Qualifier("ORM_ShippingProfileDAO")
    @Autowired
    private ShippingProfileDAO shippingProfileDAO;
    @Autowired
    private SpecialSaleOffer specialSaleOffer;

    @Autowired
    private StockDAO stockDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Map<String, Object> serve() {
        Map<String, Object> data = new HashMap<>();

        data.put("saleOffer", specialSaleOffer.getOffer());
        User user = userProvider.getUser();
        if (user != null) {
            List<ShippingProfile> shippingProfiles = shippingProfileDAO.getAllByUser(user);
            data.put("shippingProfiles", shippingProfiles);
        }
        data.put("user", user);
        data.put("checkoutCart", cartProvider.getCart());
        return data;
    }


    public Order createOrder(Cart cart, ShippingProfile shippingProfile) {
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
            orderLines.add(orderLine);
        }
        order.setOrderLines(orderLines);

        orderDAO.create(order);
        return order;
    }
}

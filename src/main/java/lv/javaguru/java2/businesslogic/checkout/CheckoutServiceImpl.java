package lv.javaguru.java2.businesslogic.checkout;

import lv.javaguru.java2.businesslogic.product.SpecialSaleOffer;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.validators.ShippingDetailsFormatValidationService;
import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.domain.order.OrderLine;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.dto.builders.ShippingDetailsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CheckoutServiceImpl implements CheckoutService {

    private static final String CART_CONTENT_HAS_CHANGED = "Cart content changed";

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
    private ShippingDetailsUtil shippingDetailsUtil;
    @Autowired
    private ShippingDetailsFormatValidationService shippingDetailsFormatValidationService;


    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Map<String, Object> model() {
        Map<String, Object> data = new HashMap<>();

        User user = userProvider.getUser();
        if (user != null) {
            List<ShippingProfile> shippingProfiles = shippingProfileDAO.getAllByUser(user);
            data.put("shippingProfiles", shippingProfiles);
        }
        data.put("saleOffer", specialSaleOffer.getOffer());
        data.put("user", user);
        data.put("checkoutCart", cartProvider.getCart());
        return data;
    }

    public Order createOrder(Cart cart, String hashcode, ShippingDetails shippingDetails) throws ServiceException {
        if (!hashcode.equals(new Long(cart.getHashCode()).toString())) {
            throw new ServiceException(CART_CONTENT_HAS_CHANGED);
        }

        shippingDetailsFormatValidationService.validate(shippingDetails);

        Order order = new Order();
        if (userProvider.authorized())
            order.setUserId(userProvider.getUser().getId());

        shippingDetailsUtil.updateOrder(shippingDetails, order);

        order.setOrderDate(new Date());
        order.setDeliveryDate(new Date());
        order.setTotal(cart.getTotalPrice());

        order.setOrderLines(new ArrayList<>());
        for (Map.Entry<Product, Integer> cartLine : cart.getAll().entrySet()) {
            Product product = cartLine.getKey();
            OrderLine orderLine = new OrderLine();

            orderLine.setDescription(product.getDescription());
            orderLine.setName(product.getName());
            orderLine.setPrice(product.getPrice());
            orderLine.setProductId(product.getId());

            orderLine.setQuantity(cartLine.getValue());
            orderLine.setExpireDate(new Date());
            orderLine.setOrder(order);
            order.getOrderLines().add(orderLine);
        }
        orderDAO.create(order);
        System.out.println("order has been created !!!");
        return order;
    }
}

package lv.javaguru.java2.businesslogic.checkout;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.product.StockService;
import lv.javaguru.java2.businesslogic.profilepages.ShippingProfileService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.validators.DeliveryDateValidationService;
import lv.javaguru.java2.businesslogic.validators.LockedResourceAccessService;
import lv.javaguru.java2.businesslogic.validators.ShippingDetailsFormatValidationService;
import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.dto.builders.OrderUtil;
import lv.javaguru.java2.helpers.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class CheckoutServiceImpl implements CheckoutService {

    private static final String CART_CONTENT_HAS_CHANGED = "Cart content changed";
    private static final String EMPTY_CART = "Cart is empty";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", Locale.ENGLISH);
    @Autowired
    OrderUtil orderUtil;
    @Autowired
    ShippingProfileService shippingProfileService;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private CartProvider cartProvider;
    @Autowired
    private CartService cartService;
    @Qualifier("ORM_ShippingProfileDAO")
    @Autowired
    private ShippingProfileDAO shippingProfileDAO;
    @Autowired
    private ShippingDetailsFormatValidationService shippingDetailsFormatValidationService;
    @Autowired
    private DeliveryDateValidationService deliveryDateValidationService;
    @Autowired
    private StockService stockService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private LockedResourceAccessService lockedResourceAccessService;

    @Override
    public Map<String, Object> model() throws ServiceException {
        User user = userProvider.getUser();
        Cart cart = cartProvider.getCart();
        return model(cart, user);
    }
    @Override
    public Map<String, Object> model(Cart cart, User user) throws ServiceException {
        if (cart.getAll().size() == 0) {
            throw new ServiceException(EMPTY_CART);
        }
        Map<String, Object> map = new HashMap<>();
        map.putAll(templateService.model(user));
        map.putAll(cartService.model(cart));
        if (user != null) {
            List<ShippingProfile> shippingProfiles = shippingProfileDAO.getAllByUser(user);
            map.put("shippingProfiles", shippingProfiles);
        }
        return map;
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Order checkout(String checkSum, User user, Cart cart, ShippingDetails shippingDetails, String date) throws ServiceException {
        if (!checkSum.equals(new Long(cart.getHashCode()).toString())) {
            throw new ServiceException(CART_CONTENT_HAS_CHANGED);
        }

        LocalDate delivery_date = DateUtils.asLocalDate(date, formatter);
        deliveryDateValidationService.validate(delivery_date);
        
        stockService.supply(cart);
        shippingDetailsFormatValidationService.validate(shippingDetails);
        Order order = new Order();
        orderUtil.build(userProvider.getUser(), order);
        orderUtil.build(shippingDetails, order);
        orderUtil.build(cart, order);
        order.setOrderDate(new Date());
        order.setDeliveryDate(DateUtils.asDate(delivery_date));
        order.setSecurityKey(lockedResourceAccessService.generateKey());
        order.setStatus(false);
        orderDAO.create(order);
        cartProvider.empty();
        try {
            if (shippingDetails.getId() == 0 && userProvider.authorized())
                shippingProfileService.save(shippingDetails);
        } catch (ServiceException e) {
        }

        return order;
    }
}

package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.CheckoutService;
import lv.javaguru.java2.businesslogic.PendingOrder;
import lv.javaguru.java2.businesslogic.UserProvider;
import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.domain.order.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class CheckoutController extends MVCController {
    private final String EMPTY_FIELDS = "All fields must be filled";

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private PendingOrder pendingOrder;

    @Autowired
    private UserProvider userProvider;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> data = checkoutService.serve();
        return new MVCModel(data, "/checkout.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        ShippingProfile shippingProfile = buildShippingProfileFromRequest(request);
        if (shippingProfile.getAddress().isEmpty() || shippingProfile.getPerson().isEmpty() ||
                shippingProfile.getPhone().isEmpty() || shippingProfile.getDocument().isEmpty()) {
            request.getSession().setAttribute("profileError", EMPTY_FIELDS);
            return redirectTo(CheckoutController.class);
        }
        Order order = new Order();
        order.setPhone(shippingProfile.getPhone());
        order.setAddress(shippingProfile.getAddress());
        order.setDocument(shippingProfile.getDocument());
        order.setPerson(shippingProfile.getPerson());
        order.setOrderDate(new Date());
        order.setDeliveryDate(new Date());
        order.setTotal(pendingOrder.getCart().getTotalPrice());
        if (userProvider.authorized())
            order.setUserId(userProvider.getUser().getId());
        Set<OrderLine> orderLines = new HashSet<OrderLine>();
        for (Map.Entry<Product, Integer> cartLine : pendingOrder.getCart().getAll().entrySet()) {
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
        return redirectTo(ProfileHistoryController.class);
    }

    private ShippingProfile buildShippingProfileFromRequest(HttpServletRequest request) {
        ShippingProfile shippingProfile = new ShippingProfile();

        String param = request.getParameter("profileId");
        if (param != null && !param.isEmpty()) {
            shippingProfile.setUserId(Long.valueOf(param));
        }
        shippingProfile.setAddress(request.getParameter("address"));
        shippingProfile.setPerson(request.getParameter("person"));
        shippingProfile.setPhone(request.getParameter("phone"));
        shippingProfile.setDocument(request.getParameter("document"));
        return shippingProfile;
    }
}

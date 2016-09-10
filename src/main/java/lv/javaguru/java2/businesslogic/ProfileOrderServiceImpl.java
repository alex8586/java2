package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.database.OrderLineDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProfileOrderServiceImpl implements ProfileOrderService {

    @Autowired
    private OrderLineDAO orderLineDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private SpecialSaleOffer specialSaleOffer;
    @Autowired
    private UserProvider userProvider;

    @Override
    public Map<String, Object> getOrder(long id) {
        Map<String, Object> orderData = new HashMap<>();

        Order order = orderDAO.getById(id);
        orderData.put("order", order);
        Product saleOffer = specialSaleOffer.getOffer();
        orderData.put("saleOffer", saleOffer);
        User user = userProvider.getUser();
        orderData.put("user", user);
        return orderData;
    }

    @Override
    public Map<String, Object> getHistoryOrders() {

        Map<String, Object> historyOrders = new HashMap<String, Object>();
        User user = userProvider.getUser();
        historyOrders.put("user", user);

        List<Order> orderList = orderDAO.getByUserId(user.getId());
        historyOrders.put("orderList", orderList);

        Product product = specialSaleOffer.getOffer();
        historyOrders.put("saleOffer", product);

        return historyOrders;
    }


}

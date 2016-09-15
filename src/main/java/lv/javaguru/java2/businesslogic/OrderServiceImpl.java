package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;


public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDAO;

    @Override
    public void create(Order order) {
        orderDAO.create(order);
    }
}
